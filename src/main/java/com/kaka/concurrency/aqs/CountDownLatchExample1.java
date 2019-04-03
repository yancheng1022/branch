package com.kaka.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class CountDownLatchExample1 {
    private final static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i=0;i<threadCount;i++){
            final int threadNum = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        test(threadNum);
                    } catch (InterruptedException e) {
                        log.error("exception",e);
                    }finally {
                        countDownLatch.countDown();
                    }
                }
            });
        }
        countDownLatch.await();
        log.info("finish");
        executorService.shutdown();
    }

    public static void test(int threadNum) throws InterruptedException {
        Thread.sleep(100);
        log.info("{}", threadNum);
        Thread.sleep(100);
    }
}
