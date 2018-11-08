/**
 * Creation Date:2017年6月27日-上午9:43:24
 * 
 * Copyright 2010-2017 © 中格软件 Inc. All Rights Reserved
 */
package com.xiaoyong.common.exception;

/**
 * Description Of The Class<br/>
 * QQ:603470086
 * 
 * @author 	郁晓勇
 * @version 1.0.0, 2017年6月27日-上午9:43:24
 * @since 2017年6月27日-上午9:43:24
 */
public class FileNotFoundException extends ResourceNotFoundException {
	
	private static final long serialVersionUID = -3010497118142527411L;

	public FileNotFoundException() {}
	
	public FileNotFoundException(String message) {
		super(message);
	}
	
	public FileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
}

