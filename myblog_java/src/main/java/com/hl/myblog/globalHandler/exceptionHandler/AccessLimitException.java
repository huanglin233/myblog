package com.hl.myblog.globalHandler.exceptionHandler;

/**
 * 频繁访问异常
 * 
 * @author huanglin
 * @data   2021年1月8日-下午11:12:32
 */
public class AccessLimitException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public String msg;

    public AccessLimitException(String msg) {
        this.msg = msg;
    }
}
