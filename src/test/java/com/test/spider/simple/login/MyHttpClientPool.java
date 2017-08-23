package com.test.spider.simple.login;
/***
 * 基于httpclient4.5封装的一个HttpClient线程池
 * @author Administrator
 *
 */

import static org.hamcrest.CoreMatchers.nullValue;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.log4j.Logger;


public class MyHttpClientPool {
	private static Logger log=Logger.getLogger(MyHttpClientPool.class);
	private volatile static MyHttpClientPool httpClientPool;
	public static final int MAX_TOTAL_CONNECTIONS=50;
	public static final int MAX_ROUTE_CONNECTIONS=20;
	public static final int CONNECT_TIMEOUT=3000;//连接时间
	public static final int SOCKET_TIMEOUT=5000;//获取内容
	private static PoolingHttpClientConnectionManager cManager=null;
	private static CloseableHttpClient httpClient;
	
	
	
	
	
	public MyHttpClientPool() {
		super();
		// TODO Auto-generated constructor stub
	}

	static MyHttpClientPool myHttpClientPool=null;
	/**
	 * 初始化连接池
	 */
	static {
		myHttpClientPool=new MyHttpClientPool();
	}

	public static MyHttpClientPool getClientConnectionPool() {
		// TODO Auto-generated method stub
		return myHttpClientPool;
	}

	public static MyHttpClientPool getHttpClientPool() {
		return httpClientPool;
	}

	public static void setHttpClientPool(MyHttpClientPool httpClientPool) {
		MyHttpClientPool.httpClientPool = httpClientPool;
	}

	public static PoolingHttpClientConnectionManager getcManager() {
		return cManager;
	}

	public static void setcManager(PoolingHttpClientConnectionManager cManager) {
		MyHttpClientPool.cManager = cManager;
	}

	public static CloseableHttpClient getHttpClient() {
		return httpClient;
	}

	public static void setHttpClient(CloseableHttpClient httpClient) {
		MyHttpClientPool.httpClient = httpClient;
	}

	public static int getMaxTotalConnections() {
		return MAX_TOTAL_CONNECTIONS;
	}

	public static int getMaxRouteConnections() {
		return MAX_ROUTE_CONNECTIONS;
	}

	public static int getConnectTimeout() {
		return CONNECT_TIMEOUT;
	}

	public static int getSocketTimeout() {
		return SOCKET_TIMEOUT;
	}
	
	

}
