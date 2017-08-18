package com.test.shiro;

import org.apache.shiro.authz.Permission;
/*****
 * 权限字符串格式:
 * +资源字符串+权限位+实例ID 以+开头 中间通过+分割
 * 权限:0 表示所有权限 1 新增(0001)
 * 2 修改(0010)       4 删除(0100)
 * 8查看 (1000)
 * ex:+user+10 表示对资源user拥有修改、查看权限    
 * @author Administrator
 *
 */
public class BitPermission implements Permission {
	private String resourceIdentity;
	private int PermissionBit;
	private String instanceId;
	
	public BitPermission(String permissionString) {
		String[] array=permissionString.split("\\+");
		if(array.length>1) {
			resourceIdentity=array[1];
		}
		
	}
	
	@Override
	public boolean implies(Permission p) {
		
		return false;
	}

}


































