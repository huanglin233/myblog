package com.hl.myblog.dao;

import java.util.List;

import com.hl.myblog.po.Blog;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author huanglin 
 * @date   2020年4月12日
 */
@Mapper
public interface BlogMapper {
     /**
     * 根据条件查询的博客信息，如果所有条件信息为空就查询所有的博客信息
     * 
     * @param  title     博客的标题
     * @param  typeId    博客分类ID
     * @param  tagId     博客标签ID
     * @param  recommend 博客是否为推荐
     * @return
     */
    public List<Blog> queryAll(@Param("title") String title, @Param("typeId") Long typeId, @Param("tagId") Long tagId, @Param("recommend") Boolean recommend, @Param("published") Boolean published);

    /**
     * 根据博客ID查询博客信息
     * 
     * @param  id 博客ID
     * @return
     */
    public Blog queryById(@Param("id") long id);

    /**
     * 根据博客id查询博客信息
     * 
     * @param title
     * @return
     */
    public List<Blog> searchByTitle(@Param("title") String title);

    /**
     * 查询所有博客发布的年份
     * 
     * @return
     */
    public List<String> queryGroupYear();

    /**
     * 根据年份查询的博客信息
     * 
     * @param   year 年份
     * @return
     */
    public List<Blog> queryByYear(String year);

    /**
     * 统计发布的博客数量
     * 
     * @return
     */
    public long queryCount();

    /**
     * 添加博客
     * 
     * @param  blog 添加的博客信息
     * @return
     */
    public int add(Blog blog);

    /**
     * 添加博客与标签的关联记录
     * 
     * @param  blogId 博客id
     * @param  tagId  标签id
     * @return
     */
    public int addBlogWithTag(@Param("blogId") long blogId, @Param("tagId") long tagId);

    /**
     * 更新博客与标签的关联记录
     * @param  blogId 博客id
     * @param  tagId  标签id
     * @return
     */
    public int updateBlogWithTag(@Param("blogId") long blogId, @Param("tagId") long tagId);

    /**
     * 删除博客和标签的关联记录
     * 
     * @param  tagId  标签ID
     * @return
     */
    public int deleteBlogWithTag(@Param("tagId") long tagId);
    /**
     * 更新博客的浏览量
     * 
     * @param id
     * @return
     */
    public int updateViews(@Param("id") long id);

    /**
     * 更新博客信息
     * 
     * @param   blog 更新的博客信息
     * @return
     */
    public int update(@Param("blog") Blog blog);

    /**
     * 根据博客ID删除博客
     * 
     * @param id
     * @return
     */
    public int delete(@Param("id") long id);
}