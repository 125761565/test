package com.test.quartz;

import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class SimpleTriggerExample {
	// public static void main(String[] args) throws Exception {
	/***
	 * JobDetail表示一个具体的可执行的调度程序，Job是这个可执行程调度程序所要执行的内容，另外JobDetail还包含了这个任务调度的方案和策略。
	 */
	/*
	 * JobDetail
	 * job=JobBuilder.newJob(HelloJob.class).withIdentity("dummyJobName","group1").
	 * build(); //JobDetail job=new JobDetail("job1_1","JGroup1",HelloJob.class);
	 * //Trigger代表一个调度参数的配置，什么时候去调。 TriggerBuilder
	 * triggerBuilder=TriggerBuilder.newTrigger()
	 * .withIdentity("dummyTriggerName","group1").startNow();
	 * 
	 * triggerBuilder.withSchedule(SimpleScheduleBuilder.simpleSchedule()
	 * .withIntervalInSeconds(1).withRepeatCount(5)); Trigger
	 * trigger=triggerBuilder.build(); SimpleTrigger simpleTrigger=new
	 * SimpleTrigger("trigger1_1","tgroup1"); simpleTrigger.setStartTime(new
	 * Date()); simpleTrigger.setRepeatInterval(2000);
	 * simpleTrigger.setRepeatCount(100);
	 * 
	 *//****
		 * Scheduler代表一个调度容器，一个调度容器中可以注册多个JobDetail和Trigger。当Trigger与JobDetail组合，就可以被Scheduler容器调度了
		 *//*
			 * try {
			 * 
			 * Scheduler scheduler=new StdSchedulerFactory().getScheduler();
			 * scheduler.start(); scheduler.scheduleJob(job,trigger); } catch (Exception e)
			 * { // TODO: handle exception } Thread.sleep(65L*1000L);
			 * scheduler.shutdown(true);
			 */
	

	public static void main(String[] args) throws Exception {  
  
		// 1.获取scheduler对象，用quartz默认的
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		// 计算下一分钟的时间
		Date runTime = new Date();
		// 2.定义一个作业job，指明job的名称，所在组的名称，以及绑定job类
		JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("job1", "group1").build();
		// 3.定义一个触发该job的触发器，设置触发规则
		Trigger trigger =TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(runTime).build();
		// 4.将job和trigger设置到调度器中
		sched.scheduleJob(job,trigger);
		// 5.启动调度
		sched.start();

		try
		{
			// wait 65 seconds to show job
			Thread.sleep(65L * 1000L);
			// executing...
		}catch(
		Exception e)
		{
			//
		}
		// 6.停止调度
		sched.shutdown(true);
		}
  
 

}
