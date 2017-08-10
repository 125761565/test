package com.test.validator;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class Test {
	public static void main(String[] args) {
		/* BigDecimal volumn = new BigDecimal("1");
		    volumn = volumn.add(new BigDecimal("2"));
		    System.out.println(volumn);*/
		    
		    BigDecimal bigLoanAmount = new BigDecimal(503.20);   //创建BigDecimal对象
		    BigDecimal bigInterestRate = new BigDecimal(1000);
		    BigDecimal bigInterest = bigLoanAmount.multiply(bigInterestRate); //BigDecimal运算
		    NumberFormat currency = NumberFormat.getCurrencyInstance();    //建立货币格式化引用
		    NumberFormat percent = NumberFormat.getPercentInstance();     //建立百分比格式化用
		    percent.setMaximumFractionDigits(3);               //百分比小数点最多3位
		    //利用BigDecimal对象作为参数在format()中调用货币和百分比格式化
		    System.out.println("Loan amount:\t" + currency.format(bigLoanAmount));
		    System.out.println("Interest rate:\t" + percent.format(bigInterestRate));
		    System.out.println("Interest:\t" + currency.format(bigInterest));
	}
}
