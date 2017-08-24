package com.test.spider;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

/***
 * 下载网页工具类
 * 
 * @author Administrator
 *
 */
public class DownLoadFile {
	/****
	 * 根据url和网页类型生成需要保存的网页的文件名，去除url中的非文件名字符
	 * 
	 * @param url
	 * @param contentType
	 * @return
	 */
	public String getFileNameByUrl(String url, String contentType) {
		// 移除"http://"这7个字符
		url = url.substring(7);
		// 确认抓取到的页面为text/html类型
		if (contentType.indexOf("html") != -1) {
			url = url.replaceAll("[\\?/:*|<>\"]", "_") + ".html";
			return url;
		} else {//application/pdf类型
			url = url.replaceAll("[\\?/:*|<>\"]", "_") + "." + contentType.substring(contentType.lastIndexOf("/") + 1);
			return url;
		}


	}

	/****
	 * 保存网页字节数组到本地文件，filePath为要保存的文件的相对地址
	 */
	private void saveToLocal(byte[] data, String filepath) {
		try {
			DataOutputStream out = new DataOutputStream(new FileOutputStream(new File(filepath)));
			for (int i = 0; i < data.length; i++) {
				out.write(data[i]);
				out.flush();
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 下载url指向的网页
	public String downloadFile(String url) {
		String filePath=null;
		//生成HttpClinet对象并设置参数
		HttpClient httpClient=new HttpClient();
		//设置http连接超时为5s
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
		//生成GetMethod对象并设置参数
		GetMethod getMethod=new GetMethod(url);
		//设置get请求超时5s
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
		//设置请求重试处理
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		//执行get请求
		try {
			int statusCode=httpClient.executeMethod(getMethod);
			//判断访问的状态码
			if(statusCode!=HttpStatus.SC_OK) {
				System.out.println("Method failed:"+getMethod.getStatusLine());
				filePath=null;
			}
			//处理http响应内容
			byte[] responseBody=getMethod.getResponseBody();//读取为字节数组
			//根据网页url生成保存时的文件名
			filePath="f://"+getFileNameByUrl(url, getMethod.getResponseHeader("Content-Type").getValue());
			saveToLocal(responseBody, filePath);
		} catch (HttpException e) {
			//发生致命的异常，可能是协议不对或者返回的内容有问题
			System.out.println("请检查你的http地址是否正确");
			e.printStackTrace();
		}catch (IOException e) {
			//发生网络异常
			e.printStackTrace();
		}finally {
			//释放连接
			getMethod.releaseConnection();
		}
		return filePath;
	}

}
