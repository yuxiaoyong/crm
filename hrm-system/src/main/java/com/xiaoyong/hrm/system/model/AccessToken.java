package com.xiaoyong.hrm.system.model;/**
 * Created by atlantisholic on 2018/8/12.
 */

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @ClassName AccessToken
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/8/12 13:49
 * @Version 1.0.0
 **/
public class AccessToken {

    private String token;
    private long expiresIn;

    public AccessToken(String token, long expiresIn){
        this.token = token;
        this.expiresIn = expiresIn;
    }

    @JsonProperty("access_token")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @JsonProperty("expires_in")
    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
