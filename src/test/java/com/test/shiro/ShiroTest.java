package com.test.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.util.Factory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

public class ShiroTest {
	@Test
	public void testHelloWorld() {
		Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro-multi-realm.ini");
		
		SecurityManager securityManager=factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken("zhang","123");
		try {
			subject.login(token);			
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(true, subject.isAuthenticated());//断言用户已经登录
		//退出
		subject.logout();

	}
	@Test
	public void testAllsuccessfulStategeyWithSuccess() {
		login("classpath:shiro-authenticator-all-success.ini");
		Subject subject=SecurityUtils.getSubject();
		//得到一个身份集合，包含了Realm验证成功的身份信息
		PrincipalCollection principalCollection=subject.getPrincipals();
		Assert.assertEquals(2, principalCollection.asList().size());
		System.out.println(principalCollection.asList().size());
	}
	
	
	public void login(String configFile) {
		Factory<SecurityManager> factory=new IniSecurityManagerFactory(configFile);		
		SecurityManager securityManager=factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken("zhang","123");
		subject.login(token);
	}
}
