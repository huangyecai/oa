package com.hyc.oa.modules.department.dao;

import org.apache.ibatis.annotations.Mapper;

import com.hyc.oa.modules.department.entity.Department;

/**
 * 
 * 部门dao
 * 
 * @author hyc
 * 
 */
@Mapper
public interface DepartmentDao {

	int deleteByPrimaryKey(String id);

	int insert(Department entity);

	int insertSelective(Department entity);

	Department selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Department entity);

	int updateByPrimaryKey(Department entity);
}