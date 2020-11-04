package com.hl.myblog.dao;

import java.util.List;

import com.hl.myblog.po.Tag;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 *  @author huanglin 
 *  @date   2020年5月21日
 */

@Mapper
public interface TagMapper {
    /**
     * 查询所有标签
     * 
     * @return
     */
    public List<Tag> queryAll();

    /**
     * 根据标签id查询标签
     * 
     * @param  id 标签id
     * @return
     */
    public Tag queryById(@Param("id") long id);

    /**
     * 根据标签name查询标签
     * 
     * @param  name 标签名称
     * @return
     */
    public Tag queryByName(@Param("name") String name);

    /**
     * 根据标签id列表查询标签
     * 
     * @param  ids id列表
     * @return
     */
    public List<Tag> queryByIds(@Param("ids") List<Long> ids);

    /**
     * 添加新的标签
     * 
     * @param  name 标签名称
     * @return
     */
    public int add(@Param("name") String name);

    /**
     * 更新标签信息
     * 
     * @param  id   标签id
     * @param  name 标签新名称
     * @return
     */
    public int update(@Param("id") long id, @Param("name") String name);

    /**
     * 删除标签信息
     * 
     * @param  id 标签id
     * @return
     */
    public int delete(@Param("id") long id);
}