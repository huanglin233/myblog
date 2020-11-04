package com.hl.myblog.vo;

/**
 * 博客查询条件
 * 
 * @author huanglin 
 * @date   2020年2月6日
 */

public class BlogQuery {
    public String  title;
    public Long    typeId;
    public Long    tagId;
    public Boolean recommend;

    public String toString(String prefix) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append(prefix + "title     = " + title  + "\n");
        sb.append(prefix + "typeId    = " + typeId + "\n");
        sb.append(prefix + "tagId     = " + tagId  + "\n");
        sb.append(prefix + "recommend = " + recommend  + "\n");

        return sb.toString();
    }
}