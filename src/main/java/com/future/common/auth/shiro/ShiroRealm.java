package com.future.common.auth.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.future.common.config.FrameworkConfig;
import com.future.common.config.FrameworkConfig.Jwt;
import com.future.common.utils.jwt.TokenUtils;
import com.future.common.utils.jwt.UserPayload;
import com.future.system.domain.User;
import com.future.system.domain.enums.State;
import com.future.system.service.UserService;

public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private FrameworkConfig frameworkConfig;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 给用户授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = null;
        if(principals != null) {
            user = (User) principals.getPrimaryPrincipal();
        }
        
        return null;
    }

    /**
     * 用户登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        if (token == null) {
            throw new AuthenticationException("token invalid");
        }
        Jwt jwt = frameworkConfig.getJwt();
        UserPayload payload = TokenUtils.validateAndGetPayload(token, jwt.getSecret());
        if(payload == null || payload.userId() == null) {
            throw new AuthenticationException("token invalid");
        }
        User user = this.userService.findById(payload.userId());
        if(user == null) {
            throw new AuthenticationException("用户不存在");
        }
        if(user.getState() == State.LOCKED) {
            throw new AuthenticationException("账号被锁定, 请联系管理员");
        }
        if(user.getState() == State.INVALID) {
            throw new AuthenticationException("用户无效");
        }
        return new SimpleAuthenticationInfo(user, token, getName());
    }

}
