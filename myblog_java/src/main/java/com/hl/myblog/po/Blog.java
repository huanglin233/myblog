package com.hl.myblog.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hl.myblog.common.utils.PrefixString;

/**
 * 博客实体
 * 
 * @author huanglin 
 * @date   2020年2月6日
 */

public class Blog {
    private Long    id;
    private String  title;
    private String  content;
    private String  firstPicture;
    private String  flag;
    private Integer views;
    private boolean appreciation;
    private boolean shareStatement;
    private boolean commentabled;
    private boolean published;
    private boolean recomment;
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

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getFirstPicture() {
        return firstPicture;
    }
    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }
    public String getFlag() {
        return flag;
    }
    public void setFlag(String flag) {
        this.flag = flag;
    }
    public Integer getViews() {
        return views;
    }
    public void setViews(Integer views) {
        this.views = views;
    }
    public boolean isAppreciation() {
        return appreciation;
    }
    public void setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
    }
    public boolean isShareStatement() {
        return shareStatement;
    }
    public void setShareStatement(boolean shareStatement) {
        this.shareStatement = shareStatement;
    }
    public boolean isCommentabled() {
        return commentabled;
    }
    public void setCommentabled(boolean commentabled) {
        this.commentabled = commentabled;
    }
    public boolean isPublished() {
        return published;
    }
    public void setPublished(boolean published) {
        this.published = published;
    }
    public boolean isrecomment() {
        return recomment;
    }
    public void setrecomment(boolean recomment) {
        this.recomment = recomment;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public String getTagIds() {
        return tagIds;
    }
    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }
    public List<Tag> getTags() {
        return tags;
    }
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public List<Comment> getComments() {
        return comments;
    }
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
    public Long getTypeId() {
        return typeId;
    }
    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
         this.userId = userId;
    }

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