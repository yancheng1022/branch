package com.kaka.concurrency.lock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeadLock implements Runnable{
    public int flag = 1;
    private static Object o1 = new Object(),o2 = new Object();
    @Override
    public void run() {
        log.info("flag {}", flag);
        if (flag == 1){
            synchronized (o1){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2){
                    log.info("flag 1 finished");
                }
            }
        }
        if (flag == 0){
            synchronized (o2){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1){
                    log.info("flag 2 finished");
                }
            }
        }
    }

    public static void main(String[] args) {
        DeadLock lock1 = new DeadLock();
        DeadLock lock2 = new DeadLock();
        lock1.flag = 1;
        lock2.flag = 0;
        new Thread(lock1).start();
        new Thread(lock2).start();
    }
}
