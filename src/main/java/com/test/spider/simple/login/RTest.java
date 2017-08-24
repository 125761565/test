package com.test.spider.simple.login;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RTest {
	@Test
	public void simple() {
		Pattern pattern = Pattern.compile("^Java.*");
		Matcher matcher = pattern.matcher("Java是一门编程语言");
		boolean b = matcher.matches();
		System.out.println(b);
	}
	
	@Test
	public void many() {
		Pattern pattern = Pattern.compile("[,|]+");
		String[] strs=pattern.split("Java Hello World Java,Hello,,World|Sun");
		for (int i=0;i<strs.length;i++) {		
			System.out.println(strs[i]);
		}
	}
	
	
}
