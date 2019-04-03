package com.kaka.concurrency.sync;

import lombok.extern.slf4j.Slf4j;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import sun.rmi.runtime.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Slf4j
public class SynchronizedExample1 {
    //修饰代码块
    public void test1(){
        synchronized (this){
            for (int i=0;i<10;i++){
                log.info("test1-{}", i);
            }
        }
    }
    //修饰方法
    public synchronized void test2(){
        for (int i=0;i<10;i++){
            log.info("test2-{}", i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample1 example1 = new SynchronizedExample1();
        SynchronizedExample1 example2 = new SynchronizedExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                example1.test1();
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                example2.test2();
            }
        });
    }
}
