package com.test.spider.simple;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class RetrivePage {
	private static HttpClient httpClient = new HttpClient();
	// 设置代理服务器
	static {
		// 设置代理服务器端口
		httpClient.getHostConfiguration().setProxy("localhost", 8080);
	}

	public static boolean downloadPage(String path) throws IOException, Exception {
		InputStream input = null;
		OutputStream output = null;
		// 得到post方法
		PostMethod postMethod = new PostMethod(path);
		// 设置参数
		NameValuePair[] postData = new NameValuePair[2];
		postData[0] = new NameValuePair("name", "baidu");
		// postData[1]=new NameValuePair("password","******");
		postMethod.addParameters(postData);
		// 执行，返回状态码
		int statusCode = httpClient.executeMethod(postMethod);
		// 针对状态码进行处理_只处理返回值为200的状态码
		if (statusCode == HttpStatus.SC_OK) {
			input = postMethod.getResponseBodyAsStream();
			// 得到文件名
			String filename = path.substring(path.lastIndexOf('/') + 1);
			// 获得文件输出流
			output = new FileOutputStream(filename);
			// 输出到文件夹
			int tempByte = -1;
			while ((tempByte = input.read()) > 0) {
				output.write(tempByte);
			}
			// 关闭输入输出流
			if (input != null) {
				input.close();
			}
			if (output != null) {
				output.close();
			}
			return true;
		} else if ((statusCode == HttpStatus.SC_MOVED_TEMPORARILY) || (statusCode == HttpStatus.SC_MOVED_PERMANENTLY)
				|| (statusCode == HttpStatus.SC_SEE_OTHER) || (statusCode == HttpStatus.SC_TEMPORARY_REDIRECT)) {
				//读取新的url地址
			Header header=postMethod.getResponseHeader("location");
			if(header!=null) {
				String newUrl=header.getValue();
				if(newUrl==null||newUrl.equals("")) {
					newUrl="/";
					//使用post转向
					PostMethod redirect=new PostMethod(newUrl);
					//发送请求，做进一步处理
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		try {
			RetrivePage.downloadPage("https://www.baidu.com/");
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}



























