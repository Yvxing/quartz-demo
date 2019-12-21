package com.ujiuye;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class DemoTest  {
    public static void main(String[] args) {
//        将要执行的方法的包装类继承quartz自带的job类，将要执行的逻辑写入重写的方法execute中
        //为了将任务引入，创建该对象并将实现了job的类填入其中
        JobDetail  detail = JobBuilder.newJob(Job1.class).withIdentity("job01","group1").build();
        //对cron表达式的填入
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("timer","group1").withSchedule(CronScheduleBuilder.cronSchedule("* * 11 * * ?")).build();

        Scheduler scheduler = null;
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.scheduleJob(detail,cronTrigger);          //管理并添加任务

            scheduler.start();          //开启
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
                    //cron 表达式有六个或者七个字段组成，并使用空格分隔，顺序为
                    //秒     分       时       日       月       星期几     年       //目前支持1970-2099
                //日和星期的设定不能冲突   虽然都可以设定为*  但不能同时为*  可将其中一个用' ? '替代
                //  可以使用   -  ,  /  *  ?  填入对应的位置，  -  相当于一个闭区间  若是相邻的两个数值设定建议使用  ,
                //相邻的两个使用可能会不生效      /   设置步长   例0/5
    }

}
