package com.hl.myblog.po;

import com.github.pagehelper.PageInfo;
import com.hl.myblog.common.utils.PrefixString;

/**
 * 标签类实体
 * 
 * @author huanglin 
 * @date   2020年2月6日
 */

public class Tag {
    private Long   id;
    private String name;

    private PageInfo<Blog> blogs = new PageInfo<Blog>();

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
    public PageInfo<Blog> getBlogs() {
        return blogs;
    }
    public void setBlogs(PageInfo<Blog> blogs) {
        this.blogs = blogs;
    }

    public String toString(String prefix) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append(prefix + "id    = "   + id        + "\n");
        sb.append(prefix + "name  = "   + name      + "\n");

        String newPrefix = PrefixString.Indent(prefix);
        if(blogs == null) {
            sb.append(prefix + "blogs = null" + "\n");
        } else {
            for(Blog blog : blogs.getList()){
                sb.append(prefix + "blogs = " + blog.toString(newPrefix));	
            }
        }

        return sb.toString();
    }
}