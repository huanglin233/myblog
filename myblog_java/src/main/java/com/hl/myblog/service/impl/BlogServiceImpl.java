package com.hl.myblog.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hl.myblog.annotation.RecordLog;
import com.hl.myblog.common.enums.RecordObject;
import com.hl.myblog.common.enums.RecordType;
import com.hl.myblog.common.utils.MarkdownUtils;
import com.hl.myblog.dao.BlogMapper;
import com.hl.myblog.globalHandler.exceptionHandler.NotFindException;
import com.hl.myblog.po.Blog;
import com.hl.myblog.po.Tag;
import com.hl.myblog.service.BlogService;

/**
 * @author huanglin 
 * @date   2020年4月12日
 */

@Service
public class BlogServiceImpl implements BlogService{

    @Autowired
    BlogMapper blogMapper;
    @Autowired
    TagServiceImpl TagServiceImpl;

    @RecordLog(detail = "通过id = [{{id}}]查询博客信息", recordType = RecordType.SELECT, recordObject = RecordObject.BLOG)
    @Override
    public Blog getBlog(Long id) {
        Blog queryById = blogMapper.queryById(id);
        queryBlogTagIds(queryById);

        return queryById;
    }

    @RecordLog(detail = "通过id = [{{id}}]查询博客信息,并把博客信息转为html格式", recordType = RecordType.SELECT, recordObject = RecordObject.BLOG)
    @Override
    public Blog getAndConvert(Long id) {
        Blog queryById = blogMapper.queryById(id);
        if(queryById == null) {
            throw new NotFindException();
        }
        queryById.setContent(MarkdownUtils.markdownToHtmlExtensions(queryById.getContent()));
        blogMapper.updateViews(id);
        queryBlogTagIds(queryById);

        return queryById;
    }

    @RecordLog(detail = "通过指定参数查询博客信息", recordType = RecordType.SELECT, recordObject = RecordObject.BLOG)
    @Override
    public PageInfo<Blog> getBlogList(int pageNum, int pageSize, String title, Long typeId, Long tagId, Boolean recomment, Boolean published) {
        PageHelper.startPage(pageNum, pageSize).setOrderBy("blog.update_time desc");
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(blogMapper.queryAll(title, typeId, tagId, recomment, published));
        for(Blog blog : pageInfo.getList()) {
            queryBlogTagIds(blog);
        }

        return pageInfo;
    }

    @RecordLog(detail = "通过指定参数查询博客信息", recordType = RecordType.SELECT, recordObject = RecordObject.BLOG)
    @Override
    public PageInfo<Blog> getBlogList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize).setOrderBy("blog.update_time desc");
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(blogMapper.queryAll(null, null, null, null, null));
        for(Blog blog : pageInfo.getList()) {
            queryBlogTagIds(blog);
        }

        return pageInfo;
    }

    @RecordLog(detail = "通过指定参数查询博客信息", recordType = RecordType.SELECT, recordObject = RecordObject.BLOG)
    @Override
    public PageInfo<Blog> getBlogList(Long tagId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize).setOrderBy("blog.update_time desc");
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(blogMapper.queryAll(null, null, tagId, null, null));
        for(Blog blog : pageInfo.getList()) {
            queryBlogTagIds(blog);
        }

        return pageInfo;
    }

    @RecordLog(detail = "通过指定参数查询博客信息", recordType = RecordType.SELECT, recordObject = RecordObject.BLOG)
    @Override
    public PageInfo<Blog> getBlogList(String query, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize).setOrderBy("blog.update_time desc");
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(blogMapper.searchByTitle(query));
        for(Blog blog : pageInfo.getList()) {
            queryBlogTagIds(blog);
        }

        return pageInfo;
    }

    @RecordLog(detail = "获取前[{{size}}]的博客列表", recordType = RecordType.SELECT, recordObject = RecordObject.BLOG)
    @Override
    public List<Blog> ListrecommentBlogTop(Integer size) {
        PageHelper.startPage(0, size).setOrderBy("blog.update_time desc");
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(blogMapper.queryAll(null, null, null, true, true));
        for(Blog blog : pageInfo.getList()) {
            queryBlogTagIds(blog);
        }
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

    @RecordLog(detail = "更新博客信息", recordType = RecordType.UPDATE, recordObject = RecordObject.BLOG)
    @Override
    public int updateBlog(Blog blog) {
        Blog       queryById  = blogMapper.queryById(blog.getId());
        int        update     = blogMapper.update(blog);
        List<Long> addTags    = new ArrayList<Long>();
        List<Long> deleteTags = new ArrayList<Long>();
        List<Long> existTags  = new ArrayList<Long>();
        for(Tag tag : queryById.getTags()) {
            existTags.add(tag.getId());
        }
        if(update == 1 && blog.getTagIds() != null && !"".equals(blog.getTagIds())) {
            String[] split = blog.getTagIds().split(",");
            for(String tagId : split) {
                if(existTags == null || existTags.size() < 1) {
                    addTags.add(Long.parseLong(tagId));
                }

                if(!existTags.contains(Long.parseLong(tagId)) && !addTags.contains(Long.parseLong(tagId))) {
                    addTags.add(Long.parseLong(tagId));
                }
            }

            for(Tag tag :queryById.getTags()) {
                if(!Arrays.asList(split).contains(String.valueOf(tag.getId()))) {
                    deleteTags.add(tag.getId());
                }
            }

            for(Long tagId : addTags) {
                blogMapper.addBlogWithTag(blog.getId(), tagId);
            }

            for(Long tagId : deleteTags) {
                blogMapper.deleteBlogWithTag(blog.getId(), tagId);
            }
        }

        return update;
    }

    @RecordLog(detail = "删除博客归档信息", recordType = RecordType.DELETE, recordObject = RecordObject.BLOG)
    @Override
    public int deleteBlog(Long id) {
        Blog queryById = blogMapper.queryById(id);
        for(Tag tag : queryById.getTags())
        blogMapper.deleteBlogWithTag(id, tag.getId());

        return blogMapper.delete(id);
    }

    @Override
    public int deleteBlogWithTag(Long tagId) {
        return blogMapper.deleteAllBlogWithTag(tagId);
    }

    private void queryBlogTagIds(Blog blog){
       StringBuilder tagIds = new StringBuilder();
       for(Tag tag : blog.getTags()) {
           if(tagIds.length() < 1) {
               tagIds.append(tag.getId());
           } else {
               tagIds.append("," + tag.getId());
           }
       }
       if(tagIds.length() > 0) {
           blog.setTagIds(tagIds.toString());
       }
    }
}