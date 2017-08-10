package com.test.pojo;

import java.io.Serializable;

import javax.management.relation.Role;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="用户姓名不能为空!")
	@Size(min=2,max=4,message="用户姓名长度不符!必须在{min}到{max之间}")
	private String name;
	private String password;
	
	//@validSex(flag="ignore")
	//private String sex;
	
	/***
	 * 对象内嵌约束
	 */
	/*@Valid
	private Role role;*/
	
	
	
	
	public User(String name, String password) {		
		this.name = name;
		this.password = password;
		
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + "]";
	}

	/*public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}*/

	
	
	
	
	

}
