package com.xiaoyong.hrm.support.util;
/**
 * Created by atlantisholic on 2018/6/14.
 */

import com.xiaoyong.hrm.support.model.UserSession;
import org.apache.shiro.SecurityUtils;

/**
 * @ClassName SessionUtils
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/6/14 15:03
 * @Version 1.0.0
 **/
public final class SessionUtils {

    private SessionUtils(){}

    public static UserSession getUserSession(){
        return (UserSession) SecurityUtils.getSubject().getPrincipal();
    }

}
