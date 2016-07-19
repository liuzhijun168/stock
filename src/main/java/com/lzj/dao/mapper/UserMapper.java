package com.lzj.dao.mapper;

import com.lzj.bean.User;

public interface UserMapper {

	int insertStation(User user);

	User getUser(int id);
	
}