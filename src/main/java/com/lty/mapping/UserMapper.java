package com.lty.mapping;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface UserMapper {

    public List<Map<String,Object>> getAllUser();


}
