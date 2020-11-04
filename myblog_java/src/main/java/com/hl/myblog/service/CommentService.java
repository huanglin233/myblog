package com.hl.myblog.service;

import java.util.List;

import com.hl.myblog.po.Comment;

/**
 * @author huanglin 
 * @date   2020年5月1日
 */

public interface CommentService {

    /**
     * 获取博客的评论
     * 
     * @param  blogId 博客id
     * @return 
     */
    List<Comment> getListComment(Long blogId);

    /**
     * 保存评论
     * 
     * @param  int 保存结果
     * @return
     */
    int saveComment(Comment comment);
}