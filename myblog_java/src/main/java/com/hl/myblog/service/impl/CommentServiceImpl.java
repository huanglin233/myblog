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

    @RecordLog(detail = "通过博客Id=[{{blogId}}]", recordType = RecordType.SELECT, recordObject = RecordObject.COMMENT)
    @Override
    public List<Comment> getListComment(Long blogId) {
        List<Comment> comments = commentMapper.queryByBlogId(blogId);

        return eachComment(comments);
    };

    @RecordLog(detail = "添加博客评论", recordType = RecordType.INSERT, recordObject = RecordObject.COMMENT)
    @Override
    public int saveComment(Comment comment) {
        comment.setCreateTime(new Date());

        return commentMapper.add(comment);
    };

    /**
     *  循环出每个顶级的评论节点
     */
    private List<Comment> eachComment(List<Comment> comments) {
        List<Comment> commentTopOne = new ArrayList<Comment>();
        List<Comment> delComments   = new ArrayList<Comment>();
        for(Comment comment : comments) {
            Comment c = new Comment();
            if(comment.getParentCommentId() == null) {
                BeanUtils.copyProperties(comment, c);
                commentTopOne.add(c);
                delComments.add(comment);
            }
        }
        for(Comment delComment : delComments) {
            comments.remove(delComment);
        }
        //合并评论的各个层次到一级子代集合
        combineChildren(commentTopOne, comments);

        return commentTopOne;
    }

    /**
     * 合并顶级评论中的所有子节点
     */
    private void combineChildren(List<Comment> commentTopOne, List<Comment> comments) {
        for(Comment comment : commentTopOne) {
            List<Comment> replyComments = new ArrayList<Comment>();
            List<Comment> delComments   = new ArrayList<Comment>();
            for(Comment childComment : comments) {
                if(comment.getId() == childComment.getParentCommentId()) {
                    Comment c = new Comment();
                    BeanUtils.copyProperties(childComment, c);
                    recursively(c, comments);
                    c.setParentComment(comment.getNickname());
                    replyComments.add(c);
                    delComments.add(childComment);
                }
            }
            for(Comment delComment : delComments) {
                comments.remove(delComment);
            }
            comment.setReplyComments(replyComments);
        }
    }

    /**
     * 循环出所有的子节点
     */
    private void recursively(Comment comment, List<Comment> comments) {
        List<Comment> replyComments = new ArrayList<Comment>();
        List<Comment> delComments   = new ArrayList<Comment>();
        for(Comment commentChild : comments) {
            if(comment.getId() == commentChild.getParentCommentId()) {
                Comment c = new Comment();
                BeanUtils.copyProperties(commentChild, c);
                recursively(c, comments);
                c.setParentComment(comment.getNickname());
                replyComments.add(c);
                delComments.add(commentChild);
            }
        }
        for(Comment delComment : replyComments) {
            comments.remove(delComment);
        }
        comment.setReplyComments(replyComments);
    }
}