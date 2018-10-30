package com.hyc.oa.modules.system.menu.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyc.oa.modules.system.menu.dao.MenuDao;
import com.hyc.oa.modules.system.menu.entity.Menu;

/**  

* <p>Title: MenuService</p>  

* <p>Description: 菜单service</p>  

* @author hyc  

* @date 2018年10月26日  

*/
@Service
public class MenuService {
	
	@Autowired
	private MenuDao menuDao;

	/**
	 * 	保存
	 * @param entity
	 * @return
	 */
	@Transactional
	public String save(Menu entity) {
		String id = entity.getId();
		if (StringUtils.isBlank(id) || get(id) == null) {
			create( entity );
		}else {
			update( entity );
		}
		return entity.getId();
	}
	
	/**
	 * 	新增
	 * @param entity
	 * @return
	 */
	private String create(Menu entity) {
		entity.preInsert();
		menuDao.insert( entity );
		return entity.getId();
	}
	
	/**
	 * 	修改
	 * @param entity
	 * @return
	 */
	private String update(Menu entity) {
		entity.preUpdate();
		menuDao.update( entity );
		return entity.getId();
	}
	
	/**
	 * 	根据id获取菜单
	 * @param id
	 * @return
	 */
	public Menu get(String id) {
		return menuDao.getById(id);
	}
	
	public List<Menu> list(Menu entity) {
		return menuDao.list(entity);
	}
}
