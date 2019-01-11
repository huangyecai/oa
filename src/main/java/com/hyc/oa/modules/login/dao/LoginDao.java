package com.hyc.oa.modules.login.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyc.oa.modules.login.entity.Login;

@Mapper
public interface LoginDao {
    int delete(String id);

    int insert(Login entity);

    int update(Login entity);

	List<Login> list(Login entity);

	Login get(String id);

}