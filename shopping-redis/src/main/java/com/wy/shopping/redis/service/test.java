package com.wy.shopping.redis.service;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wy
 * @description
 * @date 2019-05-23
 */
public class test {
    private static int counter = 0;
    private static ReentrantLock lock = new ReentrantLock();
    public static void offer(){
        lock.lock();
        counter++;
        System.out.println(counter);
        lock.unlock();
    }
}
