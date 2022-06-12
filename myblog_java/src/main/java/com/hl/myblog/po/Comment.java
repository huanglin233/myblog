package com.hl.myblog.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hl.myblog.common.utils.PrefixString;
import lombok.Data;

/**
 * 评论实体
 * 
 * @author huanglin 
 * @date   2020年2月6日
 */

@Data
public class Comment {
	
    private Long    id;
    private Long    blogId;
    private Long    parentCommentId;
    private String  nickname;
    private String  email;
    private String  content;
    private String  avatar;
    private Date    createTime;
    private boolean adminComment;

    private Blog   blog;
    private String parentComment;
    private List<Comment> replyComments = new ArrayList<Comment>();

    public String toString(String prefix) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append(prefix + "id              = " + id              + "\n");
        sb.append(prefix + "nickname        = " + nickname        + "\n");
        sb.append(prefix + "email           = " + email           + "\n");
        sb.append(prefix + "content         = " + content         + "\n");
        sb.append(prefix + "avatar          = " + avatar          + "\n");
        sb.append(prefix + "createTime      = " + createTime      + "\n");
        sb.append(prefix + "blogId          = " + blogId          + "\n");
        sb.append(prefix + "parentCommentId = " + parentCommentId + "\n");
        sb.append(prefix + "parentComment   = " + parentComment   + "\n");

        String newPrefix = PrefixString.Indent(prefix);
        if(blog == null) {
            sb.append(prefix + "blog            = null");
        } else {
            sb.append(prefix + "blog            = " + blog.toString(newPrefix));
        }
        if(replyComments == null) {
            sb.append(prefix + "replyComments   = null");
        } else {
            for(Comment  comment : replyComments) {
                sb.append(prefix + "replyComments   = " + comment.toString(newPrefix));	
            }
        }

        return sb.toString();
    }
}