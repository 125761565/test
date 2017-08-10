package com.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.GoodsDao;
import com.test.entity.Goods;
import com.test.service.GoodsService;
@Service
public class GoodsServiceImpl implements GoodsService {
	
	@Autowired
	private GoodsDao goodsDao;
	@Override
	public List<Goods> getList() {
		// TODO Auto-generated method stub
		return goodsDao.getList();
	}
	
	
	@Override
	public boolean insertEntity(Goods goods) {
		// TODO Auto-generated method stub
		return goodsDao.insertEntity(goods);
	}


	@Override
	public List<Goods> findAll() {
		// TODO Auto-generated method stub
		return goodsDao.findAll();
	}


	@Override
	public Goods findByColumnName(String columnName,String value) {
		// TODO Auto-generated method stub
		return goodsDao.findByColumnName(columnName,value);
	}


	@Override
	public boolean updateEntity(Goods goods) {
		// TODO Auto-generated method stub
		return goodsDao.updateEntity(goods);
	}

	
	
	
}
