package com.test.spider.simple.login;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.LinkStringFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.OptionTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class GetAllMovies {
	/**
	 * 获取一个页面的下载链接
	 */
	public static String getMoiveDownloadUrl(String moiveIntroUrl) {
		// 页面下载连接保存在这里
		String moiveDownLoadUrl = "";
		try {
			// 首先根据页面URL建立一个Parser.
			Parser parser = new Parser(moiveIntroUrl);
			// 使用parser中extractAllNodesThatMatch方法，这个有许多的过滤器，可以帮助我们过滤出我们想要的内容,具体可以看api的介绍
			// 这里我们使用链接文本过滤器，可以过滤出链接里面含ftp的内容，这样就可以取出我们想要的链接
			NodeList nodelist = parser.extractAllNodesThatMatch(new LinkStringFilter("ftp"));
			for (int i = 0; i < nodelist.size(); i++) {
				LinkTag tag = (LinkTag) nodelist.elementAt(i);
				moiveDownLoadUrl = tag.getLink();
			}
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return moiveDownLoadUrl;
	}

	/**
	 * 
	 * 获取一个分页里的所有电影介绍页面
	 */
	public static List getAllMoiveUrlFromOneList(String pageListUrl) {
		// 将链接地址以集合的形式返回出去
		List<String> allMoiveUrl = new ArrayList<String>();
		try {
			Parser parser = new Parser(pageListUrl);
			// 这里我们使用属性过滤器，可以帮助我们过滤一些属性特殊或者属性里面值唯一的标签
			NodeList nodelist = parser.extractAllNodesThatMatch(new HasAttributeFilter("class", "ulink"));
			for (int i = 0; i < nodelist.size(); i++) {
				LinkTag tag = (LinkTag) nodelist.elementAt(i);
				// 将取出的分页链接拼接一下，放入到集合中来。
				allMoiveUrl.add("http://www.ygdy8.net" + tag.getLink());
			}
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return allMoiveUrl;
	}

	/**
	 *获取电影网里面的所有的分页 
	 */
	public static List getAllPage() {
		// 将链接地址以集合的形式返回出去
		List<String> allPage = new ArrayList<String>();
		try {
			Parser parser = new Parser("http://www.ygdy8.net/html/gndy/jddy/index.html");
									  //http://www.ygdy8.net/html/gndy/jddy/index.html
			NodeList nodelist = parser.extractAllNodesThatMatch(new TagNameFilter("option"))
					.extractAllNodesThatMatch(new HasAttributeFilter("value"));
			for (int i = 0; i < nodelist.size(); i++) {
				OptionTag tag = (OptionTag) nodelist.elementAt(i);
				if (tag.getAttribute("value").contains("list")) {
					allPage.add("http://www.ygdy8.net/html/gndy/jddy/" + tag.getAttribute("value"));
				}
			}
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return allPage;
	}

	/**
	 * 
	 * 功能：保存数据到文件中
	 * @param content 要保存的内容
	 * @param fileName 目标文件名（路径）
	 * 
	 */
	public static boolean writeContentToFileTwo(String content, String fileName) {
		boolean flag = false;
		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName, true)));
			pw.println();
			pw.print(content);
			pw.flush();
			pw.close();
			flag = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public static List getAllMoive() {
		List<String> movieList = new ArrayList<String>();
		// 得到全部的分页链接
		List<String> allPage = getAllPage();
		for (Iterator iterator = allPage.iterator(); iterator.hasNext();) {
			String pageListUrl = (String) iterator.next();
			List<String> allMoiveUrl = getAllMoiveUrlFromOneList(pageListUrl);
			for (Iterator iterator2 = allMoiveUrl.iterator(); iterator2.hasNext();) {
				String moiveIntroUrl = (String) iterator2.next();
				String moiveDownLoadUrl = getMoiveDownloadUrl(moiveIntroUrl);
				writeContentToFileTwo(moiveDownLoadUrl, "a.txt");
				movieList.add(moiveDownLoadUrl);
			}
		}
		return movieList;
	}
	public static void main(String[] args) {
		getAllMoive();
	}
}