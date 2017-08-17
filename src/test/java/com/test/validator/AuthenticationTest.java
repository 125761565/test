package com.test.validator;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;
import org.apache.shiro.mgt.SecurityManager;

public class AuthenticationTest {

	// 用户登录和退出
	/*
	 * @Test public void testLogin(){
	 * 
	 * // 创建SecurityManager工厂，通过init配置文件构造 IniSecurityManagerFactory factory = new
	 * IniSecurityManagerFactory("classpath:shiro-first.ini");
	 * 
	 * // 创建SecurityManager org.apache.shiro.mgt.SecurityManager securityManager =
	 * factory.createInstance();
	 * 
	 * // 将SecurityManager设置到当前运行环境 SecurityUtils.setSecurityManager(
	 * securityManager);
	 * 
	 * // 从SecurityUtils里创建Subject Subject subject = SecurityUtils.getSubject();
	 * 
	 * // 认证提交前准备token UsernamePasswordToken token = new
	 * UsernamePasswordToken("zhangsan","123");
	 * 
	 * try{ // 执行认证提交 subject.login(token); }catch(Exception e){
	 * e.printStackTrace(); }
	 * 
	 * // 是否认证通过 boolean isAuthenticated = subject.isAuthenticated();
	 * System.out.println("是否认证通过: " + isAuthenticated);
	 * 
	 * // 退出操作 subject.logout();
	 * 
	 * isAuthenticated = subject.isAuthenticated(); System.out.println("是否认证通过: " +
	 * isAuthenticated); }
	 */

	// ===================================================================
	// 用户登录和退出
	/****
	 * shiro认证
	 */
	/*@Test
	public void testCustomRealm() {

		// 创建SecurityManager工厂，通过init配置文件构造
		IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");

		// 创建SecurityManager
		org.apache.shiro.mgt.SecurityManager securityManager = factory.createInstance();

		// 将SecurityManager设置到当前运行环境
		SecurityUtils.setSecurityManager(securityManager);

		// 从SecurityUtils里创建Subject
		Subject subject = SecurityUtils.getSubject();

		// 认证提交前准备token
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123");

		try {
			// 执行认证提交
			subject.login(token);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 是否认证通过
		boolean isAuthenticated = subject.isAuthenticated();

		System.out.println("是否认证通过: " + isAuthenticated);

		// 退出操作
		subject.logout();

		isAuthenticated = subject.isAuthenticated();
		System.out.println("是否认证通过: " + isAuthenticated);
	}*/

	 //角色授权、资源授权测试
    
   /* @Test
    public void testAuthorzation(){
        //创建SecurityManager工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-permission.ini");
         
        //创建SecurityManager
        SecurityManager securityManager = factory.getInstance();
         
        //将SecurityManager设置到系统环境
        SecurityUtils.setSecurityManager(securityManager);
         
        //创建Subject
        Subject subject = SecurityUtils.getSubject();
 
        //创建token令牌
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan","123");
         
        //执行认证
        try{
            subject.login(token);
        }catch(AuthenticationException e){
            e.printStackTrace();
        }
         
        System.out.println("是否认认证通过: " + subject.isAuthenticated());
         
         
        //基于角色的授权(角色标识)
        boolean hasRole = subject.hasRole("role3");
        System.out.println("基于角色的授权: " + hasRole);
         
        //基于资源的授权(权限标识符)
        boolean permitted = subject.isPermitted("user:create");
        System.out.println("基于资源的授权: " + permitted);
    }*/
	/***
	 * 自定义授权
	 */
	@Test
	public void testCusRealm() {
		Factory< SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro-realm.ini");
		SecurityManager securityManager=factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken("zhangsan","123");
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		
		System.out.println("是否认证通过："+subject.isAuthenticated());
		
		//基于资源的授权
		boolean permitted=subject.isPermitted("user:create");
		System.out.println("基于资源的授权："+permitted);
		
	}
     


}
