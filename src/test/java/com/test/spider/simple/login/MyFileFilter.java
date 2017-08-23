package com.test.spider.simple.login;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyFileFilter implements FileFilter{
	private ArrayList files=new ArrayList();
	private String _path;//文件路径
	private String _regexp;//未合并的正则表达式
	@Override
	public boolean accept(File file) {
		try {
			Pattern pattern=Pattern.compile(_regexp);
			Matcher match=pattern.matcher(file.getName());
			return match.matches();
			
		} catch (Exception e) {
			return true;
		}
	}
	
	
	
	

}
