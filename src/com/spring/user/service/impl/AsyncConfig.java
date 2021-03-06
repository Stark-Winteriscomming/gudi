package com.spring.user.service.impl;

import java.util.concurrent.Executor;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer{
		//기본 Thread 수
		private static int TASK_CORE_POOL_SIZE = 1;
		//최대 Thread 수
		private static int TASK_MAX_POOL_SIZE = 1;
		//QUEUE 수
		private static int TASK_QUEUE_CAPACITY = 0;
		//Thread Bean Name
		private final String EXECUTOR_BEAN_NAME = "executor1";
		
		@Resource(name="executor1")
		private ThreadPoolTaskExecutor executor1;
		
		@Bean(name="executor1")
		@Override
		public Executor getAsyncExecutor() {
			ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
			executor.setCorePoolSize(TASK_CORE_POOL_SIZE);
			executor.setMaxPoolSize(TASK_MAX_POOL_SIZE);
			executor.setQueueCapacity(TASK_QUEUE_CAPACITY);
			executor.setBeanName(EXECUTOR_BEAN_NAME);
			executor.setWaitForTasksToCompleteOnShutdown(false);
			executor.initialize();
			return executor;
		}
		
		/*
		 * Thread Process도중 에러 발생시
		 */
		/*
		 * task 생성전에 pool이 찼는지를 체크
		 */
		public boolean checkSampleTaskExecute() {
			boolean result = true;
			
			System.out.println("활성 Task 수 :::: " + executor1.getActiveCount());
			
			if(executor1.getActiveCount() >= (TASK_MAX_POOL_SIZE + TASK_QUEUE_CAPACITY)) {
				result = false;
			}
			return result;
		}
}
