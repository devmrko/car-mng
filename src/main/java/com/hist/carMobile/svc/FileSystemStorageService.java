package com.hist.carMobile.svc;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.hist.carMobile.cfg.StorageException;
import com.hist.carMobile.cfg.StorageFileNotFoundException;
import com.hist.carMobile.cfg.StorageProperties;

/**
 * <B>Project Name : </B>car-mobile<br/>
 * <B>Package Name : </B>com.hist.carMobile.svc<br/>
 * <B>File Name : </B>FileSystemStorageService<br/>
 * <B>Description</B>
 * <ul>
 * <li>파일저장 service interface 를 구현한 class
 * </ul>
 * 
 * @author Joungmin
 * @since 2019. 6. 12.
 */
@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = { Exception.class,
		RuntimeException.class })
@Service
public class FileSystemStorageService implements StorageService {

	private final Path rootLocation;

	@Autowired
	public FileSystemStorageService(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}

	public void fileAndInfoStore(MultipartFile file) {
		
		// TODO 1. UUID 파일 명 생성, 원본 파일명 저장
		String orgFilename = StringUtils.cleanPath(file.getOriginalFilename());
		String uuidFilename = UUID.randomUUID().toString();
		// 기타 필요한 파일 정보 table key 정보 등
		
		// TOOD 2. 1번 내역 정보를 DB에 저장
		
		
		// TODO 3. 파일 저장
		// 파일명 UUID 명으로 변환
		store(file, uuidFilename);
	}
	
	@Override
	public void store(MultipartFile file, String uuidFileName) {
		// 원 파일명 filename 으로 보관
		String filename = StringUtils.cleanPath(uuidFileName);
		try {
			FileValidation(file, filename);

			// InputStream 변수에 파일 stream 저장
			try (InputStream inputStream = file.getInputStream()) {
				// stream 내역을 파일로 저장, 기존에 같은 파일명 존재시 replace(교체)
				Files.copy(inputStream, this.rootLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (IOException e) {
			// IOException 발생시 예외 처리
			throw new StorageException("Failed to store file " + filename, e);
		}
	}
	
	@Override
	public void store(MultipartFile file) {
		// 원 파일명 filename 으로 보관
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			FileValidation(file, filename);

			// InputStream 변수에 파일 stream 저장
			try (InputStream inputStream = file.getInputStream()) {
				// stream 내역을 파일로 저장, 기존에 같은 파일명 존재시 replace(교체)
				Files.copy(inputStream, this.rootLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (IOException e) {
			// IOException 발생시 예외 처리
			throw new StorageException("Failed to store file " + filename, e);
		}
	}

	private void FileValidation(MultipartFile file, String filename) {
		// 파일내용이 없을 때 에외 처리
		if (file.isEmpty()) {
			throw new StorageException("Failed to store empty file " + filename);
		}
		// 저장 폴더 상위 path 로 저장 시도 시 예외 처리
		if (filename.contains("..")) {
			// This is a security check
			throw new StorageException(
					"Cannot store file with relative path outside current directory " + filename);
		}
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			// 파일 load시 depth 지정 1 depth
			// filter에서 path 항목 제거, 파일 조회시 path 도 하나의 항목으로 취급 됨
			// file명을 포함한 path 내역은 relativize(상대) 로 리턴
			return Files.walk(this.rootLocation, 1).filter(path -> !path.equals(this.rootLocation))
					.map(this.rootLocation::relativize);
		} catch (IOException e) {
			throw new StorageException("Failed to read stored files", e);
		}

	}

	@Override
	public Path load(String filename) {
		// filename에 해당하는 파일 path 정보 리턴
		return rootLocation.resolve(filename);
	}

	@Override
	public Resource loadAsResource(String filename) {
		try {
			// filename에 해당하는 file을 resource 객체로 리턴
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new StorageFileNotFoundException("Could not read file: " + filename);

			}
		} catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		}
	}

	@Override
	public void deleteAll() {
		// 파일 path 내역 삭제
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	@Override
	public void init() {
		try {
			// 파일 저장 path 생성
			Files.createDirectories(rootLocation);
		} catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}
}
