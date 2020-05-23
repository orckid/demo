package com.orc.demo.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author orcki
 * 定时任务,默认单线程执行,需要设置pool.size
 */
//@Component
public class TaskService {
    private static final Logger LOG = LoggerFactory.getLogger(TaskService.class);

    /**
     * fixedDelay：任务执行的时间间隔，跟任务逻辑的执行时间无关
     * 5s钟执行一次,首次执行延迟5s
     */
    @Scheduled(initialDelay = 5000, fixedDelay = 1000)
    public void delayTask() {
        LOG.info("开始定时任务，类型：{}，时间：{}", "fixedDelay", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM" +
                "-dd HH:mm:ss")));
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            LOG.error("定时任务执行异常", e);
        }
    }

    /**
     * fixedRate:下一次任务开始与本次任务开始时间的间隔
     * 任务开始的时间间隔2s
     */
    @Scheduled(fixedRate = 2000)
    public void rateTask() {
        LOG.info("开始定时任务，类型：{}，时间：{}", "fixedRate", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM" +
                "-dd HH:mm:ss")));
    }

    /**
     * 当线程数设为2时，前面2个任务先执行，占用了全部的线程，这个任务线程随机
     * cron任务 10s执行一次
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void cronTask() {
        LOG.info("开始定时任务，类型：{}，时间：{}", "cron", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM" +
                "-dd HH:mm:ss")));
    }
}
