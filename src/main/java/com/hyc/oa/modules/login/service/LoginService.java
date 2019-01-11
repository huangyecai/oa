package com.hyc.oa.modules.login.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyc.oa.modules.login.dao.LoginDao;
import com.hyc.oa.modules.login.entity.Login;

@Service
public class LoginService {
	
	@Autowired
	private LoginDao loginDao;

	/**
	 * 列表查询
	 * @param entity
	 * @return
	 */
	public List<Login> list(Login entity) {
		return loginDao.list(entity);
	}

	/**
	 * 根据id获取
	 * @param id
	 * @return
	 */
	public Login get(String id) {
		return loginDao.get(id);
	}

	/**
	 * 保存
	 * @param entity
	 * @return
	 */
	@Transactional
	public String save(Login entity) {
		String id = entity.getUserId();
		if (StringUtils.isNotBlank(id) && get(id) == null) {
			entity.setAuthFlag(0);
			entity.setPassword( new BCryptPasswordEncoder().encode("12345"));
			create(entity);
		}else if (StringUtils.isNotBlank(id) && get(id) != null){
			update( entity );
		}else {
			throw new RuntimeException("用户不存在！");
		}
		return entity.getUserId();
	}
	
	/**
	 * 更新
	 * @param entity
	 */
	private void update(Login entity) {
		loginDao.update(entity);
	}

	/**
	 * 新增
	 * @param entity
	 */
	private void create(Login entity) {
		loginDao.insert(entity);	
	}

}