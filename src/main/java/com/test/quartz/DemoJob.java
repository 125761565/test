package com.test.quartz;

import org.springframework.stereotype.Component;

@Component
public class DemoJob {

	public void execute() {
		System.out.println("demo job!");
	}
}