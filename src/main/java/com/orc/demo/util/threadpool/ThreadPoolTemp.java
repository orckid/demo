package com.orc.demo.util.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 线程列队管理 使用时需要创建 使用结束后手动销毁
 * @author orckid
 */
public class ThreadPoolTemp {

    private static final Logger LOG = LoggerFactory.getLogger(ThreadPoolTemp.class);

    private ExecutorService executorService;

    public ThreadPoolTemp(String poolName) {
        executorService = new ThreadPoolExecutor(ThreadConstant.CORE_POOL_SIZE, ThreadConstant.MAX_POOL_SIZE,
                ThreadConstant.KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1024),
                r -> new Thread(r, poolName + "-thread-pool-%d"), new ThreadPoolExecutor.CallerRunsPolicy());
    }

    /**
     * 批量返回线程任务
     *
     * @param taskList list
     * @param <T>      T
     * @return list
     */
    public <T> List<T> doSubmit(List<Callable<T>> taskList) {
        List<T> result = new ArrayList<>();
        List<Future<T>> futures = new ArrayList<>();
        for (Callable<T> task : taskList) {
            Future<T> future = executorService.submit(task);
            futures.add(future);
        }

        for (Future<T> future : futures) {
            try {
                result.add(future.get());
            } catch (Exception e) {
                LOG.error("批量线程任务执行异常！", e);
            }
        }
        return result;
    }

    /**
     * 提交任务，返回future对象
     *
     * @param task task
     * @param <T>  T
     * @return T
     */
    public <T> Future<T> doSubmit(Callable<T> task) {
        return executorService.submit(task);
    }

    /**
     * 提交任务，返回结果
     *
     * @param task task
     * @param <T>  T
     * @return result
     */
    public <T> T submitResult(Callable<T> task) {
        Future<T> future = executorService.submit(task);
        try {
            return future.get();
        } catch (Exception e) {
            LOG.error("执行线程任务异常！", e);
        }
        return null;
    }

    /**
     * 提交单个无返回的任务
     *
     * @param task task
     */
    public void doExecutor(Runnable task) {
        executorService.execute(task);
    }

    /**
     * 提交批量无返回的任务
     * @param taskList list
     */
    public void doExecutor(List<Runnable> taskList) {
        for (Runnable task : taskList) {
            executorService.execute(task);
        }
    }

    public boolean shoutDown() {
        executorService.shutdown();
        while (true) {
            if (executorService.isTerminated()) {
                LOG.info("线程任务执行结束，线程池关闭。");
                return true;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                LOG.info("线程休眠异常。", e);
            }
        }
    }
}
