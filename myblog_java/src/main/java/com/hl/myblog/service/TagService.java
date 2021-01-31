package com.hl.myblog.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hl.myblog.po.Tag;

/**
 * @author huanglin 
 * @date   2020年4月11日
 */

public interface TagService {

    /**
     * 添加新标签
     * 
     * @param  tag 新的标签内容
     * @return
     */
    int saveTag(Tag tag);

    /**
     * 根据id获取标签信息
     * 
     * @param  id 标签id
     * @return
     */
    Tag getTag(long id);

    /**
     * 根据标签名获取标签信息
     * 
     * @param  name 查询的名称
     * @return
     */
    Tag getTagByName(String name);

    /**
     * 分页查询标签
     * 
     * @param  pageNum  当前页数
     * @param  pageSize 每页条数
     * @return
     */
    PageInfo<Tag> getTagList(int pageNum, int pageSize);

    /**
     * 获取所有的标签信息
     * 
     * @return
     */
    List<Tag> getTagList();

    /**
     * 根据标签字符串id获取标签数组
     * 
     * @param   ids 标签字符串id
     * @return
     */
    List<Tag> getTagList(String ids);

    /**
     * 获取标签内容最多的前几个
     * 
     * @param  size 需要标签的个数
     * @return
     */
    List<Tag> getTagTop(int size);

    /**
     * 通过博客id获取标签列表
     * @param  blogId 博客id
     * @return
     */
    List<Tag> getTagsByBlogId(Long blogId);

    /**
     * 更新标签信息
     * 
     * @param  tag 更新的内容
     * @return
     */
    int updateTag(Tag tag);

    /**
     * 根据id删除标签
     * 
     * @param id 标签内容
     */
    int deleteTag(Long id);
}