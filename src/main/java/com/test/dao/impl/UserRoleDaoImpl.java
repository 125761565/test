package com.test.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.test.dao.IUserRoleDao;
import com.test.entity.UserRole;
@Repository
public class UserRoleDaoImpl extends BaseDao<UserRole> implements IUserRoleDao {
	private static final String ENTITY_NAME="userrole";
	@Override
	public List<UserRole> findByUserId(String userId) {
		// TODO Auto-generated method stub
		return this.getListByColumn(ENTITY_NAME, "userId", userId);
	}

}
