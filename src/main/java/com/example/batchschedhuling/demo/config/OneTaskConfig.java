package com.example.batchschedhuling.demo.config;

import com.example.batchschedhuling.demo.tasks.OneTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class OneTaskConfig {

    private Logger LOGGER = LoggerFactory.getLogger(OneTaskConfig.class);

    @Bean
    public Job oneTaskletJob(JobRepository jobRepository, Step taskletStep) {
        return new JobBuilder("oneTaskletJob", jobRepository)
                .start(taskletStep)
                .build();
    }

    @Bean
    public Step taskletStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("taskletStep", jobRepository).tasklet(new OneTask(), transactionManager).build();
    }
}
