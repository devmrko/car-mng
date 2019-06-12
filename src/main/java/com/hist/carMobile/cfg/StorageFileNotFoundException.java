package com.hist.carMobile.cfg;

/**
 * <B>Project Name : </B>car-mobile<br/>
 * <B>Package Name : </B>com.hist.carMobile.cfg<br/>
 * <B>File Name : </B>StorageFileNotFoundException<br/>
 * <B>Description</B>
 * <ul>
 * <li>파일 저장 에 사용 되는 FileNotFound 경우의 Exception
 * </ul>
 * 
 * @author Joungmin
 * @since 2019. 6. 12.
 */
public class StorageFileNotFoundException extends StorageException {

	private static final long serialVersionUID = -5102827323230731342L;

	public StorageFileNotFoundException(String message) {
		super(message);
	}

	public StorageFileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}