package com.lzj.dao;

import com.lzj.bean.User;

public interface IUserDao {

	User selectByPrimaryKey(int userId);

}
