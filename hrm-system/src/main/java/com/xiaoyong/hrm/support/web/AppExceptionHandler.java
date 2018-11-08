/**
 * Creation Date:2017年1月4日-下午2:27:47
 * 
 * Copyright 2010-2017 © 中格软件 Inc. All Rights Reserved
 */
package com.xiaoyong.hrm.support.web;

import com.xiaoyong.common.exception.*;
import com.xiaoyong.common.util.WebUtils;
import com.xiaoyong.hrm.support.model.FailureResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description Of The Class<br/>
 * QQ:603470086
 * 
 * @author 	郁晓勇
 * @version 1.0.0, 2017年1月4日-下午2:27:47
 * @since 2017年1月4日-下午2:27:47
 */
@ControllerAdvice()
public class AppExceptionHandler {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@ExceptionHandler
	public Object handleException(HttpServletRequest request, HttpServletResponse response,
                                  Object obj, Exception ex) throws Exception{

		if(ex instanceof ResourceNotFoundException){
			logger.info(ex.getMessage(), ex);
		}else{
			logger.error(ex.getMessage(), ex);
		}

		if(WebUtils.isAjaxRequest(request) || StringUtils.startsWithIgnoreCase(request.getRequestURI().replace(request.getContextPath(), ""), "/api")){
			return processAjaxRequest(ex);
		}

		return processCommonRequest(ex);
	}

	private ResponseEntity<?> processAjaxRequest(Exception ex){
		if(ex instanceof HttpRequestMethodNotSupportedException){
			return ResponseEntity
					.status(HttpStatus.METHOD_NOT_ALLOWED)
					.body(FailureResult.create(ex.getMessage(), ex.getMessage(), HttpStatus.METHOD_NOT_ALLOWED.value()));
		}
		if(ex instanceof BadRequestException){
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(FailureResult.create(ex.getMessage(), ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
		}
		if(ex instanceof BusinessException){
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(FailureResult.create(ex.getMessage(), ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
		}
		if(ex instanceof AuthorizationException || ex instanceof AuthenticationException || ex instanceof org.apache.shiro.authc.AuthenticationException){
			return ResponseEntity
					.status(HttpStatus.UNAUTHORIZED)
					.body(FailureResult.create(ex.getMessage(), ex.getMessage(), HttpStatus.UNAUTHORIZED.value()));
		}
		if(ex instanceof ResourceNotFoundException){
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(FailureResult.create(ex.getMessage(), ex.getMessage(), HttpStatus.NOT_FOUND.value()));
		}
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(FailureResult.create("服务器内部错误", "服务器内部错误", HttpStatus.INTERNAL_SERVER_ERROR.value()));
	}

	private ModelAndView processCommonRequest(Exception ex){
		if(ex instanceof BadRequestException){
			return new ModelAndView("redirect:/html/error.html");
		}
		if(ex instanceof AuthorizationException || ex instanceof AuthenticationException){
			return new ModelAndView("redirect:/html/401.html");
		}
		if(ex instanceof ResourceNotFoundException){
			return new ModelAndView("redirect:/html/404.html");
		}
		return new ModelAndView("redirect:/html/500.html");
	}
	
}

