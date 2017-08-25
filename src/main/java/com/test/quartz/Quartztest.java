package com.test.quartz;

import java.util.Date;

import javax.ejb.Schedule;

import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.helpers.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.HolidayCalendar;

public class Quartztest {
	
	
	/*public static void main(String[] args) {
		Scheduler sched=new StdSchedulerFactory().getScheduler();
		HolidayCalendar cal=new HolidayCalendar();
		cal.addExcludedDate(new Date());
		//sched.addCalendar("myHolidays",cal,false);
		Trigger trigger=TriggerUtils.makeHourlyTrigger();//每小时触发一次
		trigger.setStartTime(TriggerUtils.getEvenHourDate(new Date()));//从下一个小时开始
		
	}*/

}
