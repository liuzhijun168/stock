package com.lzj.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lzj.bean.User;
import com.lzj.dao.IUserDao;
import com.lzj.dao.mapper.UserMapper;

@Repository("userDao")
public class UserDaoImpl implements IUserDao{

	@Autowired
	private UserMapper userMapper;
	
	public User selectByPrimaryKey(int userId){
		System.out.println("UserDaoImpl.selectByPrimaryKey()"+userMapper.getUser(1));
		return null;
	}

}
