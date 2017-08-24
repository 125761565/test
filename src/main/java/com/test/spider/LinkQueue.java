package com.test.spider;
/***
 * 保存Visited /unVisited
 * @author Administrator
 *
 */

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
public class LinkQueue {
	/***
	 * 已访问的url集合,即Visited表
	 */
	private static Set<Object> visitedUrl=new HashSet<>();
	
	//待访问的url集合
	//private static Queue unVisitedUrl=new Queue();
	private static PriorityQueue unVisitedUrl=new PriorityQueue();
	//获取unViditedUrl对列
		public static PriorityQueue getUnVisitedUrl() {
			return unVisitedUrl;
		}
	
	//添加到访问过的url队列中
	public static void addVisitedUrl(String url) {
		visitedUrl.add(url);
	}
	//移除访问过的url
	public static void romoveVisitedUrl(String url) {
		visitedUrl.remove(url);
	}
	
	//未访问的unVisitedUrl出队列
		public static Object unvisitedUrlDeQueue() {
			//return unVisitedUrl.deQueue();
			return unVisitedUrl.poll();
		}
		
		//保证添加url到unVisitedUrl的时候每个URL只被访问一次
		public static void addUnvisitedUrl(String url){
			if(url!=null&&!url.trim().equals("")&&!visitedUrl.contains(url)&&!unVisitedUrl.contains(url)) {
				unVisitedUrl.add(url);
			}
		}
	
	// 获得已经访问的url数目
	public static int getVisitedUrlNum() {
		return visitedUrl.size();
	}
	

	
	

	//判断未访问的url对列是否为空
	public static boolean unVisitedUrlIsEmpty() {
		return unVisitedUrl.isEmpty();

	}
}
