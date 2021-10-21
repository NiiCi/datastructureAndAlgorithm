package com.niici.study.juc.sync;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * synchronized线程间通信
 * 例子：创建两个线程，一个线程对值加1，一个线程对值减1
 */
@Slf4j
public class SyncThreadCommunication {
    public static void main(String[] args) {
        Share share = new Share();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }, "incr线程").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }, "decr线程").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }, "incr线程2").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }, "decr线程2").start();
    }
}


@Data
@Slf4j
class Share {
    private Integer number = 0;

    public synchronized void incr() throws InterruptedException {
        // while 解决虚假唤醒，如果使用if，4个线程同时执行时，会出现非1 0的场景
        // 使用while使得线程一直等待
        // 将条件判断放在while中，避免条件唤醒
        while (number != 0) { // 判断是否等于0，不等于0，则等待
            this.wait();
        }
        number++;
        log.info(Thread.currentThread().getName() + "-- number:" + number);
        // 通知其他线程
        this.notifyAll();
    }

    public synchronized void decr() throws InterruptedException {
        while (number == 0) {
            this.wait();
        }
        number--;
        log.info(Thread.currentThread().getName() + "-- number:" + number);
        this.notifyAll();
    }
}
