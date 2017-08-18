package com.test.shiro;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
public class ShiroRoleTest {
	@Test
	public void testHasRole() {
	Login("classpath:shiro-role.ini","zhang","123");
		Assert.assertTrue(subject().hasRole("role1"));

	}

	private Subject subject() {
		// TODO Auto-generated method stub
		return null;
	}

	private void Login(String configFile, String username, String password) {
		Factory< SecurityManager> factory=new IniSecurityManagerFactory(configFile);		
		SecurityManager securityManager=factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken(username,password);
			subject.login(token);		
		
	}
}
