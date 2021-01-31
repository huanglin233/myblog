package com.hl.myblog.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hl.myblog.po.Blog;
import com.hl.myblog.vo.BlogArchive;

/**
 * @author huanglin 
 * @date   2020年4月12日
 */

public interface BlogService {

    /**
     * 获取指定博客id的信息
     * 
     * @param  id 博客id
     * @return
     */
    Blog getBlog(Long id);

    /**
     * 博客的获取和markdown格式的转换
     * 
     * @param  id 博客id
     * @return
     */
    Blog getAndConvert(Long id);

    /**
     * 分页条件获取博客列表，条件可以都为null
     * 
     * @param  pageSize   每页显示的条数
     * @param  pageNum    显示的页数
     * @param  title      博客标题
     * @param  typeId     博客分类id
     * @param  tagId      博客标签id
     * @param  recomment  博客是否被推荐
     * @Param  published  博客是发布
     * @return
     */
    PageInfo<Blog> getBlogList(int pageNum, int pageSize, String title, Long typeId, Long tagId, Boolean recomment, Boolean published);

    /**
     * 分页获取博客列表
     * 
     * @param  pageSize 每页显示的条数
     * @param  pageNum  显示的页数
     * @return
     */
    PageInfo<Blog> getBlogList(int pageNum, int pageSize);

    /**
     * 分页获取指定博客标签下的博客列表
     * 
     * @param  tagId     博客标签id
     * @param  pageSize  每页显示的条数
     * @param  pageNum   显示的页数
     * @return
     */
    PageInfo<Blog> getBlogList(Long tagId, int pageNum, int pageSize);

    /**
     * 分页获取博客列表
     * 
     * @param  query     查询条件
     * @param  pageNum   显示的页数
     * @param  pageSize  每页显示的条数
     * @return
     */
    PageInfo<Blog> getBlogList(String query, int pageNum, int pageSize);
    
    /**
     * 获取博客评论前几的博客
     * 
     * @param  size 获取的博客数
     * @return
     */
    List<Blog> ListrecommentBlogTop(Integer size);

    /**
     * 获取博客的归档信息
     * 
     * @return
     */
    List<BlogArchive> archiveBlog();

    /**
     * 博客数统计
     * 
     * @return
     */
    Long countBlog();

    /**
     * 添加博客信息
     * 
     * @param  blog 博客信息
     * @return
     */
    int saveBlog(Blog blog);

    /**
     * 更新博客信息
     * 
     * @param  blog 博客信息
     * @return
     */
    int updateBlog(Blog blog);

    /**
     * 删除博客信息
     * 
     * @param  id 博客id
     * @return
     */
    int deleteBlog(Long id);

    /**
     * 删除所有博客中的标签
     * @param  tagId 标签id
     * @return
     */
    int deleteBlogWithTag(Long tagId);
}