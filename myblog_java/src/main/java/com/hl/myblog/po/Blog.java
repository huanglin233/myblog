package com.hl.myblog.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hl.myblog.common.utils.PrefixString;
import lombok.Data;

/**
 * 博客实体
 * 
 * @author huanglin 
 * @date   2020年2月6日
 */

@Data
public class Blog {
	
    private Long    id;
    private String  title;
    private String  content;
    private String  firstPicture;
    private String  flag;
    private Integer views;
    private Boolean appreciation;
    private Boolean shareStatement;
    private Boolean commentabled;
    private Boolean published;
    private Boolean recomment;
    private Date    createTime;
    private Date    updateTime;
    private String  tagIds;
    private String  description;
    private Long    typeId;
    private Long    userId;

    private Type          type;
    private User          user;
    private List<Tag>     tags     = new ArrayList<Tag>();
    private List<Comment> comments = new ArrayList<Comment>();

    public String toString(String prefix) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append(prefix + "id             = " + id             + "\n");
        sb.append(prefix + "title          = " + title          + "\n");
        sb.append(prefix + "content        = " + content        + "\n");
        sb.append(prefix + "firstPicture   = " + firstPicture   + "\n");
        sb.append(prefix + "flag           = " + flag           + "\n");
        sb.append(prefix + "views          = " + views          + "\n");
        sb.append(prefix + "appreciation   = " + appreciation   + "\n");
        sb.append(prefix + "shareStatement = " + shareStatement + "\n");
        sb.append(prefix + "commentabled   = " + commentabled   + "\n");
        sb.append(prefix + "published      = " + published      + "\n");
        sb.append(prefix + "recomment      = " + recomment      + "\n");
        sb.append(prefix + "createTime     = " + createTime     + "\n");
        sb.append(prefix + "updateTime     = " + updateTime     + "\n");
        sb.append(prefix + "tagIds         = " + tagIds         + "\n");

        String newPrefix = PrefixString.Indent(prefix);
        if(type == null) {
            sb.append(prefix + "type           = null" + "\n");
        } else {
            sb.append(prefix + "type           = " + type.toString(newPrefix));
        }
        if(user == null) {
            sb.append(prefix + "user           = null" + "\n");
        } else {
            sb.append(prefix + "user           = " + user.toString(newPrefix));
        }
        if(tags == null) {
            sb.append(prefix + "tags           = null" + "\n");
        } else {
            for(Tag tag : tags) {
                sb.append(prefix + "tags           = " + tag.toString(newPrefix));
            }
        } 
        if(comments == null) {
            sb.append(prefix + "comments       = null" + "\n");
        } else {
            for(Comment comment : comments) {
                sb.append(prefix + "comments       = " + comment.toString(newPrefix));
            }
        } 

        return sb.toString();
    }
}