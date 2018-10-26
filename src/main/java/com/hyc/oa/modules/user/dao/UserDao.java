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
}
