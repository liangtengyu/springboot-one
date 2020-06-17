package com.lty.mapping;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
@DS("slave")
public interface DataSource2Mapper {
    public List<Map<String,Object>> getall();

}
