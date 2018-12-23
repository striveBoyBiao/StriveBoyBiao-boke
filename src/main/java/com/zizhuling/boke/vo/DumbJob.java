package com.zizhuling.boke.vo;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

public class DumbJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey jobKey=context.getJobDetail().getKey();
        JobDataMap dataMap=context.getJobDetail().getJobDataMap();
        String user=dataMap.getString("user");
        String password=dataMap.getString("password");
        System.out.println("jobkey:"+jobKey+"---------user:"+user+"----------password:"+password);
        
        
        
    }

}
