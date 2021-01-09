package com.hl.myblog.security.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.hl.myblog.redis.RedisUtil;
import com.hl.myblog.security.jwt.JWTToken;
import com.hl.myblog.security.jwt.JWTUtil;
import com.hl.myblog.service.impl.UserServiceImpl;

/**
 * 登录授权realm
 * 
 * @author huanglin
 * @data   2020年7月8日
 */
@Service
public class ShiroRealm extends AuthorizingRealm{

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 为当前登录用户授予角色权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String                  userName          = JWTUtil.getUserName(principals.getPrimaryPrincipal().toString());
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 根据登录名获取登录信息
        if(!StringUtils.isEmpty(userName)) {
            // 设置用户角色
            authorizationInfo.addRole("admin");
            // 设置权限
            authorizationInfo.addStringPermission("admin");

            return authorizationInfo;
        }

        return null;
    }

    /**
     * 验证当前登录用户
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws UnauthenticatedException {
        String token    = (String) auth.getCredentials();
        String userName = JWTUtil.getUserName(token);
        if(StringUtils.isEmpty(userName)) {
            throw new UnauthenticatedException("token错误");
        }

        try {
            if(JWTUtil.verify(token, userName, redisUtil)) {
                return new SimpleAuthenticationInfo(token, token, getName());
            } else {
                throw new UnauthenticatedException("token认证失败");
            }
        } catch (TokenExpiredException e) {
            throw new UnauthenticatedException("token已过期");
        } catch (SignatureVerificationException e) {
            throw new UnauthenticatedException("密码不正确!");
        }
    }

    /**
     * 清除登录用户授权信息缓存
     */
    public void clearCached() {
        this.clearCachedAuthenticationInfo(SecurityUtils.getSubject().getPreviousPrincipals());
    }

    /**
     * 超级管理员不做权限判断,自动拥有所有权限
     */
    @Override
    public boolean isPermitted(PrincipalCollection principals, String permission) {
        String username = JWTUtil.getUserName(principals.getPrimaryPrincipal().toString());

        return username.equals("huanglin") || super.isPermitted(principals, permission);
    }

    @Override
    public boolean hasRole(PrincipalCollection principal, String roleIdentifier) {
        String username = JWTUtil.getUserName(principal.getPrimaryPrincipal().toString());

        return username.equals("huanglin") || super.hasRole(principal, roleIdentifier);
    }
}