package com.hl.myblog.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hl.myblog.annotation.RecordLog;
import com.hl.myblog.common.enums.RecordObject;
import com.hl.myblog.common.enums.RecordType;
import com.hl.myblog.dao.CommentMapper;
import com.hl.myblog.po.Comment;
import com.hl.myblog.service.CommentService;

/**
 * @author huanglin 
 * @date   2020年5月1日
 */

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentMapper commentMapper;

    @RecordLog(detail = "通过博客Id=[[${blogId}]]", recordType = RecordType.SELECT, recordObject = RecordObject.COMMENT)
    @Override
    public List<Comment> getListComment(Long blogId) {
        List<Comment> comments = commentMapper.queryByBlogId(blogId);

        return eachComment(comments);
    };

    @RecordLog(detail = "添加博客评论", recordType = RecordType.INSERT, recordObject = RecordObject.COMMENT)
    @Override
    public int saveComment(Comment comment) {
        Comment parentComment = comment.getParentComment();
        if(parentComment != null && parentComment.getId() != -1) {
            comment.setParentComment(commentMapper.queryById(comment.getId()));
        } else {
           comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());

        return commentMapper.add(comment);
    };

    /**
     *  循环每个顶级的评论节点
     */
    private List<Comment> eachComment(List<Comment> comments) {
        List<Comment> commentView = new ArrayList<>();
        for(Comment comment : comments) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment, c);
            commentView.add(c);
        }
        //合并评论的各个层次到一级子代集合
        combineChildren(comments);

        return comments;
    }

    /**
     * 合并顶级评论中的所有子节点
     */
    private void combineChildren(List<Comment> comments) {
        for(Comment comment : comments) {
            List<Comment> relys = comment.getReplyComments();
            for(Comment rely : relys) {
                recursively(rely);
            }
            comment.setReplyComments(tempReplys);
            tempReplys = new ArrayList<Comment>();
        }
    }

    /**
     * 循环出所有的子节点
     */
    private List<Comment> tempReplys = new ArrayList<Comment>();
    private void recursively(Comment comment) {
        tempReplys.add(comment); //顶级点添加到临时存放集合
        if(comment.getReplyComments().size() > 0) {
            List<Comment> replys = comment.getReplyComments();
            for(Comment reply : replys) {
                tempReplys.add(reply);
                if(reply.getReplyComments().size() > 0) {
                    recursively(reply);
                }
            }
        }
    }
}