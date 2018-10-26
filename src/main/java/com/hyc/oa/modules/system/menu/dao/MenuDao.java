package com.hyc.oa.modules.system.menu.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyc.oa.modules.system.menu.entity.Menu;

/**
 * <p> Title: 菜单Dao </p>
 * <p> Description: </p>
 * 
 * @author hyc
 * 
 * @date 2018年10月26日
 */
@Mapper
public interface MenuDao {
	int deleteByPrimaryKey(String id);

	int insert(Menu entity);

	int insertSelective(Menu entity);

	Menu selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Menu entity);

	int updateByPrimaryKey(Menu entity);
	
	public List<Menu> list(Menu entity);
}