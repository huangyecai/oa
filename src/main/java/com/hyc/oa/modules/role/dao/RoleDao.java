package com.hyc.oa.modules.role.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyc.oa.modules.role.entity.Role;

@Mapper
public interface RoleDao {
    int delete(String id);

    int insert(Role entity);

    int update(Role entity);

	List<Role> list(Role entity);

	Role get(String id);

}