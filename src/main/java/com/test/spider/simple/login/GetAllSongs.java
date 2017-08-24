package com.test.spider.simple.login;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

import com.csvreader.CsvWriter;


import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
/***
 * 获取网易云音乐
 * @author Administrator
 *
 */
public class GetAllSongs extends BreadthCrawler{
	private CsvWriter r=null;
	
	public void closeCsv() {
		this.r.close();
	}
	
	/****
	 * 转换字节流
	 * @param crawlPath
	 * @param autoParse
	 * @throws IOException 
	 */
	public static  byte[] readeInputStream(InputStream inputStream) throws IOException {
		ByteArrayOutputStream outStream=new ByteArrayOutputStream();
		byte[] buffer=new byte[1024];
		int len=0;
		while ((len=inputStream.read(buffer))!=-1) {
			outStream.write(buffer,0,len);			
		}
		inputStream.close();
		return outStream.toByteArray();
	}
	/******
	 * 根据URL获取网页源码
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String getURLSource(URL url) throws IOException {
		HttpURLConnection conn=(HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(500000);
		InputStream inputStream=conn.getInputStream();
		byte[] data=readeInputStream(inputStream);
		String htmlSource=new String(data);
		return htmlSource;
	}
	
	
	/****
	 * 
	 * @param crawlPath 爬虫路径
	 * @param autoParse 是否自动解析
	 */
	public GetAllSongs(String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);
		//逗号进行分割
		this.r=new CsvWriter("songId.csv",',',Charset.forName("GBK"));
	}

	@Override
	public void visit(Page page, CrawlDatums next) {
		String song_regex="^http://music.163.com/song\\?id=[0-9]+";
		@SuppressWarnings("unused")
		Pattern songIdPattern=Pattern.compile("^http://music.163.com/song\\?id=([0-9]+)");
		Pattern songInfoPattern=Pattern.compile("(.*?)-(.*?)-");
		//对页面正则判断 有 将歌曲id和网页标题提取出来，否则不操作
		if(Pattern.matches(song_regex, page.url())) {
			// 将网页的URL和网页标题提取出来，网页标题格式：歌曲名字-歌手-网易云音乐
			String url=page.url();
			String title=page.doc().title();
			String songName=null;
			String songSinger=null;
			String songId=null;
			String infoUrl=null;
			String mp3Url=null;
			 // 对标题进行歌曲名字、歌手解析
			Matcher infoMatcher=songInfoPattern.matcher(title);
			if(infoMatcher.find()) {
				songName=infoMatcher.group(1);
				songSinger=infoMatcher.group(2);
			}
			System.out.println("正在抽取:"+url);
			Matcher idMatcher=songInfoPattern.matcher(url);
			if(idMatcher.find()) {
				songId=idMatcher.group(1);
			}
			System.out.println("歌曲："+songName);
			System.out.println("演唱者："+songSinger);
			System.out.println("ID："+songId);
			infoUrl="http://music.163.com/api/song/detail/?id=" + songId + "&ids=%5B+" + songId + "%5D";
			try {
				URL urlObject=new URL(infoUrl);
				//获取json源码
				String urlsource=getURLSource(urlObject);
				JSONObject j=new JSONObject(urlsource);
				JSONArray a=(JSONArray) j.get("songs");
				JSONObject aa=(JSONObject) a.get(0);
				mp3Url=aa.get("mp3Url").toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
			String[] contents= {songId,songName,songSinger,url,mp3Url};
			try {
				this.r.writeRecord(contents);
				this.r.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) throws Exception {
		URL url=new URL("http://music.163.com/api/song/detail/?id=110411&ids=%5B110411%5D");
		String urlsource=getURLSource(url);
		System.out.println(urlsource);
		JSONObject j=new JSONObject(urlsource);
		JSONArray a=(JSONArray) j.get("songs");
		JSONObject aa=(JSONObject) a.get(0);
		System.out.println(aa.get("mp3Url"));
		GetAllSongs crawler=new GetAllSongs("crawler", true);
		crawler.addSeed("http://music.163.com/#/album?id=604667405");
		crawler.addRegex("http://music.163.com/.*");
		crawler.setMaxExecuteCount(50);
		crawler.setThreads(10);
		crawler.setResumable(false);
		crawler.start(3);
		
	}
}
