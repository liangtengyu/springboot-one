package com.lty.service.impl;

import com.lty.mapping.DataSource2Mapper;
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
    @Autowired
    private DataSource2Mapper dataSource2Mapper;


    @Override
    public List<Map<String, Object>> getAllUser() throws Exception{
        return userMapper.getAllUser();
    }

    @Override
    public List<Map<String, Object>> getall() {
        return dataSource2Mapper.getall();

    }


}
