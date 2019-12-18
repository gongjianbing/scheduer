/*
 * (C) 2018 LIGHTWORKS CORP.
 * システム名 : 学研アプリ
 * 注意事項 :
 */
package com.example.scheduler.service;

/**
 * <p>機能について短い文で「・・・。」とする。</p >
 *
 * @author NWT : gong <br />
 * 変更履歴 <br />
 * 2019/08/05 : gong: 新規<br />
 * @version 1.0
 */

public interface JobService {
    /**
     * 添加一个定时任务
     * @param jobName
     * @param jobGroup
     */
    void addCronJob(String jobName, String jobGroup);

    /**
     * 添加异步任务
     * @param jobName
     * @param jobGroup
     */
    void addAsyncJob(String jobName, String jobGroup);

    /**
     * 暂停任务
     * @param jobName
     * @param jobGroup
     */
    void pauseJob(String jobName, String jobGroup);

    /**
     * 暂停任务
     * @param jobId
     */
    void pauseJob(int jobId);

    /**
     * 恢复任务
     * @param triggerName
     * @param triggerGroup
     */
    void resumeJob(String triggerName, String triggerGroup);

    /**
     * 删除job
     * @param jobName
     * @param jobGroup
     */
    void deleteJob(String jobName, String jobGroup);


}