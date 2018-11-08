package com.xiaoyong.hrm.shiro.realm;

import com.xiaoyong.hrm.shiro.token.JwtToken;
import com.xiaoyong.hrm.support.model.UserSessionAdapter;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 *
 * Description Of The Class<br/>
 * QQ:603470086
 *
 * @author 	郁晓勇
 * @version 1.0.0, 2015年6月12日-下午3:06:15
 * @since 2015年6月12日-下午3:06:15
 */
public class ApplicationAuthorizingRealm extends AuthorizingRealm {

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {

		AuthenticationInfo authenticationInfo = null;

		if(token instanceof UsernamePasswordToken){
			authenticationInfo = doLoginByUsernamePasswordToken((UsernamePasswordToken) token);
		}else{
			authenticationInfo = doLoginByJwtToken((JwtToken) token);
		}

		return authenticationInfo;
	}

	protected AuthenticationInfo doLoginByUsernamePasswordToken(UsernamePasswordToken usernamePasswordToken){
		if( !StringUtils.equals(usernamePasswordToken.getUsername(), "zhangjing")
				|| !StringUtils.equals(new String(usernamePasswordToken.getPassword()), "123456")){
			throw new AuthenticationException("用户名或密码错误");
		}
		return new SimpleAuthenticationInfo(new UserSessionAdapter<>(), usernamePasswordToken.getCredentials(), "usernamePasswordToken");
	}

	protected AuthenticationInfo doLoginByJwtToken(JwtToken jwtToken){
		return new SimpleAuthenticationInfo(new UserSessionAdapter(), jwtToken.getCredentials(), "jwtToken");
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return true;
	}
}
