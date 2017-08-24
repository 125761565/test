package com.test.spider.simple.login;

import org.jsoup.nodes.Document;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

public class HelloWorld  extends BreadthCrawler {

	public HelloWorld(String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);
		this.addSeed("http://book.qidian.com/");
		this.addRegex("http://book.qidian.com/info/.*");
		this.addRegex("-.*\\.(jpg|png|gif).*");//过滤图片
		this.addRegex("-.*#.*");//不要爬取包含 # 的URL
	}

	@Override
	public void visit(Page page, CrawlDatums next) {
		if(page.matchUrl("http://book.qidian.com/info/.*")) {
			Document doc=page.doc();
			String title=page.select("div[class=book-info]>h1>em").first().text();
			System.out.println("书名:\n"+title);
			String mulu=page.select("div[class=volume]>ul>li").text();
			System.out.println("目录：\n"+mulu);
		}
		
	}	
	public static void main(String[] args) throws Exception {
		HelloWorld hWorld=new HelloWorld("path", true);
		hWorld.setThreads(50);
		hWorld.setMaxExecuteCount(100);
		hWorld.start(5);
	}

}
