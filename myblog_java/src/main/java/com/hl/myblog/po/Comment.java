package com.hl.myblog.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hl.myblog.common.utils.PrefixString;

/**
 * 评论实体
 * 
 * @author huanglin 
 * @date   2020年2月6日
 */

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

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getBlogId() {
        return blogId;
    }
    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }
    public Long getParentCommentId() {
        return parentCommentId;
    }
    public void setParentCommentId(Long parentCommentId) {
        this.parentCommentId = parentCommentId;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public boolean isAdminComment() {
        return adminComment;
    }
    public void setAdminComment(boolean adminComment) {
        this.adminComment = adminComment;
    }
    public Blog getBlog() {
        return blog;
    }
    public void setBlog(Blog blog) {
        this.blog = blog;
    }
    public List<Comment> getReplyComments() {
        return replyComments;
    }
    public void setReplyComments(List<Comment> replyComments) {
        this.replyComments = replyComments;
    }
    public String getParentComment() {
        return parentComment;
    }
    public void setParentComment(String parentComment) {
        this.parentComment = parentComment;
    }

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