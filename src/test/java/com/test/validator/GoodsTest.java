package com.test.validator;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.test.dao.IUserDao;
import com.test.entity.Goods;
import com.test.entity.User;
import com.test.service.GoodsService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml" })
@Transactional
public class GoodsTest {
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private IUserDao userDao;
	
	@org.junit.Test
	public void getList() {
		List<Goods> goods=goodsService.getList();
		for(Goods g:goods) {
			System.out.println(g.getName());
		}
		
	}
		
	 @Test
	 @Rollback(false)
	public void addEntity() {
		Goods goods=new Goods();
		goods.setName("简爱");
		goods.setContent("小说");
		goods.setPic("http://asdglkals");
		goods.setPrice(11.00);
		System.out.println(goodsService.insertEntity(goods));	
	}
	 
	 @Test
	 public void findAll() {
		 List<Goods> goods=goodsService.findAll();
		 for(Goods g:goods) {
				System.out.println(g.getName());
			}	
	 }
	 @Test
	 public void findById() {
		 Goods goods=goodsService.findByColumnName("id","1");
		 System.out.println(goods.getName());
	 }
	 
	 @Test
	 @Rollback(false)
	 public void update() {
		 Goods goods=goodsService.findByColumnName("id", "1");		
		 goods.setId("1");
		 goods.setName("水浒");
		 System.out.println(goodsService.updateEntity(goods));
	 }
	 
	 
	 @Test
	 public void getUser() {
		 System.out.println("====================");
		 User user=userDao.findUserByUsername("zhang");
		 System.out.println(user.getPassword());
	 }

}
