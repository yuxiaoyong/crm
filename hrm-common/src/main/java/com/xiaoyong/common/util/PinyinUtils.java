/**
 * Creation Date:2017年2月24日-下午2:54:02
 * 
 * Copyright 2010-2017 © 中格软件 Inc. All Rights Reserved
 */
package com.xiaoyong.common.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 中文转拼音工具类
 * QQ:603470086
 * 
 * @author 	郁晓勇
 * @version 1.0.0, 2017年2月24日-下午2:54:02
 * @since 2017年2月24日-下午2:54:02
 */
public final class PinyinUtils {
	
	private PinyinUtils() {}
	
	private static final Logger logger = LoggerFactory.getLogger(PinyinUtils.class);
	/** 多音字字典信息，如果有其它的多音字可以自己添加 */
	private static final String DUOYINZI_DICT_FILE = "duoyinzi_dic.txt";
	/** 多音字字典 */
	private static Map<String, List<String>> pinyinMap = new HashMap<String, List<String>>();
  
    public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination {
        String str = "测试一aa下11重庆";  
        String py = convertChineseToPinyinShort(str);
        System.out.println(str+" : "+py);  
    }
    
    static{
    	initPinyin();
    }
    
    
    public static String convertChineseToPinyinShort(String chinese){
    	if(chinese == null || chinese.equals("")){
    		return "";
    	}
    	
        StringBuffer pinyin = new StringBuffer();  
  
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();  
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
  
        char[] arr = chinese.toCharArray();  
  
        for (int i = 0; i < arr.length; i++) {  
  
            char ch = arr[i];  
  
            if (ch > 128) { // 非ASCII码  
                // 取得当前汉字的所有全拼  
                try {  
  
                    String[] results = PinyinHelper.toHanyuPinyinStringArray(  
                            ch, defaultFormat);  
  
                    if (results == null) {  //非中文  
                        return "";  
                    } else {  
                        int len = results.length;  
  
                        if (len == 1) { // 不是多音字  
  
                            String py = results[0];       
                            if(py.contains("u:")){  //过滤 u:  
                                py = py.replace("u:", "v");  
                            }  
                            pinyin.append(firstLetter(py));  
                              
                        }else if(results[0].equals(results[1])){    //非多音字 有多个音，取第一个  
                              
                            pinyin.append(firstLetter(results[0]));  
                              
                        }else { // 多音字  
                              
                            int length = chinese.length();  
                              
                            boolean flag = false;  
                              
                            String s = null;  
                              
                            List<String> keyList =null;  
                              
                            for (int x = 0; x < len; x++) {  
                                  
                                String py = results[x];  
                                  
                                if(py.contains("u:")){  //过滤 u:  
                                    py = py.replace("u:", "v");  
                                }  
  
                                keyList = pinyinMap.get(py);  
                                  
                                if (i + 3 <= length) {   //后向匹配2个汉字  大西洋   
                                    s = chinese.substring(i, i + 3);  
                                    if (keyList != null && (keyList.contains(s))) {  
                                        pinyin.append(firstLetter(py));  
                                        flag = true;  
                                        break;  
                                    }  
                                }  
                                  
                                if (i + 2 <= length) {   //后向匹配 1个汉字  大西  
                                    s = chinese.substring(i, i + 2);  
                                    if (keyList != null && (keyList.contains(s))) {  
                                        pinyin.append(firstLetter(py));  
                                        flag = true;  
                                        break;  
                                    }  
                                }  
                                  
                                if ((i - 2 >= 0) && (i+1<=length)) {  // 前向匹配2个汉字 龙固大  
                                    s = chinese.substring(i - 2, i+1);  
                                    if (keyList != null && (keyList.contains(s))) {  
                                        pinyin.append(firstLetter(py));  
                                        flag = true;  
                                        break;  
                                    }  
                                }  
                                  
                                if ((i - 1 >= 0) && (i+1<=length)) {  // 前向匹配1个汉字   固大  
                                    s = chinese.substring(i - 1, i+1);  
                                    if (keyList != null && (keyList.contains(s))) {  
                                        pinyin.append(firstLetter(py));  
                                        flag = true;  
                                        break;  
                                    }  
                                }  
                                  
                                if ((i - 1 >= 0) && (i+2<=length)) {  //前向1个，后向1个      固大西  
                                    s = chinese.substring(i - 1, i+2);  
                                    if (keyList != null && (keyList.contains(s))) {  
                                        pinyin.append(firstLetter(py));  
                                        flag = true;  
                                        break;  
                                    }  
                                }  
                            }  
                              
                            if (!flag) {    //都没有找到，匹配默认的 读音  大   
                                  
                                s = String.valueOf(ch);  
                                  
                                for (int x = 0; x < len; x++) {  
                                      
                                    String py = results[x];  
                                      
                                    if(py.contains("u:")){  //过滤 u:  
                                        py = py.replace("u:", "v");  
                                    }  
                                    pinyin.append(firstLetter(py));
                                    break;
//                                    keyList = pinyinMap.get(py);  
//                                      
//                                    if (keyList != null && (keyList.contains(s))) {  
//                                        pinyin.append(convertInitialToUpperCase(py));//拼音首字母 大写  
//                                        break;  
//                                    }
                                }  
                            }  
                        }  
                    }  
  
                } catch (BadHanyuPinyinOutputFormatCombination e) {  
                	logger.error(e.getMessage(), e);
                }  
            } else {
                pinyin.append(arr[i]);  
            }  
        }  
        return pinyin.toString();  
    }
  
    /**
     * 
     * 
     * @author 	郁晓勇
     * @version 1.0.0, 2017年2月24日-下午4:08:29
     * @param value
     * @return String
     */
    public static String firstLetter(String value){
    	if(value == null || value.equals("")){
    		return "";
    	}else{
    		return value.substring(0, 1);
    	}
    }
    /**
     * 汉字转拼音
     * 
     * @author 	郁晓勇
     * @version 1.0.0, 2017年2月24日-下午3:18:54
     * @param chinese 输入字符串参数
     * @return String
     */
    public static String convertChineseToPinyin(String chinese) {  
  
    	if(chinese == null || chinese.equals("")){
    		return "";
    	}
    	
        StringBuffer pinyin = new StringBuffer();  
  
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();  
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
  
        char[] arr = chinese.toCharArray();  
  
        for (int i = 0; i < arr.length; i++) {  
  
            char ch = arr[i];  
  
            if (ch > 128) { // 非ASCII码  
                // 取得当前汉字的所有全拼  
                try {  
  
                    String[] results = PinyinHelper.toHanyuPinyinStringArray(  
                            ch, defaultFormat);  
  
                    if (results == null) {  //非中文  
                        return "";  
                    } else {  
                        int len = results.length;  
  
                        if (len == 1) { // 不是多音字  
  
                            String py = results[0];       
                            if(py.contains("u:")){  //过滤 u:  
                                py = py.replace("u:", "v");  
                            }  
                            pinyin.append(convertInitialToUpperCase(py));  
                              
                        }else if(results[0].equals(results[1])){    //非多音字 有多个音，取第一个  
                              
                            pinyin.append(convertInitialToUpperCase(results[0]));  
                              
                        }else { // 多音字  
                              
                            int length = chinese.length();  
                              
                            boolean flag = false;  
                              
                            String s = null;  
                              
                            List<String> keyList =null;  
                              
                            for (int x = 0; x < len; x++) {  
                                  
                                String py = results[x];  
                                  
                                if(py.contains("u:")){  //过滤 u:  
                                    py = py.replace("u:", "v");  
                                }  
  
                                keyList = pinyinMap.get(py);  
                                  
                                if (i + 3 <= length) {   //后向匹配2个汉字  大西洋   
                                    s = chinese.substring(i, i + 3);  
                                    if (keyList != null && (keyList.contains(s))) {  
                                        pinyin.append(convertInitialToUpperCase(py));  
                                        flag = true;  
                                        break;  
                                    }  
                                }  
                                  
                                if (i + 2 <= length) {   //后向匹配 1个汉字  大西  
                                    s = chinese.substring(i, i + 2);  
                                    if (keyList != null && (keyList.contains(s))) {  
                                        pinyin.append(convertInitialToUpperCase(py));  
                                        flag = true;  
                                        break;  
                                    }  
                                }  
                                  
                                if ((i - 2 >= 0) && (i+1<=length)) {  // 前向匹配2个汉字 龙固大  
                                    s = chinese.substring(i - 2, i+1);  
                                    if (keyList != null && (keyList.contains(s))) {  
                                        pinyin.append(convertInitialToUpperCase(py));  
                                        flag = true;  
                                        break;  
                                    }  
                                }  
                                  
                                if ((i - 1 >= 0) && (i+1<=length)) {  // 前向匹配1个汉字   固大  
                                    s = chinese.substring(i - 1, i+1);  
                                    if (keyList != null && (keyList.contains(s))) {  
                                        pinyin.append(convertInitialToUpperCase(py));  
                                        flag = true;  
                                        break;  
                                    }  
                                }  
                                  
                                if ((i - 1 >= 0) && (i+2<=length)) {  //前向1个，后向1个      固大西  
                                    s = chinese.substring(i - 1, i+2);  
                                    if (keyList != null && (keyList.contains(s))) {  
                                        pinyin.append(convertInitialToUpperCase(py));  
                                        flag = true;  
                                        break;  
                                    }  
                                }  
                            }  
                              
                            if (!flag) {    //都没有找到，匹配默认的 读音  大   
                                  
                                s = String.valueOf(ch);  
                                  
                                for (int x = 0; x < len; x++) {  
                                      
                                    String py = results[x];  
                                      
                                    if(py.contains("u:")){  //过滤 u:  
                                        py = py.replace("u:", "v");  
                                    }  
                                    pinyin.append(convertInitialToUpperCase(py));
                                    break;
//                                    keyList = pinyinMap.get(py);  
//                                      
//                                    if (keyList != null && (keyList.contains(s))) {  
//                                        pinyin.append(convertInitialToUpperCase(py));//拼音首字母 大写  
//                                        break;  
//                                    }
                                }  
                            }  
                        }  
                    }  
  
                } catch (BadHanyuPinyinOutputFormatCombination e) {  
                	logger.error(e.getMessage(), e);
                }  
            } else {  
                pinyin.append(arr[i]);  
            }  
        }  
        return pinyin.toString();  
    }  
  
    
    /**
     * 首字母大写
     * 
     * @author 	郁晓勇
     * @version 1.0.0, 2017年2月24日-下午3:18:34
     * @param str
     * @return String
     */
    private static String convertInitialToUpperCase(String str){
        if(str==null){  
            return null;  
        }  
        StringBuffer sb = new StringBuffer();  
        char[] arr = str.toCharArray();  
        for(int i=0;i<arr.length;i++){  
            char ch = arr[i];  
            if(i==0){  
                sb.append(String.valueOf(ch).toUpperCase());  
            }else{  
                sb.append(ch);  
            }  
        }  
          
        return sb.toString();  
    }  
   /**
    * 初始化多音字字典信息
    * 
    * @author 	郁晓勇
    * @version 1.0.0, 2017年2月24日-下午2:58:04
    */
    private static void initPinyin() {  
        // 读取多音字的全部拼音表;
    	logger.info("load duoyinzi dict from classpath file: " + DUOYINZI_DICT_FILE);
    	InputStream file = Thread.currentThread().getContextClassLoader().getResourceAsStream(DUOYINZI_DICT_FILE);
    	if(file == null){
    		logger.info("classpath file not found, load default duoyinzi dict file: " + DUOYINZI_DICT_FILE);
    		file = PinyinUtils.class.getResourceAsStream(DUOYINZI_DICT_FILE);
    	}
  
        BufferedReader br = new BufferedReader(new InputStreamReader(file));  
  
        String s = null;  
        try {  
            while ((s = br.readLine()) != null) {  
  
                if (s != null) {  
                    String[] arr = s.split("#");  
                    String pinyin = arr[0];  
                    String chinese = arr[1];  
  
                    if(chinese!=null){  
                        String[] strs = chinese.split(" ");  
                        List<String> list = Arrays.asList(strs);  
                        pinyinMap.put(pinyin, list);  
                    }  
                }  
            }  
  
        } catch (IOException e) {  
            logger.error(e.getMessage(), e);
        }finally{  
            try {  
                br.close();  
            } catch (IOException e) {  
            	logger.error(e.getMessage(), e);
            }  
        }  
    }  
}

