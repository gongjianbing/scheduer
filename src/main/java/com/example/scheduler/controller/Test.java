/*
 * (C) 2018 LIGHTWORKS CORP.
 * システム名 : 学研アプリ
 * 注意事項 :
 */
package com.example.scheduler.controller;

import com.example.scheduler.entity.SysScheduleJobEntity;
import com.example.scheduler.utils.SchedulerUtil;
import org.quartz.SchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>機能について短い文で「・・・。」とする。</p >
 *
 * @author NWT : gong <br />
 * 変更履歴 <br />
 * 2019/08/06 : gong: 新規<br />
 * @version 1.0
 */
@Controller
@RequestMapping("/corn")
public class Test {
    @Autowired
    SchedulerFactoryBean schedulerFactoryBean;

    @RequestMapping(value="/add",method = RequestMethod.GET)
    public  void addEmailJob(){
        SysScheduleJobEntity sysScheduleJobEntity=new SysScheduleJobEntity();
        sysScheduleJobEntity.setJobId(1L);
        sysScheduleJobEntity.setBeanName("sendMsgController");
        sysScheduleJobEntity.setCronExpression("0 0/2 * * * ?");
        sysScheduleJobEntity.setMethodName("setJavaMailSender");
        try {
            SchedulerUtil.createCronJobScheduler(schedulerFactoryBean,sysScheduleJobEntity);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
