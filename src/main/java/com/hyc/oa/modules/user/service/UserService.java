package com.hyc.oa.modules.user.service;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyc.oa.common.utils.ConstantUtils;
import com.hyc.oa.modules.user.dao.UserDao;
import com.hyc.oa.modules.user.entity.User;

@Service
public class UserService implements Serializable {

    private static final long serialVersionUID = 1L;
    @Autowired
    private UserDao userDao;

    public User get(String id){
        return userDao.getById(id);
    }

    public List<User> list(User user){
        return userDao.list(user);
    }

    @Transactional
	public String save(User entity) {
    	entity.setStatus(ConstantUtils.ALIVE);
		if ( StringUtils.isNotBlank( entity.getId()) && get( entity.getId()) != null) {
			return update( entity);
		}else {
			return create( entity);
		}
	}

	private String create(User entity) {
		entity.preInsert();
		userDao.insert(entity);
		return entity.getId();
	}
	
	private String update(User entity) {
		entity.preUpdate();
		userDao.update(entity);
		return entity.getId();
	}
	
	/**
	 * 	删除账号
	 * @param id
	 * @return
	 */
	@Transactional
	public boolean delete(String id) {
		User entity = get(id);
		if (entity != null && entity.getStatus() != ConstantUtils.DEAD) {
			 userDao.delete(id);
			 return true;
		}
		return false;
	}
	
	/**
	 * 	禁用账号
	 * @param id
	 * @return
	 */
	@Transactional
	public boolean forbid(String id) {
		User entity = get(id);
		if (entity != null && entity.getStatus() == ConstantUtils.ALIVE) {
			userDao.forbid(id);
			return true;
		}
		return false;
	}

	public boolean enable(String id) {
		User entity = get(id);
		if (entity != null && entity.getStatus() == ConstantUtils.FORBID) {
			userDao.enable(id);
			return true;
		}
		return false;
	}
}
