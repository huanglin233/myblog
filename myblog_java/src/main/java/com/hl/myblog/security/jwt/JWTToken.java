package com.hl.myblog.security.jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Token 实体
 * 
 * @author huanglin 
 * @date   2020年7月4日
 */

public class JWTToken implements AuthenticationToken{
    private static final long serialVersionUID = 1L;
    
    // 秘钥
    private String token;

    public JWTToken(String token) {
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
}