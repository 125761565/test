package com.test.validator;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import com.test.pojo.User;

public class ValidatorTest {
	private static Validator validator;

	   @BeforeClass
	   public static void setUp() {
	      ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	      validator = factory.getValidator();
	   }

	   @Test
	   public void manufacturerIsNull() {
	      User car = new User("","123");

	      Set<ConstraintViolation<User>> constraintViolations =validator.validate( car );

	      assertEquals( 1, constraintViolations.size() );
	      assertEquals(
	         "may not be null",
	         constraintViolations.iterator().next().getMessage()
	      );
	   }
}
