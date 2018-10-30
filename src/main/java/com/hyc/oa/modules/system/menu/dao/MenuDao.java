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
	int delete(String id);

	int insert(Menu entity);

	int insertSelective(Menu entity);

	Menu getById(String id);

	int update(Menu entity);
	
	public List<Menu> list(Menu entity);
}