package com.quartz;


import com.zizhuling.boke.vo.DumbJob;
import com.zizhuling.boke.vo.MyJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by hebiao on 2018/3/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QuartzTest {

    @Test
    public void test1(){
        SchedulerFactory factory = new StdSchedulerFactory();
        // 从工厂里面拿到一个scheduler实例
        Scheduler scheduler= null;
        try {
            scheduler = factory.getScheduler();
            // 计算任务的开始时间，DateBuilder.evenMinuteDate方法是取下一个整数分钟
            //  Date runTime = DateBuilder.evenMinuteDate(new Date());
            Date runTime = DateBuilder.dateOf(9, 59, 5, 15, 12, 2018);

            // 真正执行的任务并不是Job接口的实例，而是用反射的方式实例化的一个JobDetail实例
            JobDetail job= JobBuilder.newJob(MyJob.class).withIdentity("job1","group1").build();

            // 定义一个触发器，startAt方法定义了任务应当开始的时间
            Trigger trigger=TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
                    .startAt(runTime).build();

            // 将任务和Trigger放入scheduler
            scheduler.scheduleJob(job, trigger);

            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }



    }


    @Test
    public void test2(){
        SchedulerFactory factory = new StdSchedulerFactory();
        // 从工厂里面拿到一个scheduler实例
        Scheduler scheduler= null;
        try {
            scheduler = factory.getScheduler();
            // 计算任务的开始时间，DateBuilder.evenMinuteDate方法是取下一个整数分钟
            //  Date runTime = DateBuilder.evenMinuteDate(new Date());
            Date runTime = DateBuilder.dateOf(9, 59, 5, 15, 12, 2017);

            // 真正执行的任务并不是Job接口的实例，而是用反射的方式实例化的一个JobDetail实例
            JobDetail job=JobBuilder.newJob(MyJob.class).withIdentity("job1","group1").build();

            // 定义一个触发器，startAt方法定义了任务应当开始的时间
//        Trigger trigger=TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
//                .startAt(runTime).build();

            Trigger trigger=TriggerBuilder.newTrigger()
                    .withIdentity("trigger3", "group1")
                    .startAt(new Date())  // if a start time is not given (if this line were omitted), "now" is implied
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ? "))
                    .build();

            Trigger trigger2=TriggerBuilder.newTrigger()
                    .withIdentity("trigger3", "group1")
                    .startAt(new Date())
                    .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(5))
                    .endAt( DateBuilder.dateOf(9, 58, 6, 9, 3, 2018))
                    .build();


            // 将任务和Trigger放入scheduler
            scheduler.scheduleJob(job, trigger);

            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void test3(){
        JobDetail jobDetail=JobBuilder.newJob(DumbJob.class)
                .withIdentity("job", "gounp")
                .usingJobData("user","zhang")
                .usingJobData("password","123456")
                .build();
        Trigger trigger=TriggerBuilder.newTrigger()
                .withIdentity("trigger", "triggerGoupd")
                .startAt(new Date())
                .build();

        SchedulerFactory factory=new StdSchedulerFactory();
        Scheduler scheduler= null;
        try {
            scheduler = factory.getScheduler();
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }


    }

}
