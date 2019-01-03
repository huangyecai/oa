package com.hyc.oa.modules.user.dao;


import com.hyc.oa.modules.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {
    public User getById(String id) ;

    List<User> list(User user);

	public void insert(User entity);

	public void update(User entity);

	public int delete(String id);
	
	public int forbid(String id);

	public int enable(String id);
}
