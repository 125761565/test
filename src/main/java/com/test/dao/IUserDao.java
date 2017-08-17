package com.test.dao;

import com.test.entity.User;

public interface IUserDao {

	public	User findUserByUsername(String username);

}
