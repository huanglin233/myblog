package com.hl.myblog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hl.myblog.po.Type;

/**
 *  @author huanglin 
 *  @date   2020年5月21日
 */

@Mapper
public interface TypeMapper {
     /**
     * 查询所有分类
     * 
     * @return
     */
    public List<Type> queryAll();

    /**
     * 根据分类id查询分类
     * 
     * @param  id 分类id
     * @return
     */
    public Type queryById(@Param("id") long id);

    /**
     * 根据分类name查询分类
     * 
     * @param  name 分类信息名称
     * @return
     */
    public Type queryByName(@Param("name") String name);

    /**
     * 根据博客id查询分类信息
     * 
     * @param  bid 博客id
     * @return
     */
    public Type queryByBlogId(@Param("bid") long bid);

    /**
     * 添加新的分类
     * 
     * @param  name 分类名称
     * @return
     */
    public int add(@Param("name") String name);

    /**
     * 更新分类信息
     * 
     * @param  id   分类id
     * @param  name 分类新名称
     * @return
     */
    public int update(@Param("id") long id, @Param("name") String name);

    /**
     * 删除分类信息
     * 
     * @param  id 分类id
     * @return
     */
    public int delete(@Param("id") long id);
}