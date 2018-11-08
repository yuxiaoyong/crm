/**
 * Creation Date:2016年7月14日-下午2:49:26
 * 
 * Copyright 2010-2016 © 中格软件 Inc. All Rights Reserved
 */
package com.xiaoyong.hrm.support.model;


/**
 * Description Of The Class<br/>
 * QQ:603470086
 * 
 * @author 	郁晓勇
 * @version 1.0.0, 2016年7月14日-下午2:49:26
 * @since 2016年7月14日-下午2:49:26
 */
public class FailureResult extends GenericResult{
	
	private static final long serialVersionUID = 1L;
	
	public static FailureResult create(String message, String internalMessage, int code){
		return (FailureResult)new FailureResult().setMessage(message)
												 .setInternalMessage(internalMessage)
				                                 .setRetcode(code)
				                                 .setAttr("success", false);
	}

	public String getInternalMessage(){
		return (String)this.get("internalMessage");
	}
	
	public FailureResult setInternalMessage(String internalMessage){
		this.put("internalMessage", internalMessage);
		return this;
	}
	
	public String getMessage() {
		return (String)this.get("message");
	}

	public FailureResult setMessage(String message) {
		this.put("message", message);
		return this;
	}

}

