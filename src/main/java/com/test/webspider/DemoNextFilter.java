package com.test.webspider;
import cn.edu.hfut.dmic.webcollector.fetcher.NextFilter;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

/**
 * 去重过滤器
 */
public class DemoNextFilter extends BreadthCrawler {

    /*
        该例子利用WebCollector 2.50新特性NextFilter过滤探测到的URL
     */
    public DemoNextFilter(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);
        addSeed("http://blog.csdn.net/");
        addRegex(".*");
        //设置线程数
        setThreads(30);
    }

    @Override
    public void visit(Page page, CrawlDatums next) {
        if (page.matchType("content")) {
            String title = page.select("div[class=article_title]").first().text();
            String author = page.select("div[id=blog_userface]").first().text();
            System.out.println("title:" + title + "\tauthor:" + author);
        }
    }

    public static void main(String[] args) throws Exception {
        DemoNextFilter crawler = new DemoNextFilter("crawl", true);
        crawler.setNextFilter(new NextFilter() {
            @Override
            public CrawlDatum filter(CrawlDatum nextItem, CrawlDatum referer) {
                if (nextItem.matchUrl("http://blog.csdn.net/.*/article/details/.*")) {
                    nextItem.type("content");
                    return nextItem;
                } else {
                    return null;
                }
            }
        });
        crawler.start(2);
    }

}