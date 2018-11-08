package com.xiaoyong.hrm.support.model;/**
 * Created by atlantisholic on 2018/5/25.
 */

/**
 * @ClassName UserSession
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/5/25 17:52
 * @Version 1.0.0
 **/
public interface UserSession<T> {

    public int getUserId();
    public String getUserName();
    public String getMobile();
    public String getEmail();
    public int getGender();
    public Integer getDeptId();
    public String getDeptName();
    /**
     * 用户角色，包含：WY、YWH、WGZX、COMMUNITY
     * @return
     */
}
