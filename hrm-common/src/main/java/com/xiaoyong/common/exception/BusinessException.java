/**
 * Creation Date:2017年1月4日-下午5:04:37
 * 
 * Copyright 2010-2017 © 中格软件 Inc. All Rights Reserved
 */
package com.xiaoyong.common.exception;

/**
 * Description Of The Class<br/>
 * QQ:603470086
 * 
 * @author 	郁晓勇
 * @version 1.0.0, 2017年1月4日-下午5:04:37
 * @since 2017年1月4日-下午5:04:37
 */
public class BusinessException extends RuntimeException{
	
	private static final long serialVersionUID = 2617517055861243938L;

	private int code = 500;
	private String message;

	public BusinessException() {}

	public BusinessException(String message) {
		super(message);
		this.message = message;
	}

	public BusinessException(Throwable ex) {
		super(ex);
	}
	
	public BusinessException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}

	public BusinessException(int code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}

}

