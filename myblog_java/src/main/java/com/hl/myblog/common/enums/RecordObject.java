package com.hl.myblog.common.enums;

/**
 * 记录操作操作对象
 * @author huanglin
 * @data   2020年10月22日-下午11:09:16
 */
public enum RecordObject {

    /**
     * 被操作对象
     */
    UNKNOWN("unknown"),
    USER("user"),
    BLOG("blog"),
    TAG("blogTag"),
    TYPE("blogType"),
    COMMENT("blogComment"),
    REDIS("redis"),
    FASTDFS("fastDFS"),
    NOTES("notes");

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    RecordObject(String object) {
        this.value = object;
    }
}
