package com.hl.myblog.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hl.myblog.annotation.RecordLog;
import com.hl.myblog.common.enums.RecordObject;
import com.hl.myblog.common.enums.RecordType;
import com.hl.myblog.dao.TagMapper;
import com.hl.myblog.globalHandler.exceptionHandler.NotFindException;
import com.hl.myblog.po.Tag;
import com.hl.myblog.service.TagService;

/**
 * @author huanglin
 * @date   2020年4月11日
 */

@Service
public class TagServiceImpl implements TagService{

    @Autowired
    private TagMapper tagMapper;

    @RecordLog(detail = "添加博客标签", recordType = RecordType.INSERT, recordObject = RecordObject.TAG)
    @Override
    public int saveTag(Tag tag) {
        return tagMapper.add(tag.getName());
    }

    @RecordLog(detail = "通过标签id=[[${id}]]获取博客标签信息", recordType = RecordType.SELECT, recordObject = RecordObject.TAG)
    @Override
    public Tag getTag(long id) {
        Tag tag = tagMapper.queryById(id);

        return tag;
    }

    @RecordLog(detail = "通过标签name=[[${name}]]获取博客标签信息", recordType = RecordType.SELECT, recordObject = RecordObject.TAG)
    @Override
    public Tag getTagByName(String name) {
        Tag tag = tagMapper.queryByName(name);

        return tag;
    }

    @RecordLog(detail = "通过分页查询博客标签列表pageNum=[[${pageNum}]], pageSize=[[${pageSize}]]", recordType = RecordType.SELECT, recordObject = RecordObject.TAG)
    @Override
    public PageInfo<Tag> getTagList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize).setOrderBy("id desc");
        PageInfo<Tag> pageInfo = new PageInfo<Tag>(tagMapper.queryAll());

        return pageInfo;
    }

    @RecordLog(detail = "通过标签ids=[[${ides}]]查询博客标签列表", recordType = RecordType.SELECT, recordObject = RecordObject.TAG)
    @Override
    public List<Tag> getTagList(String ids) {
        List<Long> tagIds = new ArrayList<Long>();
            if(ids != null && !"".equals(ids)) {
                String [] array = ids.split(",");
                for(int i = 0; i < array.length; i++) {
                    tagIds.add(new Long(array[i]));
                }
            }
        List<Tag> tags = tagMapper.queryByIds(tagIds);

        return tags;
    }

    @RecordLog(detail = "查询所有的博客标签", recordType = RecordType.SELECT, recordObject = RecordObject.TAG)
    @Override
    public List<Tag> getTagList() {
        List<Tag> tags = tagMapper.queryAll();

        return tags;
    }

    @RecordLog(detail = "查询前[[${size}]]的标签列表", recordType = RecordType.SELECT, recordObject = RecordObject.TAG)
    @Override
    public List<Tag> getTagTop(int size) {
        PageHelper.startPage(1, size).setOrderBy("id desc");
        PageInfo<Tag> pageInfo = new PageInfo<Tag>(tagMapper.queryAll());

        return pageInfo.getList();
    }

    @RecordLog(detail = "更新博客标签", recordType = RecordType.UPDATE, recordObject = RecordObject.TAG)
    @Override
    public int updateTag(Tag tag) {
        Tag oldTag = tagMapper.queryById(tag.getId());
        if(oldTag == null) {
            throw new NotFindException();
        } else {
            int updateTag = tagMapper.update(tag.getId(), tag.getName());

            return updateTag;
        }
    }

    @RecordLog(detail = "通过博客标签id=[[{id}]]删除标签", recordType = RecordType.DELETE, recordObject = RecordObject.TAG)
    @Override
    public int deleteTag(Long id) {
        int deleteTag = tagMapper.delete(id);

        return deleteTag;
    }
}