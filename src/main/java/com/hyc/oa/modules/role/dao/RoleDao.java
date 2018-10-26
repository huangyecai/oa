package com.hyc.oa.modules.role.dao;

import org.apache.ibatis.annotations.Mapper;

import com.hyc.oa.modules.role.entity.Role;

@Mapper
public interface RoleDao {
    int deleteByPrimaryKey(String id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}