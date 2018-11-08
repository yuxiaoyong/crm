/**
 * Creation Date:2017年5月2日-上午10:54:00
 * 
 * Copyright 2010-2017 © 中格软件 Inc. All Rights Reserved
 */
package com.xiaoyong.common.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Description Of The Class<br/>
 * QQ:603470086
 * 
 * @author 	郁晓勇
 * @version 1.0.0, 2017年5月2日-上午10:54:00
 * @since 2017年5月2日-上午10:54:00
 */
public final class HierarchyUtils {

	private final static int DEFAULT_LENGTH = 10;
	private final static char DEFAULT_PAD_CHAR = '0';
	
	private HierarchyUtils() {}
	
	public static String wrap(int id){
		String temp = id+"";
		if(temp.length() < DEFAULT_LENGTH){
			return StringUtils.leftPad(temp, DEFAULT_LENGTH, DEFAULT_PAD_CHAR);
		}
		return temp;
	}
	
}

