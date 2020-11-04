package com.hl.myblog.dao;

import com.hl.myblog.po.User;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author huanglin 
 * @date   2020年2月6日
 */

@Mapper
public interface UserMapper {
	
	/**
	 * 根据用户id查询用户信息
	 * 
	 * @param  id 用户地
	 * @return
	 */
	public User queryById(@Param("id") Long id);
	
    /**
     * 根据用户名称和用户密码查询用户信息
     * 
     * @param   username  用户名
     * @param   password  用户密码
     * @return
     */
    public User queryByUsernameAndPassword(@Param("userName") String userName, @Param("password") String password);
}