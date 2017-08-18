package com.test.validator;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.test.entity.UserRole;
import com.test.service.IUserRoleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml" })
@Transactional
public class ShiroTest {
	@Autowired
	private IUserRoleService userRoleService;
	@Test
	public void userRole() {
		List<UserRole> lRoles=userRoleService.findByUserId("1");
		System.out.println(lRoles.size());
	}
}
