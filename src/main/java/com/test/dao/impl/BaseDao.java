package com.test.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.test.entity.User;
import com.test.entity.Permission;

/***
 * hibernate基础实现类 单表的增删改查 多表联合查询
 * 
 * @author
 *
 * @param <T>
 */

@Repository
public class BaseDao<T> extends HibernateDaoSupport {

	@Autowired()
	@Qualifier("sessionFactory")
	public void setBaseSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	/***
	 * 分页查询
	 * 
	 * @param entityName
	 * @param beginIndex
	 * @param length
	 * @return
	 */
	protected List<T> getList(final String entityName, final int beginIndex, final int length) {

		return this.getHibernateTemplate().execute(new HibernateCallback<List<T>>() {

			@Override
			public List<T> doInHibernate(Session session) throws HibernateException {
				final String hql = " from " + entityName;
				Query query = session.createQuery(hql);
				query.setFirstResult(beginIndex);
				query.setMaxResults(length);
				@SuppressWarnings("unchecked")
				List<T> list = query.list();
				return list;
			}

		});
	}

	/***
	 * 通过列名查询实体类
	 * 
	 * @param entityName
	 * @param columnName
	 * @param value
	 * @return
	 */
	protected T getEntityByColum(final String entityName, final String columnName, final String value) {
		return this.getHibernateTemplate().execute(new HibernateCallback<T>() {

			@Override
			public T doInHibernate(Session session) throws HibernateException {
				String hql = " from " + entityName + " where " + columnName + "=?";
				Query query = session.createQuery(hql);
				query.setString(0, value);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List<T> list = query.list();
				if (CollectionUtils.isEmpty(list)) {
					return null;
				}
				return list.get(0);
			}

		});
	}

	/***
	 * 批量插入数据
	 * 
	 * @return
	 */
	protected boolean insertBatchObject(final List<T> prams) {
		boolean flag = false;
		try {
			this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

				@Override
				public Object doInHibernate(Session session) throws HibernateException {
					for (T t : prams) {
						session.save(t);
					}
					session.flush();
					session.clear();
					return null;
				}
			});
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	/***
	 * 多表查询
	 * 
	 * @return
	 */
	protected List<?> getListBySql(Class<?> clazz, final String sql, final String... args) {

		return this.getHibernateTemplate().execute(new HibernateCallback<List<?>>() {

			@Override
			public List<?> doInHibernate(Session session) throws HibernateException {
				List<Object> list = new ArrayList<>();
				session.doWork(new Work() {

					@Override
					public void execute(Connection connection) throws SQLException {
						if (null != args) {

						}

					}
				});
				return list;
			}

		});
	}

	public List<Permission> findAllByHQL(String string, String...  args) {
		
		return (List<Permission>) this.getHibernateTemplate().execute(new HibernateCallback<List<Permission>>() {

			@Override
			public List<Permission> doInHibernate(Session session) throws HibernateException {
				Query query=session.createQuery(string);
				if(null!=args) {
					for(int i=0;i<args.length;i++) {
						query.setString(i, args[i]);
					}
				}
				@SuppressWarnings("unchecked")
				List<Permission> list=query.list();
				return list;
			}
			
		});
	}

	public User findObjectByHQL(String string, String... args) {
		
		return this.getHibernateTemplate().execute(new HibernateCallback<User>() {

			@Override
			public User doInHibernate(Session session) throws HibernateException {
				Query query=session.createQuery(string);
				if(null!=args) {
					for(int i=0;i<args.length;i++) {
						query.setString(i, args[i]);
					}
				}
				@SuppressWarnings("unchecked")
				List<User> list=query.list();
				
				return CollectionUtils.isEmpty(list)?null:list.get(0);
			}
			
		});
	}
	
	/*****
	 * 单列查询
	 * @param entityName
	 * @param columnName
	 * @param columnValue
	 * @return
	 */
	protected List<T> getListByColumn(final String entityName,final String columnName,final String columnValue){
		String hql=" from "+entityName+" where "+columnName+"=?";
		return this.getListByHql(hql,columnValue);
	}

	protected List<T> getListByHql(final String hql, final String... args) {
		
		return  this.getHibernateTemplate().execute(new HibernateCallback<List<T>>() {
			@Override
			public List<T> doInHibernate(Session session) throws HibernateException {
				Query query=session.createQuery(hql);
				if(null!=args) {
					for(int i=0;i<args.length;i++) {
						query.setString(i, args[i]);
					}
				}
					@SuppressWarnings("unchecked")
					List<T> list=query.list();
					return list;				
			}
			
		});
	}

}
