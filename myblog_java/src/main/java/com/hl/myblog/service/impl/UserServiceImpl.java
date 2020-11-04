package com.hl.myblog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hl.myblog.annotation.RecordLog;
import com.hl.myblog.common.enums.RecordObject;
import com.hl.myblog.common.enums.RecordType;
import com.hl.myblog.common.utils.MD5Utils;
import com.hl.myblog.dao.UserMapper;
import com.hl.myblog.po.User;
import com.hl.myblog.service.UserService;

/**
 * @author huanglin 
 * @date   2020年2月6日
 */

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;

    @Override
    public User queryUserById(Long id) {
        return userMapper.queryById(id);
    }

    @RecordLog(detail = "通过账户密码查询验证用户是否正确", recordType = RecordType.SELECT, recordObject = RecordObject.USER)
    @Override
    public User checkUser(String username, String password) {
        User user = userMapper.queryByUsernameAndPassword(username, MD5Utils.code(password));

        return user;
    }
}