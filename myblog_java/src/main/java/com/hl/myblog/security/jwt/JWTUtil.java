package com.hl.myblog.security.jwt;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hl.myblog.redis.RedisUtil;

/**
 * Token工具类
 * 
 * @author huanglin 
 * @date   2020年7月4日
 */

@Component
public class JWTUtil {
	private static final Logger logger = LoggerFactory.getLogger(JWTUtil.class);

    public static Long   expire        = 480000L;
	public static Long   refreshExpire = 1800000L;
	public static String secret        = "JWT_TOKEN_SHIRO"; 
    
    /**
     * 生成签名
     * 
     * @param  userName 用户名
     * @param  userId   用户id
     * @return
     */
    public static String sign(String userName, Long userId, Long currentTime) {
        try {
            Date      date       = new Date(System.currentTimeMillis() + expire);
            Algorithm algorithm  = Algorithm.HMAC256(secret);
            // 附带username信息
            return JWT.create()
                .withClaim("userName", userName)
                .withClaim("userId", userId)
                .withClaim("currentTime", currentTime)
                .withExpiresAt(date)
                .sign(algorithm);
        } catch(Exception e) {
            return null;
        }
    }

    /**
     * 校验token是否正确
     * 
     * @param  token    秘钥
     * @param  userName 是否正确
     * @return
     */
    public static boolean verify(String token, String userName, RedisUtil redisUtil) {
        if(redisUtil.hasKey(userName)) {
            Long currentTime            = JWTUtil.getCurrentTime(token);
            Long currentTimeMillisRedis = (Long)(redisUtil.get(userName));
            if(currentTime.equals(currentTimeMillisRedis)) {
                logger.info("verify token success");

                return true;
            }
        }
        logger.info("verify token fail");

        return false;
    }

    public static String getToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    /**
     * 获取用户id
     * 
     * @param  token
     * @return
     */
    public static Long getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);

            return jwt.getClaim("userId").asLong();
        } catch(JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获取用户名称
     * 
     * @param token
     * @return
     */
    public static String getUserName(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);

            return jwt.getClaim("userName").asString();
        } catch(JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获取创建token的时间节点
     * 
     * @param  token
     * @return
     */
    public static Long getCurrentTime(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);

            return jwt.getClaim("currentTime").asLong();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static Long getExpire() {
        return expire;
    }
 
    public static void setExpire(Long expire) {
        JWTUtil.expire = expire;
    }
 
    public static String getSecret() {
        return secret;
    }
 
    public static void setSecret(String secret) {
        JWTUtil.secret = secret;
    }

    public static Long getRefreshExpire() {
        return refreshExpire;
    }

    public static void setRefreshExpire(Long refreshExpire) {
        JWTUtil.refreshExpire = refreshExpire;
    }
}