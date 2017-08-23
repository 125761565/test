package com.test.spider;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.FileNotFoundException;
import java.util.Map.Entry;
import java.util.Set;


import com.sleepycat.bind.EntryBinding;
import com.sleepycat.bind.serial.SerialBinding;
import com.sleepycat.collections.StoredMap;
import com.sleepycat.je.DatabaseException;



/***
 * 实现TODO表
 * @author Administrator
 *
 */
public class BDBFrontier extends AbstractFrontier implements Frontier {
	private StoredMap pendingUrisDb=null;
	//使用默认路径和缓存大小构造函数
	public BDBFrontier(String homeDirectory) throws DatabaseException,FileNotFoundException{
		super(homeDirectory);
		EntryBinding keyBinding=new SerialBinding(javaCatalog,String.class );
		EntryBinding valueBinding=new SerialBinding<>(javaCatalog,CrawlUrl.class );
		pendingUrisDb=new StoredMap(database,keyBinding,valueBinding,true);
	}
	/***
	 * 获取下一条记录
	 */
	@Override
	public CrawlUrl getNext() throws Exception {
		CrawlUrl result=null;
		if(!pendingUrisDb.isEmpty()) {
			Set entrys=pendingUrisDb.entrySet();
			System.out.println(entrys);
			Entry<String,CrawlUrl> entry=(Entry<String,CrawlUrl>)pendingUrisDb.entrySet().iterator().next();
			result=entry.getValue();
			delete(entry.getKey());
		}
		return result;
	}
	//存入url
	@Override
	public boolean putUrl(CrawlUrl url) throws Exception {
		put(url.getOriUrl(),url);
		return true;
	}
	
	//存入数据库
	@Override
	protected void put(Object key, Object value) {
		// TODO Auto-generated method stub
		pendingUrisDb.put(key, value);
	}
	//取出
	@Override
	protected Object get(Object key) {
		// TODO Auto-generated method stub
		return pendingUrisDb.get(key);
	}
	//删除
	@Override
	protected Object delete(Object key) {
		// TODO Auto-generated method stub
		return pendingUrisDb.remove(key);
	}
	//根据URL计算键值
	private String caculateUrl(String url) {
		
		return url;
	}
	public static void main(String[] args) {
		try {
			BDBFrontier bdbFrontier=new BDBFrontier("f:bdb");
			CrawlUrl url=new CrawlUrl();
			url.setOriUrl("http://mail.163.com/");
			bdbFrontier.putUrl(url);
			System.out.println(bdbFrontier.getNext().getOriUrl());
			bdbFrontier.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			
		}
	}

}



























