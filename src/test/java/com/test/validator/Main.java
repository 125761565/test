package com.test.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.test.aspectj.Monkey;
import com.test.dao.Person;
import com.test.dao.impl.BabyPerson;
import com.test.service.impl.PersonService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml" })
@Transactional
public class Main {
	
	@Test
	public void monkey() {
		 
	        Monkey monkey = new Monkey();
		try {
			monkey.stealPeaches("孙大圣的大徒弟");			
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void man() {
		 Person person=new BabyPerson(); 
	        person.eatBreakfast();  
	        System.out.println("===================================================");  
	        person.eatLunch();  
	        System.out.println("===================================================");  
	        person.eatSupper();  
	        System.out.println("===================================================");  
	        person.drink("可乐");  
	        System.out.println("===================================================");  
	      
	}
	@Test
	public void persion() {
		PersonService personService = new PersonService();
	      String personName = "Jim";  
	      personService.addPerson(personName);  
	      personService.deletePerson(personName);  
	      personService.editPerson(personName);  
	     
	    }  
	

}
