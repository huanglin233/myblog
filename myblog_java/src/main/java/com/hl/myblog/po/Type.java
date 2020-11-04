package com.hl.myblog.po;

import java.util.ArrayList;
import java.util.List;

import com.hl.myblog.common.utils.PrefixString;

/**
 * 博客类型实体
 * 
 * @author huanglin 
 * @date   2020年2月6日
 */

public class Type {
    private Long   id;
    private String name;

    private List<Blog> blogs = new ArrayList<Blog>();

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
    public List<Blog> getBlogs() {
        return blogs;
    }
    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public String toString(String prefix) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append(prefix + "id    = " + id    + "\n");
        sb.append(prefix + "name  = " + name  + "\n");

        String newPrefix = PrefixString.Indent(prefix);
        if(blogs == null) {
            sb.append(prefix + "blogs = null" + "\n");
        } else {
            for(Blog blog : blogs){
                sb.append(prefix + "blogs = " + blog.toString(newPrefix));	
            }
        }

        return sb.toString();
    }
}