package com.hl.myblog.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hl.myblog.common.utils.PrefixString;

/**
 * 用户实体
 * 
 * @author huanglin 
 * @date   2020年2月6日
 */

public class User {
    private Long    id;
    private String  nickname;
    private String  userName;
    private String  password;
    private String  email;
    private String  avatar;
    private Integer type;
    private Date    crateTime;
    private Date    updateTime;

    private List<Blog> blogs = new ArrayList<Blog>();

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public Date getCrateTime() {
        return crateTime;
    }
    public void setCrateTime(Date crateTime) {
        this.crateTime = crateTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
        sb.append(prefix + "id         = " + id         + "\n");
        sb.append(prefix + "nickname   = " + nickname   + "\n");
        sb.append(prefix + "userName   = " + userName   + "\n");
        sb.append(prefix + "password   = " + password   + "\n");
        sb.append(prefix + "email      = " + email      + "\n");
        sb.append(prefix + "avatar     = " + avatar     + "\n");
        sb.append(prefix + "type       = " + type       + "\n");
        sb.append(prefix + "crateTime  = " + crateTime  + "\n");
        sb.append(prefix + "updateTime = " + updateTime + "\n");

        String newPrefix = PrefixString.Indent(prefix);
        if(blogs == null) {
            sb.append(prefix + "blogs      = null" + "\n");
        } else {
            for(Blog blog : blogs){
                sb.append(prefix + "blogs      = " + blog.toString(newPrefix));
            }
        }

        return sb.toString();
    }
}