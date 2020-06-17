package com.lty.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
@DS("master")
public interface UserMapper {
    public List<Map<String,Object>> getAllUser();

}
