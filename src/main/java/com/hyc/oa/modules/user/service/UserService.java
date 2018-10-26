package com.hyc.oa.modules.user.service;

import com.hyc.oa.modules.user.dao.UserDao;
import com.hyc.oa.modules.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class UserService implements Serializable {

    private static final long serialVersionUID = 1L;
    @Autowired
    private UserDao userDao;

    public User getById(String id){
        return userDao.getById(id);
    }

    public List<User> list(User user){
        return userDao.list(user);
    }
}
