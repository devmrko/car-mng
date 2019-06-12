package com.hist.carMobile;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.hist.carMobile.cfg.StorageProperties;
import com.hist.carMobile.svc.StorageService;

/**
 * <B>Project Name : </B>car-mobile<br/>
 * <B>Package Name : </B>com.hist.carMobile<br/>
 * <B>File Name : </B>Application<br/>
 * <B>Description</B>
 * <ul>
 * <li>Spring boot에서 구동시킬 main class
 * </ul>
 * 
 * @author Joungmin
 * @since 2019. 6. 12.
 */
@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * <B>History</B>
	 * <ul>
	 * <li>Date : 2019. 6. 12.
	 * <li>Developer : Joungmin
	 * <li>어플리케이션 구동 초기 구동 내역
	 * </ul>
	 * 
	 * @param storageService
	 * @return
	 */
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			// 해당 path 에 있는 파일 삭제
			// storageService.deleteAll();
			// 해당 path folder 생성
			// storageService.init();
		};
	}
}
