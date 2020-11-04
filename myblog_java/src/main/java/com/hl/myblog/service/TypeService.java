package com.hl.myblog.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hl.myblog.po.Type;

/**
 * @author huanglin 
 * @date   2020年4月12日
 */

public interface TypeService {

    /**
    * 添加博客分类
    * 
    * @param  type 添加的分类内容
    * @return
    */
    int saveType(Type type);

    /**
    * 获取指定id的博客分类信息
    * 
    * @param  id 分类id
    * @return
    */
    Type getType(Long id);

    /**
    * 根据分类名称获取分类信息
    * 
    * @param  name 名称
    * @return
    */
    Type getTypeByName(String name);

    /**
     * 分页查询博客分类列表
     * 
     * @param  pageNum  当前页数
     * @param  pageSize 每页条数
     * @return
     */
    PageInfo<Type> getTypeList(int pageNum, int pageSize);

    /**
     * 获取所有的博客分类信息
     * 
     * @return
     */
    List<Type> getTypeList();

    /**
     * 获取博客分类前几的博客
     * 
     * @param  size 查询的分类数
     * @return
     */
    List<Type> getTypeTop(int size);

    /**
     * 更新博客分类
     * 
     * @param  type 更新的分类信息
     * @return
     */
    int updateType(Type type);

    /**
     * 删除博客分类
     * 
     * @param id 删除的博客id
     */
    int deleteType(Long id);
}