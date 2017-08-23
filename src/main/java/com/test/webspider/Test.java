package com.test.webspider;

import java.io.FileOutputStream;
import java.io.PrintStream;

public class Test {
	public static void main(String[] args) throws Exception {	
		// 输出重定向到工作空间下面的work文件里面
		PrintStream ps = new PrintStream(new FileOutputStream("junshi.txt"));
		System.setOut(ps);	
		Spider aSpider = new Spider();
		Spider1 aSpider1 = new Spider1();
		aSpider1.setaSpider(aSpider);
		aSpider1.spider1();		
	}

}
