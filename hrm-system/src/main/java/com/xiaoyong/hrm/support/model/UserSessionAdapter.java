package com.xiaoyong.hrm.support.model;/**
 * Created by atlantisholic on 2018/5/28.
 */

/**
 * @ClassName UserSessionAdapter
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/5/28 11:26
 * @Version 1.0.0
 **/
public class UserSessionAdapter<T> implements UserSession {

    private int userId;
    private String userName;
    private String mobile;
    private String email;
    private int gender;
    private Integer deptId;
    private String deptName;

    @Override
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Override
    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    @Override
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
