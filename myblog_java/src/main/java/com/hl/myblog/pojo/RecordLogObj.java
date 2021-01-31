package com.hl.myblog.pojo;

import java.util.Date;

/**
 * 记录日志的对象
 * @author huanglin
 * @data   2020年10月22日-下午11:23:19
 */
public class RecordLogObj {

    /**
     * 记录时间
     */
    private Date createTime;

    /**
     * 被操作的对象
     */
     private String recoredObject;

    /**
     * 方法名
     */
    private String method;

    /**
     * 参数
     */
    private String args;

    /**
     * 操作人
     */
    private String userName;

    /**
     * 日志描述
     */
    private String describe;

    /**
     * 日志记录类型
     */
    private String recordType;

    /**
     * 方法运行时间
     */
    public Long runTime;

    /**
     * 方法返回值
     */
    private String returnValue;

    public String toString(String prefix) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append(prefix + "createTime   = " + createTime    + "\n");
        sb.append(prefix + "recordType   = " + recordType    + "\n");
        sb.append(prefix + "recordObject = " + recoredObject + "\n");
        sb.append(prefix + "describe     = " + describe      + "\n");
        sb.append(prefix + "method       = " + method        + "\n");
        sb.append(prefix + "args         = " + args          + "\n");
        sb.append(prefix + "userName     = " + userName      + "\n");
        sb.append(prefix + "runTime      = " + runTime       + "\n");
        sb.append(prefix + "returnValue  = " + returnValue   + "\n");

        return sb.toString();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRecoredObject() {
        return recoredObject;
    }

    public void setRecoredObject(String recoredObject) {
        this.recoredObject = recoredObject;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public Long getRunTime() {
        return runTime;
    }

    public void setRunTime(Long runTime) {
        this.runTime = runTime;
    }

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }
}