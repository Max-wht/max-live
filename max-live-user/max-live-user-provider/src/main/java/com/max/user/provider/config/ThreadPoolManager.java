package com.max.user.provider.config;

import java.util.concurrent.*;

/**
 * @author Max
 * @description
 * @date 2025/2/18 16:19
 */
public class ThreadPoolManager {

    //创建一个线程池工厂
    public static ThreadPoolExecutor commonAsyncPool = new ThreadPoolExecutor(
            2,
            8,
            60,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10),
            //自定义线程工厂
            new ThreadFactory() {

                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r);
                    thread.setName("commonAsyncPool-" + ThreadLocalRandom.current().nextInt(1000));
                    return  thread;
                }
            },
            new ThreadPoolExecutor.CallerRunsPolicy()
    );
}
