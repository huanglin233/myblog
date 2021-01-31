package com.hl.myblog.vo;

import java.util.List;

import com.hl.myblog.common.utils.PrefixString;
import com.hl.myblog.po.Blog;

/**
 * 博客归档返回对象
 * 
 * @author huanglin
 * @data   2021年1月9日-下午6:17:27
 */
public class BlogArchive {
    public String     year;
    public List<Blog> blogs;

    public String toString(String prefix) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append(prefix + "year  = " + year + "\n");
        if(blogs == null ) {
            sb.append(prefix + "blogs = " + null + "\n");
        } else {
            for(Blog blog : blogs) {
                sb.append(prefix + "blogs = " + blog.toString(PrefixString.Indent1()) + "\n");
            }
        }

        return sb.toString();
    }
}