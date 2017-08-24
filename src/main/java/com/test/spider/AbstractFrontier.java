package com.test.spider;
/****
 * 封装Berkeley DB的操作
 * @author Administrator
 *
 */

import java.io.File;

import com.sleepycat.bind.serial.StoredClassCatalog;
import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;

public abstract class AbstractFrontier {
	private Environment env;
	private static final String CLASS_CATALOG = "java_calss_catalog";
	protected StoredClassCatalog javaCatalog;
	protected Database catalogdatabase;
	protected Database database;

	public AbstractFrontier(String homeDirectory) {
		System.out.println("Opening environment in: " + homeDirectory);
		EnvironmentConfig envConfig = new EnvironmentConfig();
		envConfig.setTransactional(true);
		envConfig.setAllowCreate(true);
		env = new Environment(new File(homeDirectory), envConfig);
		// 设置DatabaseConfig
		DatabaseConfig dbConfig = new DatabaseConfig();
		dbConfig.setTransactional(true);
		dbConfig.setAllowCreate(true);
		// 打开
		catalogdatabase = env.openDatabase(null, CLASS_CATALOG, dbConfig);
		javaCatalog = new StoredClassCatalog(catalogdatabase);
		DatabaseConfig dbConfig0 = new DatabaseConfig();
		dbConfig0.setTransactional(true);
		dbConfig0.setAllowCreate(true);
		database = env.openDatabase(null, "URL", dbConfig);
	}

	public void close() throws DatabaseException {
		database.close();
		javaCatalog.close();
		env.close();
	}

	protected abstract void put(Object key, Object value);

	protected abstract Object get(Object key);

	protected abstract Object delete(Object key);
}
