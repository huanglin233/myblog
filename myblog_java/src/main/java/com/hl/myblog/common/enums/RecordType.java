package com.hl.myblog.common.enums;

/**
 * 操作日志记录类型
 * @author huanglin
 * @data   2020年10月22日-下午11:02:19
 */
public enum RecordType {

    /**
     * 操作记录类型
     */
    UNKNOWN("unknown"),
    SELECT("select"),
    INSERT("insert"),
    UPDATE("update"),
    DELETE("delete"),
    DOWNLOAD("download"),
    VERIFY("verify ");

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    RecordType(String type) {
        this.value = type;
    }
}