/**
 * Creation Date:2016年7月14日-下午2:55:36
 * 
 * Copyright 2010-2016 © 中格软件 Inc. All Rights Reserved
 */
package com.xiaoyong.hrm.support.model;

/**
 * Description Of The Class<br/>
 * QQ:603470086
 * 
 * @author 	郁晓勇
 * @version 1.0.0, 2016年7月14日-下午2:55:36
 * @since 2016年7月14日-下午2:55:36
 */
public class SuccessResult extends GenericResult {
	
	private static final long serialVersionUID = 1L;
	
	public static <T> SuccessResult create(){
		SuccessResult result = new SuccessResult();
		result.setRetcode(GenericResult.RETCODE_SUCCESS);
		result.setAttr("success", true);
		return result;
	}

	@SuppressWarnings("unchecked")
	public <T> T getData() {
		return (T)this.get("data");
	}

	public <T> SuccessResult setData(T data) {
		this.put("data", data);
		return this;
	}

}

