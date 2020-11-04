package com.hl.myblog.vo;

import java.util.HashMap;

import org.springframework.util.StringUtils;

import com.hl.myblog.common.constants.HttpStatus;

/**
 * 操作消息提醒
 * 
 * @author huanglin 
 * @date   2020年2月6日
 */

public class ResponseResult extends HashMap<String, Object>
{
    private static final long serialVersionUID = 1L;

    /** 状态码 */
    public static final String CODE_TAG = "code";

    /** 返回内容 */
    public static final String MSG_TAG = "msg";

    /** 数据对象 */
    public static final String DATA_TAG = "data";

    /**
     * 初始化一个新创建的 ResponseResult 对象，使其表示一个空消息。
     */
    public ResponseResult(){}

    /**
     * 初始化一个新创建的 ResponseResult 对象
     * 
     * @param code 状态码
     * @param msg 返回内容
     */
    public ResponseResult(int code, String msg){
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    /**
     * 初始化一个新创建的 ResponseResult 对象
     * 
     * @param code 状态码
     * @param msg 返回内容
     * @param data 数据对象
     */
    public ResponseResult(int code, String msg, Object data){
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (!StringUtils.isEmpty(data)){
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 返回成功消息
     * 
     * @return 成功消息
     */
    public static ResponseResult success(){
        return ResponseResult.success("操作成功");
    }

    /**
     * 返回成功数据
     * 
     * @return 成功消息
     */
    public static ResponseResult success(Object data){
        return ResponseResult.success("操作成功", data);
    }

    /**
     * 返回成功消息
     * 
     * @param msg 返回内容
     * @return 成功消息
     */
    public static ResponseResult success(String msg){
        return ResponseResult.success(msg, null);
    }

    /**
     * 返回成功消息
     * 
     * @param msg 返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static ResponseResult success(String msg, Object data){
        return new ResponseResult(HttpStatus.SUCCESS, msg, data);
    }

    /**
     * 返回错误消息
     * 
     * @return
     */
    public static ResponseResult error(){
        return ResponseResult.error("操作失败");
    }

    /**
     * 返回错误消息
     * 
     * @param msg 返回内容
     * @return 警告消息
     */
    public static ResponseResult error(String msg){
        return ResponseResult.error(msg, null);
    }

    /**
     * 返回错误消息
     * 
     * @param msg 返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static ResponseResult error(String msg, Object data){
        return new ResponseResult(HttpStatus.ERROR, msg, data);
    }

    /**
     * 返回错误消息
     * 
     * @param code 状态码
     * @param msg 返回内容
     * @return 警告消息
     */
    public static ResponseResult error(int code, String msg){
        return new ResponseResult(code, msg, null);
    }
}