package com.test.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.test.entity.Goods;
@Repository
public interface GoodsDao {
	public  List<Goods> getList();
	public boolean insertEntity(Goods goods);
	public List<Goods> findAll();
	public Goods findByColumnName(String columnName,String value);
	public boolean updateEntity(Goods goods);
}
