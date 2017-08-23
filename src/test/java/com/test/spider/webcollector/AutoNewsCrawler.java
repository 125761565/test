package com.test.spider.webcollector;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

/**
 * Crawling news from hfut news
 *
 * @author hu
 */
public class AutoNewsCrawler extends BreadthCrawler {
    /**
     * @param crawlPath  crawlPath是维护的目录路径 爬虫的信息
     *                  
     * @param autoParse 如果自动签名是正确的，那么面包爬虫将自动提取与pag匹配的regex规则的链接
     */
    public AutoNewsCrawler(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);
        /*start page*/
        this.addSeed("http://news.hfut.edu.cn/list-1-1.html");

        /*fetch url like http://news.hfut.edu.cn/show-xxxxxxhtml*/
        this.addRegex("http://news.hfut.edu.cn/show-.*html");
        /*do not fetch jpg|png|gif*/
        this.addRegex("-.*\\.(jpg|png|gif).*");
        /*do not fetch url contains #*/
        this.addRegex("-.*#.*");

        setThreads(50);
        getConf().setTopN(100);

//        setResumable(true);
    }

    @Override
    public void visit(Page page, CrawlDatums next) {
        String url = page.url();
        /*if page is news page*/
        if (page.matchUrl("http://news.hfut.edu.cn/show-.*html")) {

            /*extract title and content of news by css selector*/
            String title = page.select("div[id=Article]>h2").first().text();
            String content = page.selectText("div#artibody");

            System.out.println("URL:\n" + url);
            System.out.println("title:\n" + title);
            System.out.println("content:\n" + content);

            /*If you want to add urls to crawl,add them to nextLink*/
            /*WebCollector automatically filters links that have been fetched before*/
            /*If autoParse is true and the link you add to nextLinks does not match the 
              regex rules,the link will also been filtered.*/
            //next.add("http://xxxxxx.com");
        }
    }

    public static void main(String[] args) throws Exception {
        AutoNewsCrawler crawler = new AutoNewsCrawler("crawl", true);
        /*start crawl with depth of 4*/
        crawler.start(4);
    }

}