package com.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.IUserDao;
import com.test.entity.User;
import com.test.service.IUserService;
@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserDao userDao;
	@Override
	public User findUserByUsername(String username) {
		
		return userDao.findUserByUsername(username);
	}

}
