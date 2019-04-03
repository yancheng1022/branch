package com.kaka.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
public class SemaphoreExample1 {
    private final static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(20);
        for (int i=0;i<threadCount;i++){
            final int threadNum = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire(); //获取一个许可
                        test(threadNum);
                        semaphore.release(); //释放一个许可
                    } catch (InterruptedException e) {
                        log.error("exception",e);
                    }
                }
            });
        }

        log.info("finish");
        executorService.shutdown();
    }

    public static void test(int threadNum) throws InterruptedException {
        log.info("{}", threadNum);
        Thread.sleep(100);
    }
}
