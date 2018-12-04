package com.hyc.oa.modules.department.dao;

import java.util.List;

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

	int delete(String id);

	int insert(Department entity);

	int insertSelective(Department entity);

	Department get(String id);

	int updateByPrimaryKeySelective(Department entity);

	int update(Department entity);

	List<Department> list(Department entity);

	int deleteByParentId(String parentId);
}