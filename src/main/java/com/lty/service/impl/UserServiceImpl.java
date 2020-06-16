package com.lty.service.impl;

import com.lty.mapping.UserMapper;
import com.lty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<Map<String, Object>> getAllUser() throws Exception{
        return userMapper.getAllUser();
    }




}
