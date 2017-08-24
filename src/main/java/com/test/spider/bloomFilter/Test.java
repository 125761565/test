package com.test.spider.bloomFilter;

import java.util.ArrayList;
import java.util.List;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;



public class Test {
	private static int size=1000000;
	private static BloomFilter<Integer> bloomFilter=BloomFilter.create(Funnels.integerFunnel(), size);
	
	
	public static void main(String[] args) {
		for(int i=0;i<size;i++) {
			bloomFilter.put(i);
		}
		for(int i=0;i<size;i++) {
			if(!bloomFilter.mightContain(i)) {
				System.out.println("有漏掉的数据");
			}
		}
		List<Integer> list=new ArrayList<Integer>(1000);
		for(int i=size+10000;i<size+20000;i++) {
			list.add(i);
		}
		System.out.println("有误伤的数量:"+list.size());
	}
	

}
