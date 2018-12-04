package com.hyc.oa.modules.department.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyc.oa.modules.department.dao.DepartmentDao;
import com.hyc.oa.modules.department.entity.Department;
import com.hyc.oa.modules.system.menu.entity.Menu;

/**
 * 

* <p>Title: DepartmentService</p>  

* <p>Description: </p>  

* @author hyc  

* @date 2018年11月20日
 */
@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentDao departmentDao;

	/**
	 * 列表查询
	 * @param entity
	 * @return
	 */
	public List<Department> list(Department entity) {
		return departmentDao.list(entity);
	}

	/**
	 * 	根据id获取部门
	 * @param id
	 * @return
	 */
	public Department get(String id) {
		return departmentDao.get(id);
	}

	/**
	 * 保存
	 * @param entity
	 * @return
	 */
	@Transactional
	public String save(Department entity) {
		String id = entity.getId();
		if (StringUtils.isBlank(id) || get(id) == null) {
			create( entity );
		}else {
			update( entity );
		}
		return entity.getId();
	}

	private void update(Department entity) {
		departmentDao.update(entity);
	}

	private String create(Department entity) {
		entity.preInsert();
		departmentDao.insert( entity );
		return entity.getId();
	}

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public boolean delete(String id) {
		Department entity = get(id);
		if (entity == null || entity.getStatus() == 3) {
			return false;
		}
		departmentDao.delete(id);
		departmentDao.deleteByParentId(id);
		return true;
	}
}
