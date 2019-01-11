package com.hyc.oa.modules.user.service;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyc.oa.common.utils.ConstantUtils;
import com.hyc.oa.modules.user.dao.UserRoleDao;
import com.hyc.oa.modules.user.entity.UserRole;

@Service
public class UserRoleService {
	
	@Autowired
	private UserRoleDao userroleDao;

	/**
	 * 列表查询
	 * @param entity
	 * @return
	 */
	public List<UserRole> list(UserRole entity) {
		return userroleDao.list(entity);
	}

	/**
	 * 根据id获取
	 * @param id
	 * @return
	 */
	public UserRole get(String id) {
		return userroleDao.get(id);
	}
	
	/**
	 * 根据用户id获取角色
	 * @param userId
	 * @return
	 */
	public List<UserRole> getByUserId(String userId) {
		return userroleDao.getByUserId(userId);
	}

	/**
	 * 保存
	 * @param entity
	 * @return
	 */
	@Transactional
	public String save(UserRole entity) {
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
	private void update(UserRole entity) {
		userroleDao.update(entity);
	}

	/**
	 * 新增
	 * @param entity
	 */
	private void create(UserRole entity) {
		entity.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		userroleDao.insert(entity);	
	}

	/**
	 * 删除
	 */
	@Transactional
	public boolean delete(String id) {
		int result = userroleDao.delete(id);
		if ( result <= 0) {
			return false;
		}
		return true;
	}
}