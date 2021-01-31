package com.hl.myblog.globalHandler.exceptionHandler;

/**
 * 查询数据为空抛出此异常
 * 
 * @author huanglin
 * @data   2020年7月11日-下午12:05:46
 */
public class NotFindException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public NotFindException() {};
}