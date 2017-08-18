package com.test.dao;

import java.util.List;

import com.test.entity.UserRole;

public interface IUserRoleDao {
	/****
	 * 根据用户ID查询角色ID
	 */
	public List<UserRole> findByUserId(String userId);
}
