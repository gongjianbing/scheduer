package com.example.scheduler;

import com.example.scheduler.controller.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = {"com/dao","com.example.scheduler.dao"})
@ComponentScan("com.*")
@ServletComponentScan
public class SchedulerApplication {

    public static void main(String[] args) {

        SpringApplication.run(SchedulerApplication.class, args);
//        new Test().addEmailJob();
    }

}
