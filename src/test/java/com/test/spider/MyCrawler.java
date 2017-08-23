package com.test.spider;

import java.util.Set;

/****
 * BFS宽度优先遍历
 * @author Administrator
 *
 */
public class MyCrawler {
 /**
  * 使用种子初始化URL队列
  */
 private void initCrawlerWithSeeds(String[] seeds) {
  for (int i = 0; i < seeds.length; i++)
   LinkQueue.addUnvisitedUrl(seeds[i]);
 }
 // 定义过滤器，提取以 http://www.xxxx.com开头的链接
 public void crawling(String[] seeds) {
  LinkFilter filter = new LinkFilter() {
   public boolean accept(String url) {
    if (url.startsWith("https://www.baidu.com/"))
     return true;
    else
     return false;
   }
  };
  // 初始化 URL 队列
  initCrawlerWithSeeds(seeds);
  // 循环条件：待抓取的链接不空且抓取的网页不多于 1000
  while (!LinkQueue.unVisitedUrlIsEmpty()
    && LinkQueue.getVisitedUrlNum() <= 1000) {
   // 队头 URL 出队列
   String visitUrl = (String) LinkQueue.unvisitedUrlDeQueue();
   if (visitUrl == null)
    continue;
   DownLoadFile downLoader = new DownLoadFile();
   // 下载网页
   downLoader.downloadFile(visitUrl);
   // 该 URL 放入已访问的 URL 中
   LinkQueue.addVisitedUrl(visitUrl);
   // 提取出下载网页中的 URL
   Set<String> links = HtmlParserTool.extracLinks(visitUrl, filter);
   // 新的未访问的 URL 入队
   for (String link : links) {
    LinkQueue.addUnvisitedUrl(link);
   }
  }
 }
 // main 方法入口
 public static void main(String[] args) {
  MyCrawler crawler = new MyCrawler();
  crawler.crawling(new String[] { "https://www.baidu.com/" });
 }
}
