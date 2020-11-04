package com.hl.myblog.security.jwt;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.hl.myblog.annotation.RecordLog;
import com.hl.myblog.common.enums.RecordObject;
import com.hl.myblog.common.enums.RecordType;
import com.hl.myblog.redis.RedisUtil;

/**
 * jwt 过滤器
 * 
 * @author huanglin
 * @date   2020年7月8日
 */
public class JWTFilter extends BasicHttpAuthenticationFilter{
    private static final Logger logger = LoggerFactory.getLogger(JWTFilter.class);

    @Autowired
    RedisUtil redisUtil;

    /**
     *  如果带有token,则对token进行检查,否则直接通过
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        // 判断请求的请求头是否带上了 "Token"
        if(isLoginAttempt(request, response)) {
            // 如果存在,则进入executLogin 方法进行登入,检查token是否正确.
            try {
                return executeLogin(request, response);
            } catch (Exception e) {
                // token 错误
                Throwable throwable = e.getCause();
                if (throwable != null && throwable instanceof TokenExpiredException){
                    if (refreshToken(request, response)) {

                        return true;
                    }else {
                        responseError(response, e.getMessage());

                        return false;
                    }
                }
                responseError(response, e.getMessage());
                // 产生异常阻止请求的继续执行
                return false;
            }
        }
        // 如果请求头不存在 Token，则可能是执行登陆操作或者是游客状态访问，无需检查 token，直接返回 true
        return true;
    }

    /**
     * 判断用户是否想登录
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req   = (HttpServletRequest)request;
        String             token = req.getHeader("Authorization");

        return token != null;
    }

    /**
     *  执行登录操作
     */
    @RecordLog(detail = "通过token[{{(HttpServletRequest)request.getHeader(\"Authorization\")}}]进行验证", recordType = RecordType.VERIFY, recordObject = RecordObject.USER)
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req      = (HttpServletRequest)request;
        String             token    = req.getHeader("Authorization");
        JWTToken           jwtToken = new JWTToken(token);

        // 提交给realm进行登入,如果错误他会抛出异常并捕获
        getSubject(request, response).login(jwtToken);
        // 如果登录失败会抛出异常

        return true;
    }

    /**
     * 创建shiro token
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String jwtToken = ((HttpServletRequest)request).getHeader("Authorization");
        if(jwtToken != null) {
            logger.info("create a token = {}", jwtToken);

            return new JWTToken(jwtToken);
        }

        return null;
    }

    /**
     * isAccessAllowed为false时调用, 验证失败
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        logger.info("token verify fail");

        this.sendChallenge(request, response);

        return false;
    }

    /**
     * shiro验证成功调用
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        return true;
    }

    /*
     * 对跨域提供支持
     */
    /*
	 * @Override protected boolean preHandle(ServletRequest request, ServletResponse
	 * response) throws Exception { HttpServletRequest httpServletRequest=
	 * (HttpServletRequest) request; HttpServletResponse httpServletResponse=
	 * (HttpServletResponse) response;
	 * httpServletResponse.setHeader("Access-Control-Allow-Origin",
	 * httpServletRequest.getHeader("Origin"));
	 * httpServletResponse.setHeader("Access-Control-Allow-Methods",
	 * "GET,POST,OPTIONS,PUT,DELETE");
	 * httpServletResponse.setHeader("Access-Control-Allow-Headers",
	 * httpServletRequest.getHeader("Access-Control-Resquest-Headers"));
	 * httpServletResponse.setHeader("Access-Control-Expose-Headers",
	 * "Authorization");
	 * 
	 * if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())){
	 * httpServletResponse.setStatus(HttpStatus.OK.value()); }
	 * 
	 * //如果不带token，不去验证shiro if (!isLoginAttempt(request,response)){
	 * responseError(httpServletResponse,"no token"); return false; }
	 * 
	 * return super.preHandle(request,response); }
	 */

    /**
     * 请求异常跳转
     */
    private void responseError(ServletResponse  response, String message) {
        try {
            HttpServletResponse rep = (HttpServletResponse) response;
            rep.addHeader("Access-Control-Allow-Origin", "*");
            // 设置编码,否则中文字符在重定向时会变为空字符串
            message = URLEncoder.encode(message, "UTF-8");
            rep.sendRedirect("/amdin/noLogin?message=" + message);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 刷新AccessToken，进行判断RefreshToken是否过期，未过期就返回新的AccessToken且继续正常访问
     * 
     * @param  request
     * @param  response
     * @return
     */
    @RecordLog(detail = "刷新AccessToken", recordType = RecordType.UPDATE, recordObject = RecordObject.REDIS)
    private boolean refreshToken(ServletRequest request, ServletResponse response) {
        String token    = ((HttpServletRequest)request).getHeader("Authorization");
        String userName = JWTUtil.getUserName(token);
        // 判断Redis中RefreshToken是否存在
        if(redisUtil.hasKey(userName)) {
            // Redis中RefreshToken还存在,获取RefreshToken的时间戳
            Long currentTimeMillisRedis = (Long)redisUtil.get(userName);
            // 用当前的ccessToken中的时间戳，与RefreshToken的时间戳对比，如果当前时间戳一致，进行AccessToken刷新
            Long currentTime = JWTUtil.getCurrentTime(token);
            if(currentTimeMillisRedis.equals(currentTime)) {
                // 获取当前最新时间戳
                Long currentTimeMillis = System.currentTimeMillis();redisUtil.set(userName, currentTimeMillis, JWTUtil.getRefreshExpire());
                // 刷新AccessToken，设置时间戳为当前最新时间戳
                Long userId = JWTUtil.getUserId(token);
                token = JWTUtil.sign(userName, userId, currentTimeMillis);
                HttpServletResponse rep = (HttpServletResponse)response;
                rep.setHeader("Authorization", token);
                rep.setHeader("Access-Control-Expose-Headers", "Authorization");

                logger.info("\n" + "refreshToken a token success");

                return true;
            }
        }

        return false;
    }
}