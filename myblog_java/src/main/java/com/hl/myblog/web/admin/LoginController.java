package com.hl.myblog.web.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hl.myblog.annotation.AccessLimit;
import com.hl.myblog.po.User;
import com.hl.myblog.redis.RedisUtil;
import com.hl.myblog.security.jwt.JWTUtil;
import com.hl.myblog.service.impl.UserServiceImpl;
import com.hl.myblog.vo.ResponseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 后台登录
 * 
 * @author huanglin 
 * @date   2020年2月8日
 */

@RestController
@CrossOrigin
@RequestMapping("/admin")
@Api(value = "登录模块", tags = {"后台登录接口"})
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    RedisUtil redisUtil;

    @AccessLimit(seconds = 1, maxCount = 20)
    @ApiOperation(value = "用户未登录", notes = "检测到用户未登录,内部调用此接口")
    @RequestMapping(value = "noLogin", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseResult noLogin(String message) {

        return ResponseResult.error(401, "未登录,请先登录");
    }

    @AccessLimit(seconds = 1, maxCount = 20)
    @ApiOperation(value = "用户没有权限", notes = "检测到用户没有权限,内部调用此接口")
    @RequestMapping(value = "unauthorized", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseResult unauthorized() {

        return ResponseResult.error(401, "没有操作权限");
    }

    @AccessLimit(seconds = 1, maxCount = 20)
    @ApiOperation(value = "用户登录", notes = "用户登录接口")
    @ApiImplicitParam(name = "user", value = "用户信息", paramType = "query", dataType = "User")
    @PostMapping("/doLogin")
    public ResponseResult login(@RequestBody User user, HttpServletResponse response) {
        User userCheck = userServiceImpl.checkUser(user.getUserName(), user.getPassword());
        if (userCheck != null) {
            //更新登录信息
            Long   currentTimeMillis = System.currentTimeMillis();
            String token             = JWTUtil.sign(userCheck.getUserName(), userCheck.getId(), currentTimeMillis);
            if(token == null) {
                return ResponseResult.error(401, "登录失败");
            }

            try {
                redisUtil.set(userCheck.getUserName(),currentTimeMillis,JWTUtil.getRefreshExpire());
            } catch (Exception e) {
                logger.info("error = " + e.toString());
            }
            response.setHeader("Authorization", token);
            response.setHeader("Access-Control-Expose-Headers", "Authorization");

            return ResponseResult.success();
        } else {
            logger.info("login faile");

            return ResponseResult.error(401, "登录失败");
        }
    }

    @AccessLimit(seconds = 1, maxCount = 20)
    @ApiOperation(value = "用户登出", notes = "用户登出接口")
    @DeleteMapping("/loginOut")
    public ResponseResult loginOut(HttpServletRequest request) {
        String userName = JWTUtil.getUserName(JWTUtil.getToken(request));
        redisUtil.del(userName);

        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();

        return ResponseResult.success();
    }
}