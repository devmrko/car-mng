package com.hist.carMobile.cfg;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <B>Project Name : </B>car-mobile<br/>
 * <B>Package Name : </B>com.hist.carMobile.cfg<br/>
 * <B>File Name : </B>StorageProperties<br/>
 * <B>Description</B>
 * <ul>
 * <li>파일 저장시 사용할 properties 관련 class
 * </ul>
 * 
 * @author Joungmin
 * @since 2019. 6. 12.
 */
@ConfigurationProperties("storage")
public class StorageProperties {

	@Value("${file.path}")
	private String location;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
