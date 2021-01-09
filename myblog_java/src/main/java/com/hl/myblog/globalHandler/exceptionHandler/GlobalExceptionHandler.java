package com.hl.myblog.globalHandler.exceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.hl.myblog.common.constants.HttpStatus;
import com.hl.myblog.vo.ResponseResult;

/**
 * 全局异常处理
 * 
 * @author huanglin
 * @data   2020年7月11日-下午12:05:46
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * 捕捉所有Shiro异常
     */
    @ExceptionHandler(ShiroException.class)
    public ResponseResult handle401(ShiroException e) {
        return new ResponseResult(HttpStatus.UNAUTHORIZED, "无权访问(Unauthorized):" + e.getMessage());
    }

    /**
     * 单独捕捉Shiro(UnauthorizedException)异常 该异常为访问有权限管控的请求而该用户没有所需权限所抛出的异常
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseResult handle401(UnauthorizedException e) {
        return new ResponseResult(HttpStatus.UNAUTHORIZED, "无权访问(Unauthorized):当前Subject没有此请求所需权限(" + e.getMessage() + ")");
    }

    /**
     * 单独捕捉Shiro(UnauthenticatedException)异常
     * 该异常为以游客身份访问有权限管控的请求无法对匿名主体进行授权，而授权失败所抛出的异常
     */
    @ExceptionHandler(UnauthenticatedException.class)
    public ResponseResult handle401(UnauthenticatedException e) {
        return new ResponseResult(HttpStatus.UNAUTHORIZED, "无权访问(Unauthorized):当前Subject是匿名Subject，请先登录(This subject is anonymous.)");
    }

    /**
     * 捕捉校验异常(BindException)
     */
    @ExceptionHandler(BindException.class)
    public ResponseResult validException(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        Map<String, Object> error = this.getValidError(fieldErrors);
        return new ResponseResult(HttpStatus.BAD_REQUEST, error.get("errorMsg").toString(), error.get("errorList"));
    }


    /**
     * 捕捉404异常
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseResult handle(NoHandlerFoundException e) {
        return new ResponseResult(HttpStatus.NOT_FOUND, e.getMessage());
    }
    
    /**
     * 捕获查找数据为null的异常
     */
    @ExceptionHandler(NotFindException.class)
    public ResponseResult notFind(NotFindException e) {
		return ResponseResult.success(null);
    }

    /**
     * 捕捉其他所有异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult globalException(HttpServletRequest request, Throwable e) {
        return new ResponseResult(HttpStatus.ERROR, e.toString() + ": " + e.getMessage());
    }

    @ExceptionHandler(AccessLimitException.class)
    public ResponseResult accessLimitException(AccessLimitException e) {
        return new ResponseResult(HttpStatus.ACCESS_LIMIT, e.msg);
    }

    /**
     * 获取校验错误信息
     */
    private Map<String, Object> getValidError(List<FieldError> fieldErrors) {
        Map<String, Object> map = new HashMap<String, Object>(16);
        List<String> errorList = new ArrayList<String>();
        StringBuffer errorMsg = new StringBuffer("校验异常(ValidException):");
        for (FieldError error : fieldErrors) {
            errorList.add(error.getField() + "-" + error.getDefaultMessage());
            errorMsg.append(error.getField() + "-" + error.getDefaultMessage() + ".");
        }
        map.put("errorList", errorList);
        map.put("errorMsg", errorMsg);
        return map;
    }
}