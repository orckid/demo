package com.orc.demo.util.threadpool;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 线程列队管理
 * 静态方法直接使用，使用后无需销毁，系统保留
 *
 * @author orckid
 */
public class ThreadPoolUtils {

    private static final Logger LOG = LoggerFactory.getLogger(ThreadPoolUtils.class);

    private static ExecutorService executorService = new ThreadPoolExecutor(ThreadConstant.CORE_POOL_SIZE,
            ThreadConstant.MAX_POOL_SIZE, ThreadConstant.KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1024), r -> new Thread(r, "Thread-pool-%d"),
            new ThreadPoolExecutor.CallerRunsPolicy());

    private ThreadPoolUtils() {
    }

    /**
     * 批量返回线程任务
     *
     * @param taskList list
     * @param <T>      T
     * @return list
     */
    public static <T> List<T> doSubmit(List<Callable<T>> taskList) {
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
    public static <T> Future<T> doSubmit(Callable<T> task) {
        return executorService.submit(task);
    }

    /**
     * 提交任务，返回结果
     *
     * @param task task
     * @param <T>  T
     * @return result
     */
    public static <T> T submitResult(Callable<T> task) {
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
    public static void doExecutor(Runnable task) {
        executorService.execute(task);
    }

    /**
     * 提交批量无返回的任务
     * @param taskList list
     */
    public static void doExecutor(List<Runnable> taskList) {
        for (Runnable task : taskList) {
            executorService.execute(task);
        }
    }
}
