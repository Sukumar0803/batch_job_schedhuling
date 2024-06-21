package com.example.batchschedhuling.demo.schedhuler;

import com.example.batchschedhuling.demo.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.UUID;

@Configuration
@EnableScheduling
public class BatchJobSchedhuler {

    Logger LOGGER = LoggerFactory.getLogger(BatchJobSchedhuler.class);

    private final JobLauncher jobLauncher;
    private final BeanFactory beanFactory;

    public BatchJobSchedhuler(JobLauncher jobLauncher, BeanFactory beanFactory) {
        this.jobLauncher = jobLauncher;
        this.beanFactory = beanFactory;
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void schedhuleBatchJobOne()  {
        try {
            Job job = beanFactory.getBean("oneTaskletJob", Job.class);
            runBatchJob(job);
        } catch (Exception e) {
            LOGGER.info("An Exception Occured");
        }

    }

    public void runBatchJob(Job job) throws Exception {
        JobParameters jobParameters = new JobParametersBuilder().addDate("Date", new Date())
                .addString(Constants.BatchConstants.TRACE_ID, UUID.randomUUID().toString()).toJobParameters();
           JobExecution execution = jobLauncher.run(job, jobParameters);
        LOGGER.info("Batch Job Executed Sucessfully..{} with Job Id: {}", job.getName(), execution.getJobId());
    }
}
