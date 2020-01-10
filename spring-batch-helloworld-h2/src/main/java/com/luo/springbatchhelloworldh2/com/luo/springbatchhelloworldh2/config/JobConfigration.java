// Copyright(c) 2019 GROWTH XPARTNERS, Incorporated.
//
//

package com.luo.springbatchhelloworldh2.com.luo.springbatchhelloworldh2.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
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
        return jobBuilderFactory.get("helloWorldJob").start(step()).build();
    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get("step1").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("Hello World!");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }
}
