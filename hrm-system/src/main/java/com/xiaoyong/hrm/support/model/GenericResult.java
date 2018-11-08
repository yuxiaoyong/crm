/**
 * Creation Date:2016年7月14日-下午2:48:13
 * 
 * Copyright 2010-2016 © 中格软件 Inc. All Rights Reserved
 */
package com.xiaoyong.hrm.support.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

/**
 * Description Of The Class<br/>
 * QQ:603470086
 * 
 * @author 	郁晓勇
 * @version 1.0.0, 2016年7月14日-下午2:48:13
 * @since 2016年7月14日-下午2:48:13
 */
public abstract class GenericResult extends HashMap<String, Object>{

	private static final long serialVersionUID = 1L;
	
	public static final int RETCODE_SUCCESS = 0;
	public static final int RETCODE_FAILURE = 1;
	
	private static final String RETCODE_LABEL = "retcode";

	public int getRetcode() {
		return (Integer)this.get(RETCODE_LABEL);
	}

	public GenericResult setRetcode(int retcode) {
		this.put(RETCODE_LABEL, retcode);
		return this;
	}
	
	public boolean isSuccess(){
		if(this.getRetcode() == RETCODE_SUCCESS){
			return true;
		}else{
			return false;
		}
	}
	
	public GenericResult setAttr(String key, Object value){
		this.put(key, value);
		return this;
	}
	
	public GenericResult removeAttr(String key){
		this.remove(key);
		return this;
	}
	
	
	public String toJsonString() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(this);
		return json;
	}
}

