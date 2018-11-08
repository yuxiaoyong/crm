/**
 * Creation Date:2017年1月4日-下午5:22:02
 * 
 * Copyright 2010-2017 © 中格软件 Inc. All Rights Reserved
 */
package com.xiaoyong.common.exception;

/**
 * Description Of The Class<br/>
 * QQ:603470086
 * 
 * @author 	郁晓勇
 * @version 1.0.0, 2017年1月4日-下午5:22:02
 * @since 2017年1月4日-下午5:22:02
 */
public class AuthenticationException extends BusinessException {
	
	private static final long serialVersionUID = 1059805444002367626L;

	public AuthenticationException() {}
	
	public AuthenticationException(String message) {
		super(message);
	}
	
	public AuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}
	
}

