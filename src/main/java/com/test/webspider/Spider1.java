package com.test.webspider;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;

public class Spider1 {
	static Vector<URL> urlVector = new Vector<URL>();
	Spider aSpider;

	public Spider getaSpider() {
		return aSpider;
	}
	public void setaSpider(Spider aSpider) {
		this.aSpider = aSpider;
	}
	public void spider1() {// 生成一级链接（翻页）
		try {// 最外层获取所有异常http://club.xywy.com/list_284_all_1.htm高血压
			//http://club.xywy.com/list_730_all_2.htm头痛
			//http://club.xywy.com/list_696_all_3.htm鼻炎
			for (int a = 1; a <= 2000; a++) {
				String url = "http://club.xywy.com/list_284_all_";
				url += String.valueOf(a) + ".htm";
				URL tempUrl = new URL(url);
				this.spider2(tempUrl);
			}// 已经找到规律 就不用从网页上面爬了
				// 第n页的链接为http://club.xywy.com/list_730_all_n.html
		} catch (Exception e) {
			System.out.println("error!!!");
		} 

	}

	public void spider2(URL url) {// 爬二级链接 然后把链接装到一个vector容器里面

		try {
			URLConnection cnn = url.openConnection();
			cnn.setReadTimeout(4000);

			BufferedReader read = new BufferedReader(new InputStreamReader(
					cnn.getInputStream(), "GBK"));
			String line = "";

			while ((line = read.readLine()) != null) {// 一行一行读取网页源代码
				int index;
				if (line.indexOf("http://club.xywy.com/static/") >= 0) {
					index = line.indexOf("http://club.xywy.com/static/");
					String text = line.substring(index);
					text = text.substring(0, text.indexOf("\""));
					urlVector.add(new URL(text));
				}
			}
			this.spider3();
		} catch (Exception e) {
			System.out.println("error!!!");
		} 
	}

	public void spider3() {// 爬取对话页面

		try {
			while (urlVector.size() > 0) {
				aSpider.spider(urlVector.firstElement());
				urlVector.remove(0);
			}
		} catch (Exception e) {
			System.out.println("error!!!");
		}
	}

}