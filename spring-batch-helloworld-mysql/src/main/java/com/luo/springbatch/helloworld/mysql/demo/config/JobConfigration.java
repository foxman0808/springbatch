package com.luo.springbatch.helloworld.mysql.demo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class JobConfigration {

    // inject task object
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    // inject step object
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job helloWorldJob(){
        return jobBuilderFactory.get("helloWorldJob").start(step0()).build();
    }

    @Bean
    public Step step0() {
        return stepBuilderFactory.get("step0").tasklet((stepContribution, chunkContext) ->{
            System.out.println("Hello World!");
            return RepeatStatus.FINISHED;
        }).build();
    }
}
