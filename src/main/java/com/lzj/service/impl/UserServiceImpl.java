 package com.lzj.service.impl;  
  
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lzj.bean.User;
import com.lzj.dao.IUserDao;
import com.lzj.service.IUserService;  
  
@Service("userService")  
public class UserServiceImpl implements IUserService {
	
    @Autowired
	@Qualifier("userDao")
    private IUserDao userDao;  
    @Override  
    public User getUserById(int userId) { 
    	return this.userDao.selectByPrimaryKey(userId);  
    }
    }