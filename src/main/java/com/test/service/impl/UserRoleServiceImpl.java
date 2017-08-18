package com.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.IUserRoleDao;
import com.test.entity.UserRole;
import com.test.service.IUserRoleService;
@Service
public class UserRoleServiceImpl implements IUserRoleService {
	@Autowired
	private IUserRoleDao userRoleDao;
	@Override
	public List<UserRole> findByUserId(String userId) {
		// TODO Auto-generated method stub
		return userRoleDao.findByUserId(userId);
	}

}
