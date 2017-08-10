package com.test.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.test.dao.GoodsDao;
import com.test.entity.Goods;

@Repository
public class GoodsDaoImpl extends BaseDao<Goods> implements GoodsDao {
	private static final String ENTITY_NAME = "Goods";
	
	/***
	 * 分页查询
	 */
	@Override
	public List<Goods> getList() {
		return this.getList(ENTITY_NAME, 0, 3);
	}
	/***
	 * 插入
	 */
	@Override
	public boolean insertEntity(Goods goods) {
		
		try {
			this.getHibernateTemplate().save(goods);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	/***
	 * 查询所有数据
	 */
	@Override
	public List<Goods> findAll() {
		// TODO Auto-generated method stub
		return (List<Goods>) this.getHibernateTemplate().find("from "+ENTITY_NAME);
	}
	/***
	 * 通过ID查询
	 */
	@Override
	public Goods findByColumnName(String columnName,String value) {
		// TODO Auto-generated method stub
		return  this.getEntityByColum(ENTITY_NAME, columnName, value);
	}
	@Override
	public boolean updateEntity(Goods goods) {
		try {
			this.getHibernateTemplate().saveOrUpdate(goods);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	
}
