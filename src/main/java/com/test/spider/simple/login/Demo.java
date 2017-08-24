package com.test.spider.simple.login;

import org.jsoup.nodes.Document;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

public class Demo extends BreadthCrawler {

	public Demo(String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);
		this.addSeed("http://news.hfut.edu.cn/list-1-1.html");//种子页面
		this.addRegex("http://news.hfut.edu.cn/show-.*html");//爬取符合 http://news.hfut.edu.cn/show-xxxxxxhtml的URL
		this.addRegex("-.*\\.(jpg|png|gif).*");//过滤图片
		this.addRegex("-.*#.*");//不要爬取包含 # 的URL
	}

	@Override
	public void visit(Page page, CrawlDatums next) {
		String url= page.url();
		//判断是否为新闻页，通过正则可以轻松判断
		if(page.matchUrl("http://news.hfut.edu.cn/show-.*html")) {
			Document doc=page.doc();
			String title=page.select("div[id=Article]>h2").first().text();
			String content=page.select("div#artibody",0).text();
			System.out.println("url:\n"+url);
			System.out.println("title:\n"+title);
			System.out.println("content:\n"+content);
		}
		
	}
	public static void main(String[] args) throws Exception {
		Demo crawler=new Demo("path", true);
		crawler.setThreads(50);
		/*设置每次迭代中爬取数量的上限*/
       // crawler.setTopN(5000);
		crawler.setMaxExecuteCount(100);
		/****
		 * /*设置是否为断点爬取，如果设置为false，任务启动前会清空历史数据。
           如果设置为true，会在已有crawlPath(构造函数的第一个参数)的基础上继
           续爬取。对于耗时较长的任务，很可能需要中途中断爬虫，也有可能遇到
           死机、断电等异常情况，使用断点爬取模式，可以保证爬虫不受这些因素
           的影响，爬虫可以在人为中断、死机、断电等情况出现后，继续以前的任务
           进行爬取。断点爬取默认为false*/		 
	//	crawler.setResumable(true);
		/*开始深度为4的爬取，这里深度和网站的拓扑结构没有任何关系
        可以将深度理解为迭代次数，往往迭代次数越多，爬取的数据越多*/
		crawler.start(3);
	}

}
