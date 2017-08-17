package com.test.dao.impl;

import org.springframework.stereotype.Repository;

import com.test.dao.IUserDao;
import com.test.entity.User;
@Repository
public class UserDaoImpl extends BaseDao<User> implements IUserDao {
	private static final String ENTITY_NAME = "User";
	@Override
	public User findUserByUsername(String username) {
		
		return this.getEntityByColum(ENTITY_NAME, "username", username);
	}

}
