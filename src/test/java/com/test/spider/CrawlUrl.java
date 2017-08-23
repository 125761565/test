package com.test.spider;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class CrawlUrl implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CrawlUrl() {
		
	}
	
	private String oriUrl;//原始url的值，主机部分是域名
	//URL的值，主机部分是IP,为了防止重复主机的出现
	private String url;
	private int urlNo;
	private int statusCode;
	private int hitNum;
	private String charSet;
	private String abstractText;
	private String author;
	private int weight;
	private String fileSize;
	private Timestamp lastUpdateTime;
	private Date timeToLive;
	private String title;
	private String type;//文章类型
	private String[] urlRefrences;//引用的链接
	private int layer;//爬取的层次

	public String getOriUrl() {
		return oriUrl;
	}
	public void setOriUrl(String oriUrl) {
		this.oriUrl = oriUrl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getUrlNo() {
		return urlNo;
	}
	public void setUrlNo(int urlNo) {
		this.urlNo = urlNo;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public int getHitNum() {
		return hitNum;
	}
	public void setHitNum(int hitNum) {
		this.hitNum = hitNum;
	}
	public String getCharSet() {
		return charSet;
	}
	public void setCharSet(String charSet) {
		this.charSet = charSet;
	}
	public String getAbstractText() {
		return abstractText;
	}
	public void setAbstractText(String abstractText) {
		this.abstractText = abstractText;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public Date getTimeToLive() {
		return timeToLive;
	}
	public void setTimeToLive(Date timeToLive) {
		this.timeToLive = timeToLive;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String[] getUrlRefrences() {
		return urlRefrences;
	}
	public void setUrlRefrences(String[] urlRefrences) {
		this.urlRefrences = urlRefrences;
	}
	public int getLayer() {
		return layer;
	}
	public void setLayer(int layer) {
		this.layer = layer;
	}
	
	
	
}




























