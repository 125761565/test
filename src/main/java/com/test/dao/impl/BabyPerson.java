package com.test.dao.impl;

import org.springframework.stereotype.Component;

import com.test.dao.Person;

/**   
*功能  人的实现类 
*/  
@Component  
public class BabyPerson implements Person{  
 
   @Override  
   public void eatBreakfast() {  
       System.out.println("小Baby正在吃早餐");  
   }  
 
   @Override  
   public void eatLunch() {  
       System.out.println("小Baby正在吃午餐");  
   }  
 
   @Override  
   public void eatSupper() {  
       System.out.println("小Baby正在吃晚餐");  
   }  
 
   @Override  
   public String drink(String name) {  
       return "小Baby在喝："+name;  
   }  
 
}  