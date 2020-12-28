package ru.otus.spring.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class BookJobExecutionListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("beforeJob");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("afterJob: " + jobExecution.getStatus());
    }

}
