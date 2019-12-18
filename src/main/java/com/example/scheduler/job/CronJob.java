/*
 * (C) 2018 LIGHTWORKS CORP.
 * システム名 : 学研アプリ
 * 注意事項 :
 */
package com.example.scheduler.job;

import com.example.scheduler.entity.SysScheduleJobEntity;
import com.example.scheduler.utils.Constants;
import com.example.scheduler.utils.SpringContextUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.lang.reflect.Method;
import java.util.concurrent.Future;

/**
 * <p>機能について短い文で「・・・。」とする。</p >
 *
 * @author NWT : gong <br />
 * 変更履歴 <br />
 * 2019/08/05 : gong: 新規<br />
 * @version 1.0
 */
public class CronJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();

        SysScheduleJobEntity scheduleJob = (SysScheduleJobEntity) jobDataMap.get(Constants.TASK_DATA);
        try{
            Object object=SpringContextUtils.getBean(scheduleJob.getBeanName());
            Method method=null;
            if(scheduleJob.getParams() == null){
                method=object.getClass().getDeclaredMethod(scheduleJob.getMethodName());
                method.invoke(object);
            }else{
                method=object.getClass().getDeclaredMethod(scheduleJob.getMethodName(),String.class);
                method.invoke(object,scheduleJob.getParams());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}