package com.hl.myblog.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hl.myblog.common.utils.PrefixString;
import lombok.Data;

/**
 * 用户实体
 * 
 * @author huanglin 
 * @date   2020年2月6日
 */

@Data
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