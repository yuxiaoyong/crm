package com.xiaoyong.hrm.system.web;/**
 * Created by atlantisholic on 2018/8/12.
 */

import com.xiaoyong.hrm.shiro.token.JwtToken;
import com.xiaoyong.hrm.system.model.AccessToken;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName AuthenticationController
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/8/12 12:52
 * @Version 1.0.0
 **/
@Controller
@RequestMapping("/api/accesstoken")
public class AccessTokenController {

    @ApiOperation("根据用户名密码获取access_token")
    @GetMapping(value = "/get")
    public ResponseEntity<?> getAccessToken(String username, String password){
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        SecurityUtils.getSubject().login(token);
        return ResponseEntity.ok(createAccessToken());
    }

    @ApiOperation("刷新access_token")
    @GetMapping(value = "/refresh")
    public ResponseEntity<?> refreshAccessToken(@RequestParam("access_token") String accessToken){
        SecurityUtils.getSubject().logout();
        SecurityUtils.getSubject().login(new JwtToken(accessToken));
        return ResponseEntity.ok(createAccessToken());
    }

    @ApiOperation("销毁用户的access_token")
    @PostMapping(value = "/destroy")
    public ResponseEntity<?> destroyToken(){
        SecurityUtils.getSubject().logout();
        return ResponseEntity.ok().build();
    }

    private AccessToken createAccessToken(){
        return new AccessToken(SecurityUtils.getSubject().getSession().getId().toString(), 3600);
    }

}
