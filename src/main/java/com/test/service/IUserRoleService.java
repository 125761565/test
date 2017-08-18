package com.test.service;

import java.util.List;

import com.test.entity.UserRole;

public interface IUserRoleService {
	/****
	 * 根据用户ID查询角色ID
	 */
	public List<UserRole> findByUserId(String userId);
}
