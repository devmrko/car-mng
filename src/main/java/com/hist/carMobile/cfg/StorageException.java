package com.hist.carMobile.cfg;

/**
 * <B>Project Name : </B>car-mobile<br/>
 * <B>Package Name : </B>com.hist.carMobile.cfg<br/>
 * <B>File Name : </B>StorageException<br/>
 * <B>Description</B>
 * <ul>
 * <li>파일 저장 에 사용 되는 generic 한 RuntimeException
 * </ul>
 * 
 * @author Joungmin
 * @since 2019. 6. 12.
 */
public class StorageException extends RuntimeException {

	private static final long serialVersionUID = 3081464902462988299L;

	public StorageException(String message) {
		super(message);
	}

	public StorageException(String message, Throwable cause) {
		super(message, cause);
	}
}
