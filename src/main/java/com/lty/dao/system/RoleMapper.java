package com.lty.dao.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lty.entity.system.Role;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
	
	List<Role> findUserRole(String userName);
	
}