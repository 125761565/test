package com.test.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.entity.Goods;
@Service
public interface GoodsService {
	
	public List<Goods> getList();
	
	public boolean insertEntity(Goods goods);
	
	public List<Goods> findAll();
	
	public Goods findByColumnName(String columnName ,String value);
	
	public boolean updateEntity(Goods goods);
}
