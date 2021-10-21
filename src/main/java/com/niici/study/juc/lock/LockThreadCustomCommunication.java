package com.niici.study.juc.lock;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock线程间定制通信(指定线程执行顺序)
 * 例子：创建两个线程，一个线程对值加1，一个线程对值减1
 *
 * @author niici
 */
@Slf4j
public class LockThreadCustomCommunication {
    public static void main(String[] args) {
        ShareResource share = new ShareResource();
        new Thread(() -> {
            try {
                for (int i = 1; i <= 10 ; i++) {
                    share.print5(i);
                }
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        }, "打印5次的线程").start();

        new Thread(() -> {
            try {
                for (int i = 1; i <= 10 ; i++) {
                    share.print10(i);
                }
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        }, "打印10次的线程").start();

        new Thread(() -> {
            try {
                for (int i = 1; i <= 10 ; i++) {
                    share.print15(i);
                }
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        }, "打印15次的线程").start();
    }
}


@Data
@Slf4j
class ShareResource {
    private Integer number = 1;
    private final ReentrantLock lock = new ReentrantLock();
    // 使用Condition实现线程间的通信
    // 创建3个condition 对应3个线程
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print5(int loop) throws InterruptedException {
        lock.lock();
        try {
            while (number != 1) {
                condition1.await();
            }
            for (int i = 1; i <= 5; i++) {
                log.info(Thread.currentThread().getName() + "打印 " + i + "次" + ", 第" + loop + "轮");
            }
            number = 2;
            condition2.signal();
        } finally {
            lock.unlock();
        }
    }

    public void print10(int loop) throws InterruptedException {
        lock.lock();
        try {
            while (number != 2) {
                condition2.await();
            }
            for (int i = 1; i <= 10; i++) {
                log.info(Thread.currentThread().getName() + "打印 " + i + "次" + ", 第" + loop + "轮");
            }
            number = 3;
            condition3.signal();
        } finally {
            lock.unlock();
        }
    }

    public void print15(int loop) throws InterruptedException {
        lock.lock();
        try {
            while (number != 3) {
                condition3.await();
            }
            for (int i = 1; i <= 15; i++) {
                log.info(Thread.currentThread().getName() + "打印 " + i + "次" + ", 第" + loop + "轮");
            }
            number = 1;
            condition1.signal();
        } finally {
            lock.unlock();
        }
    }
}
