package com.test.validator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.test.pojo.Student;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class StudentTest implements Serializable {
	public static void main(String[] args) {
		Student xiaoming = getBean();
		List<String> validate = validate(xiaoming);
		validate.forEach(row -> {
			System.out.println(row.toString());
		});

	}

	private static Student getBean() {
		Student bean = new Student();
		bean.setName(null);
		bean.setAddress("北京");
		bean.setBirthday(new Date());
		bean.setFriendName(null);
		bean.setWeight(new BigDecimal(190));
		bean.setEmail("xiaogangfan163.com");
		bean.setSpellName("XIAOGANGFAN");
		return bean;
	}
	//获取一个Validator实例
	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

	public static <T> List<String> validate(T t) {
		Validator validator = factory.getValidator();
		/****
		 * 该Validator界面包含三种可用于验证整个实体的方法，也可以仅用于实体的单个属性。
		 *所有三种方法返回a Set<ConstraintViolation>。如果验证成功，则该集合为空。
		 *否则，ConstraintViolation为每个违反约束添加一个实例。
		 */
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);

		List<String> messageList = new ArrayList<>();
		for (ConstraintViolation<T> constraintViolation : constraintViolations) {
			messageList.add(constraintViolation.getMessage());
		}
		return messageList;
	}
}