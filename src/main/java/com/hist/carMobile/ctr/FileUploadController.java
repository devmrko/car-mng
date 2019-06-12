package com.hist.carMobile.ctr;

import java.io.IOException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hist.carMobile.cfg.StorageFileNotFoundException;
import com.hist.carMobile.svc.StorageService;

/**
 * <B>Project Name : </B>car-mobile<br/>
 * <B>Package Name : </B>com.hist.carMobile.ctr<br/>
 * <B>File Name : </B>FileUploadController<br/>
 * <B>Description</B>
 * <ul>
 * <li>파일 저장 controller
 * </ul>
 * 
 * @author Joungmin
 * @since 2019. 6. 12.
 */
@Controller
public class FileUploadController {

	private final StorageService storageService;

	@Autowired
	public FileUploadController(StorageService storageService) {
		this.storageService = storageService;
	}

	/**
	 * <B>History</B>
	 * <ul>
	 * <li>Date : 2019. 6. 12.
	 * <li>Developer : Joungmin
	 * <li>모델에 파일 내역을 List로 담아서 uploadForm(html 파일)을 화면에 표시하도록 리턴, 이때 modle의 files
	 * parameter 에 serveFile method를 통해 생성된 Resource 를 전달하도록 함, get method
	 * </ul>
	 * 
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/")
	public String listUploadedFiles(Model model) throws IOException {

		model.addAttribute("files",
				storageService.loadAll()
						.map(path -> MvcUriComponentsBuilder
								.fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
								.build().toString())
						.collect(Collectors.toList()));

		return "uploadForm";
	}

	/**
	 * <B>History</B>
	 * <ul>
	 * <li>Date : 2019. 6. 12.
	 * <li>Developer : Joungmin
	 * <li>serveFile 메소드에 fileName을 전달 받아 해당 fileName의 Resources를 ResponseEntity에 넣어
	 * 전달, get method
	 * </ul>
	 * 
	 * @param filename
	 * @return
	 */
	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@PostMapping("/custom-upload")
	public @ResponseBody String carMngFileUpload(@RequestParam("file") MultipartFile file) {
		String returnMsg;
		try {
			storageService.fileAndInfoStore(file);
			
			returnMsg = "You successfully uploaded " + file.getOriginalFilename() + "!";
		} catch (Exception e) {
			returnMsg = "error";
		}
		return returnMsg;
	}
	
	/**
	 * <B>History</B>
	 * <ul>
	 * <li>Date : 2019. 6. 12.
	 * <li>Developer : Joungmin
	 * <li>파일 저장만 하는 request mapping method, post method
	 * </ul>
	 * 
	 * @param file
	 * @return
	 */
	@PostMapping("/upload")
	public @ResponseBody String customHandleFileUpload(@RequestParam("file") MultipartFile file) {
		String returnMsg;
		try {
			storageService.store(file);
			returnMsg = "You successfully uploaded " + file.getOriginalFilename() + "!";
		} catch (Exception e) {
			returnMsg = "error";
		}
		return returnMsg;
	}

	/**
	 * <B>History</B>
	 * <ul>
	 * <li>Date : 2019. 6. 12.
	 * <li>Developer : Joungmin
	 * <li>파일 저장 후 / 페이지로 redirect 하여 파일 리스트를 보여주도록 함
	 * </ul>
	 * 
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/")
	public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

		storageService.store(file);
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");

		return "redirect:/";
	}

	/**
	 * <B>History</B>
	 * <ul>
	 * <li>Date : 2019. 6. 12.
	 * <li>Developer : Joungmin
	 * <li>파일이 없는 Exception 경우 처리 리턴 값 처리
	 * </ul>
	 * 
	 * @param exc
	 * @return
	 */
	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}

}
