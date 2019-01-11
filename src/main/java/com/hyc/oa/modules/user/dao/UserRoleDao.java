package com.hyc.oa.modules.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyc.oa.modules.user.entity.UserRole;

@Mapper
public interface UserRoleDao {
    int delete(String id);

    int insert(UserRole entity);

    int update(UserRole entity);

	List<UserRole> list(UserRole entity);

	UserRole get(String id);

	List<UserRole> getByUserId(String userId);

}