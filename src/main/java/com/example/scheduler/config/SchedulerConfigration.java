/*
 * (C) 2018 LIGHTWORKS CORP.
 * システム名 : 学研アプリ
 * 注意事項 :
 */
package com.example.scheduler.config;

import com.example.scheduler.factory.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

/**
 * <p>機能について短い文で「・・・。」とする。</p >
 *
 * @author NWT : gong <br />
 * 変更履歴 <br />
 * 2019/08/05 : gong: 新規<br />
 * @version 1.0
 */
@Configuration
public class SchedulerConfigration {
    @Autowired
    private JobFactory jobFactory;

    @Bean
    public SchedulerFactoryBean getSchedulerFactoryBean(DataSource masterDataSource) throws Exception {
        Properties properties = new Properties();
        InputStream in = new ClassPathResource("/scheduler.properties").getInputStream();
        properties.load(in);

        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        //设置数据源
        factory.setDataSource(masterDataSource);
        //存在则覆盖更新
        factory.setOverwriteExistingJobs(true);
        //自动启动
        factory.setAutoStartup(true);
        //设置属性
        factory.setQuartzProperties(properties);
        factory.setJobFactory(jobFactory);
        return factory;
    }
}
