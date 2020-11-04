package com.hl.myblog.dao;

import java.util.List;

import com.hl.myblog.po.Comment;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author huanglin 
 * @date   2020年5月1日
 */

@Mapper
public interface CommentMapper {
    /**
     * 获取评论信息及所有的上级信息
     * 
     * @param   id 评论id
     * @return
     */
    public List<Comment> queryByBlogId(@Param("blogId") long blogId);

    /**
     * 根据评论id获取评论信息
     * 
     * @param id
     * @return
     */
    public Comment queryById(@Param("id") long id);

    /**
     * 添加一个评论信息
     * 
     * @param comment
     * @return
     */
    public int add(@Param("comment") Comment comment);

    /**
     * 删除一个评论信息
     * 
     * @param id
     * @return
     */
    public int delete(@Param("id") long id);
}