package com.hl.myblog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hl.myblog.annotation.RecordLog;
import com.hl.myblog.common.enums.RecordObject;
import com.hl.myblog.common.enums.RecordType;
import com.hl.myblog.dao.TypeMapper;
import com.hl.myblog.globalHandler.exceptionHandler.NotFindException;
import com.hl.myblog.po.Type;
import com.hl.myblog.service.TypeService;

/**
 * @author huanglin 
 * @date   2020年4月12日
 */

@Service
public class TypeServiceImpl implements TypeService{

    @Autowired
    TypeMapper typeMapper;

    @RecordLog(detail = "添加博客分类", recordType = RecordType.INSERT, recordObject = RecordObject.TYPE)
    @Override
    public int saveType(Type type) {
        int addResult = typeMapper.add(type.getName());

        return addResult;
    }

    @RecordLog(detail = "通过分类id=[{{id}}]查询分类信息", recordType = RecordType.SELECT, recordObject = RecordObject.TYPE)
    @Override
    public Type getType(Long id) {
        Type type = typeMapper.queryById(id);

        return type;
    }

    @RecordLog(detail = "通过分类name=[{{name}}]查询分类信息", recordType = RecordType.SELECT, recordObject = RecordObject.TYPE)
    @Override
    public Type getTypeByName(String name) {
        Type type = typeMapper.queryByName(name);

        return type;
    }

    @RecordLog(detail = "通过分页查询博客分类列表pageNum=[{{pageNum}}], pageSize=[{{pageSize}}]", recordType = RecordType.SELECT, recordObject = RecordObject.TYPE)
    @Override
    public PageInfo<Type> getTypeList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        return new PageInfo<Type>(typeMapper.queryAll());
    }

    @RecordLog(detail = "查询所有的博客分类", recordType = RecordType.SELECT, recordObject = RecordObject.TYPE)
    @Override
    public List<Type> getTypeList() {
        List<Type> types = typeMapper.queryAll();

        return types;
    }

    @RecordLog(detail = "查询博分类前[{{size}}]的分类列表", recordType = RecordType.SELECT, recordObject = RecordObject.TYPE)
    @Override
    public List<Type> getTypeTop(int size) {
        PageHelper.startPage(1, size).setOrderBy("id desc");
        PageInfo<Type> pageInfo = new PageInfo<Type>(typeMapper.queryAll());

        return pageInfo.getList();
    }

    @RecordLog(detail = "更新博客分类信息", recordType = RecordType.UPDATE, recordObject = RecordObject.TYPE)
    @Override
    public int updateType(Type type) {
        Type oldType = typeMapper.queryById(type.getId());
        if(oldType == null) {
            throw new NotFindException();
        } else {
            int update = typeMapper.update(type.getId(), type.getName());

            return update;
        }
    }

    @RecordLog(detail = "更具博客分类id=[{{id}}]删除分类信息", recordType = RecordType.DELETE, recordObject = RecordObject.TYPE)
    @Override
    public int deleteType(Long id) {
        int delete = typeMapper.delete(id);

        return delete;
    }
}