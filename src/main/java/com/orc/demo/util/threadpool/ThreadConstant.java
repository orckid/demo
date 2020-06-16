package com.orc.demo.util.threadpool;


/**
 * 线程池常量
 * @author orckid
 */
class ThreadConstant {

    /**
     * 核心线程数
     */
    static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors();

    /**
     * 最大线程数
     */
    static final int MAX_POOL_SIZE = CORE_POOL_SIZE * 2;

    /**
     * 空闲线程存活时间(时间参数单位由其他字段指定)
     */
    static final int KEEP_ALIVE_TIME = 1000;



}
