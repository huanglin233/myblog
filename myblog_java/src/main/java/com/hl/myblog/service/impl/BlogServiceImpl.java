package com.hl.myblog.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hl.myblog.annotation.RecordLog;
import com.hl.myblog.common.enums.RecordObject;
import com.hl.myblog.common.enums.RecordType;
import com.hl.myblog.common.utils.MarkdownUtils;
import com.hl.myblog.dao.BlogMapper;
import com.hl.myblog.globalHandler.exceptionHandler.NotFindException;
import com.hl.myblog.po.Blog;
import com.hl.myblog.service.BlogService;

/**
 * @author huanglin 
 * @date   2020年4月12日
 */

@Service
public class BlogServiceImpl implements BlogService{

    @Autowired
    BlogMapper blogMapper;

    @RecordLog(detail = "通过id = [{{id}}]查询博客信息", recordType = RecordType.SELECT, recordObject = RecordObject.BLOG)
    @Override
    public Blog getBlog(Long id) {
        Blog queryById = blogMapper.queryById(id);

        return queryById;
    }

    @RecordLog(detail = "通过id = [{{id}}]查询博客信息,并把博客信息转为html格式", recordType = RecordType.SELECT, recordObject = RecordObject.BLOG)
    @Transactional
    @Override
    public Blog getAndConvert(Long id) {
        Blog queryById = blogMapper.queryById(id);
        if(queryById == null) {
            throw new NotFindException();
        }
        queryById.setContent(MarkdownUtils.markdownToHtmlExtensions(queryById.getContent()));
        blogMapper.updateViews(id);

        return queryById;
    }

    @RecordLog(detail = "通过指定参数查询博客信息", recordType = RecordType.SELECT, recordObject = RecordObject.BLOG)
    @Override
    public PageInfo<Blog> getBlogList(int pageNum, int pageSize, String title, Long typeId, Long tagId, Boolean recommend) {
        PageHelper.startPage(pageNum, pageSize).setOrderBy("blog.update_time desc");
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(blogMapper.queryAll(title, typeId, tagId, recommend));

        return pageInfo;
    }

    @RecordLog(detail = "通过指定参数查询博客信息", recordType = RecordType.SELECT, recordObject = RecordObject.BLOG)
    @Override
    public PageInfo<Blog> getBlogList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize).setOrderBy("blog.update_time desc");
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(blogMapper.queryAll(null, null, null, null));

        return pageInfo;
    }

    @RecordLog(detail = "通过指定参数查询博客信息", recordType = RecordType.SELECT, recordObject = RecordObject.BLOG)
    @Override
    public PageInfo<Blog> getBlogList(Long tagId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize).setOrderBy("blog.update_time desc");
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(blogMapper.queryAll(null, null, tagId, null));

        return pageInfo;
    }

    @RecordLog(detail = "通过指定参数查询博客信息", recordType = RecordType.SELECT, recordObject = RecordObject.BLOG)
    @Override
    public PageInfo<Blog> getBlogList(String query, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize).setOrderBy("update_time desc");
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(blogMapper.searchByTitle(query));

        return pageInfo;
    }

    @RecordLog(detail = "获取前[{size}]的博客列表", recordType = RecordType.SELECT, recordObject = RecordObject.BLOG)
    @Override
    public List<Blog> ListRecommendBlogTop(Integer size) {
        PageHelper.startPage(0, size).setOrderBy("update_time desc");
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(blogMapper.queryAll(null, null, null, null));

        return pageInfo.getList();
    }

    @RecordLog(detail = "获取博客归档信息", recordType = RecordType.SELECT, recordObject = RecordObject.BLOG)
    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> queryGroupYear = blogMapper.queryGroupYear();
        Map<String, List<Blog>> map = new HashMap<>();
        for(String year : queryGroupYear) {
            map.put(year, blogMapper.queryByYear(year));
        }

        return map;
    }

    @RecordLog(detail = "统计博客数量", recordType = RecordType.SELECT, recordObject = RecordObject.BLOG)
    @Override
    public Long countBlog() {
        return blogMapper.queryCount();
    }

    @RecordLog(detail = "添加新的博客", recordType = RecordType.INSERT, recordObject = RecordObject.BLOG)
    @Override
    public int saveBlog(Blog blog) {
        int result = blogMapper.add(blog);
        if(result == 1) {
            String[] splits = blog.getTagIds().split(",");
            for(String tagId :  splits){
                result = blogMapper.addBlogWithTag(blog.getId(), Long.parseLong(tagId));
            }
        }

        return result;
    }

    @RecordLog(detail = "更新博客归档信息", recordType = RecordType.UPDATE, recordObject = RecordObject.BLOG)
    @Override
    public int updateBlog(Blog blog) {
        return blogMapper.update(blog);
    }

    @RecordLog(detail = "删除博客归档信息", recordType = RecordType.DELETE, recordObject = RecordObject.BLOG)
    @Override
    public int deleteBlog(Long id) {
        blogMapper.deleteBlogWithTag(id);

        return blogMapper.delete(id);
    }
}