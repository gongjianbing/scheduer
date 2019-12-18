/*
 * (C) 2018 LIGHTWORKS CORP.
 * システム名 : 学研アプリ
 * 注意事項 :
 */
package com.example.scheduler.controller;

import com.example.scheduler.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>機能について短い文で「・・・。」とする。</p >
 *
 * @author NWT : gong <br />
 * 変更履歴 <br />
 * 2019/08/05 : gong: 新規<br />
 * @version 1.0
 */

@RestController
@RequestMapping("/quartztest")
public class JobController {
    @Autowired
    private JobService jobService;

    /**
     * 创建cron任务
     *
     * @param jobName
     * @param jobGroup
     * @return
     */
    @RequestMapping(value = "/cron", method = RequestMethod.GET)
    public String startCronJob(@RequestParam("jobName") String jobName, @RequestParam("jobGroup") String jobGroup) {
        jobService.addCronJob(jobName, jobGroup);
        return "create cron task success";
    }

    /**
     * 创建异步任务
     *
     * @param jobName
     * @param jobGroup
     * @return
     */
    @RequestMapping(value = "/async", method = RequestMethod.POST)
    public String startAsyncJob(@RequestParam("jobName") String jobName, @RequestParam("jobGroup") String jobGroup) {
        jobService.addAsyncJob(jobName, jobGroup);
        return "create async task success";
    }

    /**
     * 暂停任务
     *
     * @param jobName
     * @param jobGroup
     * @return
     */
    @RequestMapping(value = "/pause", method = RequestMethod.POST)
    public String pauseJob(@RequestParam("jobName") String jobName, @RequestParam("jobGroup") String jobGroup) {
        jobService.pauseJob(jobName, jobGroup);
        return "pause job success";
    }

    /**
     * 恢复任务
     *
     * @param jobName
     * @param jobGroup
     * @return
     */
    @RequestMapping(value = "/resume", method = RequestMethod.POST)
    public String resumeJob(@RequestParam("jobName") String jobName, @RequestParam("jobGroup") String jobGroup) {
        jobService.resumeJob(jobName, jobGroup);
        return "resume job success";
    }

    /**
     * 删除务
     *
     * @param jobName
     * @param jobGroup
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    public String deleteJob(@RequestParam("jobName") String jobName, @RequestParam("jobGroup") String jobGroup) {
        jobService.deleteJob(jobName, jobGroup);
        return "delete job success";
    }
}