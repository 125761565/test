package com.test.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.dao.IUserDao;
import com.test.entity.User;

public class BOSRealm extends AuthorizingRealm {
	
	@Autowired
	private IUserDao userDao;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}
	/***
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken=(UsernamePasswordToken) token;
		//从令牌中获取用户名
		String username=upToken.getUsername();
		User user=userDao.findUserByUsername(username);
		if(user==null) {
			//用户不存在 如果用户不存在返回null,Securitymanager会报未知用户异常
			return null;
		}else {
			//用户名存在，获取数据库密码
			String password=user.getPassword();
			/****
			 * 创建简单认证信息对象
			 * 参数1：签名。程序可以在任意位置获取当前放入的对象
			 * 参数2：从数据查出来的密码（不能变）
			 * 参数3：当前realm的名称
			 */
			SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(user,password,this.getClass().getSimpleName());
			//返回安全管理器，由安全管理器负责对比数据库查询的密码和页面提交的密码
			return info;
		}
		
		
	}

}
