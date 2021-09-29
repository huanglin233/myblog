package com.hl.myblog.po;

/**
 * 博客类型实体
 * 
 * @author huanglin 
 * @date   2020年2月6日
 */

public class Type {
	
    private Long    id;
    private String  name;
    private Long    blogsNum;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getBlogsNum() {
        return blogsNum;
    }
    public void setBlogsNum(Long blogsNum) {
        this.blogsNum = blogsNum;
    }

    public String toString(String prefix) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append(prefix + "id       = " + id    + "\n");
        sb.append(prefix + "name     = " + name  + "\n");
        sb.append(prefix + "blogsNum = " + blogsNum + "\n");

        return sb.toString();
    }
}