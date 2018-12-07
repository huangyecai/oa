package com.hyc.oa.modules.role.service;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyc.oa.common.utils.ConstantUtils;
import com.hyc.oa.modules.role.dao.RoleDao;
import com.hyc.oa.modules.role.entity.Role;

@Service
public class RoleService {
	
	@Autowired
	private RoleDao roleDao;

	/**
	 * 列表查询
	 * @param entity
	 * @return
	 */
	public List<Role> list(Role entity) {
		return roleDao.list(entity);
	}

	/**
	 * 根据id获取
	 * @param id
	 * @return
	 */
	public Role get(String id) {
		return roleDao.get(id);
	}

	/**
	 * 保存
	 * @param entity
	 * @return
	 */
	@Transactional
	public String save(Role entity) {
		String id = entity.getId();
		if (StringUtils.isBlank(id) || get(id) == null) {
			create( entity );
		}else {
			update( entity );
		}
		return entity.getId();
	}
	
	/**
	 * 更新
	 * @param entity
	 */
	private void update(Role entity) {
		roleDao.update(entity);
	}

	/**
	 * 新增
	 * @param entity
	 */
	private void create(Role entity) {
		entity.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		entity.setStatus(ConstantUtils.ALIVE);
		roleDao.insert(entity);	
	}

	/**
	 * 删除
	 */
	@Transactional
	public boolean delete(String id) {
		Role entity = get(id);
		if (entity == null || entity.getStatus() == 3) {
			return false;
		}
		roleDao.delete(id);
		return true;
	}

}
