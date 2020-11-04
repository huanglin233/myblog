package com.hl.myblog.service;

import com.hl.myblog.po.User;

/**
 * @author huanglin 
 * @date   2020年2月6日
 */

public interface UserService {

    /**
    * 根据用户id查询用户信息
    * 
    *  @param  id 用户Id
    * @return
    */
    User queryUserById(Long id);

    /**
    * 用户的登录验证
    * 
    * @param  username 用户名
    * @param  password 密码
    * @return 
    */
    User checkUser(String username, String password);
}