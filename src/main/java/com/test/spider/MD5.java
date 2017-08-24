package com.test.spider;

import java.security.MessageDigest;

/****
 * 传入参数:一个字节数组
 * 传出参数：字节数组的MD5结果字符串
 * @author Administrator
 *
 */
public class MD5 {
	
	public static String getMD5(byte[] source) {
		String s=null;
		//将字节转换成十六进制表示的字符
		char hexDigits[]= {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		try {
			MessageDigest mDigest=MessageDigest.getInstance("MD5");
			mDigest.update(source);
			//MD5的计算结果是一个128位的长整数，用字节表示就是16个字节
			byte tmp[]=mDigest.digest();
			char str[]=new char[16*2];
			int k=0;
			for(int i=0;i<16;i++) {
				byte byte0=tmp[i];
				str[k++]=hexDigits[byte0>>>4&0xf];
				str[k++]=hexDigits[byte0&0xf];
			}
			s=new String(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;

	}
	
	public static void main(String[] args) {
	String pass=MD5.getMD5(new byte[] {'1','2','3'});
	System.out.println(pass);
	}

}
