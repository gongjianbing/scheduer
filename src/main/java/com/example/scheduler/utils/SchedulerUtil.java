/*
 * (C) 2018 LIGHTWORKS CORP.
 * システム名 : 学研アプリ
 * 注意事項 :
 */
package com.example.scheduler.utils;

import com.example.scheduler.entity.SysScheduleJobEntity;
import com.example.scheduler.job.CronJob;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * <p>機能について短い文で「・・・。」とする。</p >
 *
 * @author NWT : gong <br />
 * 変更履歴 <br />
 * 2019/08/06 : gong: 新規<br />
 * @version 1.0
 */
public class SchedulerUtil {
    private static   Logger logger = LoggerFactory.getLogger(SchedulerUtil.class);

    /**
     * 创建一个Cron定时任务
     *
     * @param sysScheduleJobEntity
     */
    public static void createCronJobScheduler(SchedulerFactoryBean schedulerFactoryBean,SysScheduleJobEntity sysScheduleJobEntity) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(sysScheduleJobEntity.getJobId().toString());
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail != null) {
            logger.error("任务已经存在");
        } else {
            //构建job信息
            jobDetail = JobBuilder.newJob(CronJob.class).withIdentity(jobKey).build();
            //用JopDataMap来传递数据
            jobDetail.getJobDataMap().put(Constants.TASK_DATA, sysScheduleJobEntity);

            //表达式调度构建器(即任务执行的时间,每5秒执行一次)
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(sysScheduleJobEntity.getCronExpression());

            //按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger =
                    TriggerBuilder.newTrigger().withIdentity(TriggerKey.triggerKey(sysScheduleJobEntity.getJobId().toString())).withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);
        }

    }
}
