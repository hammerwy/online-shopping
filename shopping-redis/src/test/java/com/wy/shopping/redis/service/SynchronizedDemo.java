package com.wy.shopping.redis.service;

import java.util.concurrent.Executors;

/**
 * @author wy
 * @date 2019/12/17
 * @description
 */
public class SynchronizedDemo {
    public void method() {

        Executors.newFixedThreadPool(7);
        Executors.newSingleThreadExecutor();
        Executors.newCachedThreadPool();

        synchronized (this) {
            System.out.println("synchronized 代码块");
        }
    }

    public static void main(String[] args) {
        int a = 1;
        final int i = ~a;
        System.out.println(i);
    }
}
