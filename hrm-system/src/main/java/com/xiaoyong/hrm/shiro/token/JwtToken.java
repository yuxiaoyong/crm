package com.xiaoyong.hrm.shiro.token;/**
 * Created by atlantisholic on 2018/8/12.
 */

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @ClassName JwtToken
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/8/12 14:07
 * @Version 1.0.0
 **/
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token){
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
