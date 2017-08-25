package com.test.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.mysql.fabric.xmlrpc.base.Data;
/*****
 * 总共三步走：
1，一个Java类去实现Job，重新里面execute方法，execute()方法里面为要触发的逻辑
2，一个触发器，定义何时执行
3，Job实例化一个JobDetail 对象和触发器一起组成一个任务，注册到scheduler 里面 scheduler.scheduler(JobDetial,job)
 *
 */
public class HelloJob implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("Hello Quartz!"+arg0.getTrigger().getName()+"triggered.time is:"+(new Data()));
	}
	
}
