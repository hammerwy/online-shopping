package com.wy.shopping.config;

import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @date 2019/10/10
 * @description
 */
public class TestThread extends Thread {

    public static void main(String[] args) {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new RandomThread("RandomThread:" + i);
        }
        for(Thread thread : threads) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            thread.start();
        }
    }
}

class RandomThread extends Thread {

    public RandomThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName()+"__"+Thread.currentThread().getId());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
