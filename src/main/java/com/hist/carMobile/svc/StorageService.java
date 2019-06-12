package com.hist.carMobile.svc;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * <B>Project Name : </B>car-mobile<br/>
 * <B>Package Name : </B>com.hist.carMobile.svc<br/>
 * <B>File Name : </B>StorageService<br/>
 * <B>Description</B>
 * <ul>
 * <li>file 저장 service interface
 * </ul>
 * 
 * @author Joungmin
 * @since 2019. 6. 12.
 */
public interface StorageService {

	/**
	 * <B>History</B>
	 * <ul>
	 * <li>Date : 2019. 6. 12.
	 * <li>Developer : Joungmin
	 * <li>파일 저장 folder 생성(초기화)
	 * </ul>
	 * 
	 */
	void init();

	/**
	 * <B>History</B>
	 * <ul>
	 * <li>Date : 2019. 6. 12.
	 * <li>Developer : Joungmin
	 * <li>파일 저장
	 * </ul>
	 * 
	 * @param file
	 */
	void store(MultipartFile file);

	/**
	 * <B>History</B>
	 * <ul>
	 * <li>Date : 2019. 6. 12.
	 * <li>Developer : Joungmin
	 * <li>uuidFileName으로 파일 저장
	 * </ul>
	 * 
	 * @param file
	 * @param uuidFileName
	 */
	void store(MultipartFile file, String uuidFileName);

	/**
	 * <B>History</B>
	 * <ul>
	 * <li>Date : 2019. 6. 12.
	 * <li>Developer : Joungmin
	 * <li>파일 저장, 파일정보 DB 저장
	 * </ul>
	 * 
	 * @param file
	 */
	void fileAndInfoStore(MultipartFile file);

	/**
	 * <B>History</B>
	 * <ul>
	 * <li>Date : 2019. 6. 12.
	 * <li>Developer : Joungmin
	 * <li>폴더내 파일 조회
	 * </ul>
	 * 
	 * @return
	 */
	Stream<Path> loadAll();

	/**
	 * <B>History</B>
	 * <ul>
	 * <li>Date : 2019. 6. 12.
	 * <li>Developer : Joungmin
	 * <li>파일 명으로 조회
	 * </ul>
	 * 
	 * @param filename
	 * @return
	 */
	Path load(String filename);

	/**
	 * <B>History</B>
	 * <ul>
	 * <li>Date : 2019. 6. 12.
	 * <li>Developer : Joungmin
	 * <li>filename 에 해당하는 file을 resource 객체로 리턴
	 * </ul>
	 * 
	 * @param filename
	 * @return
	 */
	Resource loadAsResource(String filename);

	/**
	 * <B>History</B>
	 * <ul>
	 * <li>Date : 2019. 6. 12.
	 * <li>Developer : Joungmin
	 * <li>저장 폴더 내 파일 삭제
	 * </ul>
	 * 
	 */
	void deleteAll();

}
