package com.test.entity;

import java.util.Date;
import java.util.List;

public class User {
	private String id;
	/** 用户名 **/
	private String username;
	/** 密码 **/
	private String password;
	/** 是否删除 **/
	private Integer isDelete;
	/** 创建时间 **/
	private Date createDate;
	// 多对多用户权限表
	private List<UserRole> userRoles;

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

}
