package com.niici.study.juc.lock;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentranLock 售票例子
 * 可重入锁：可重复获取锁
 *
 * 多线程变成步骤：
 * 1. 创建资源类，在资源类创建属性和操作方法
 * 2. 在资源类操作方法：
 *  1) 判断
 *  2) 处理
 *  3) 通知
 * 3. 创建多个现成，调用资源类的操作方法
 *
 * @author niici
 */
public class SaleTickets {
    public static void main(String[] args) {
        Integer ticketSize = 30;
        Ticket ticket = new Ticket(ticketSize);
        while (ticket.getNumber() > 0) {
            new Thread(() -> {
                ticket.sale();
            }, "售票员1").start();

            new Thread(() -> {
                ticket.sale();
            }, "售票员2").start();

            new Thread(() -> {
                ticket.sale();
            }, "售票员3").start();
        }

    }
}

@Data
@Slf4j
@NoArgsConstructor
class Ticket {
    private final ReentrantLock lock = new ReentrantLock();
    private Integer number;

    public Ticket(Integer ticketSize) {
        this.number = ticketSize;
    }

    public void sale() {
        if (lock.tryLock()) {
            try {
                if (number > 0) {
                    log.info(Thread.currentThread().getName() + "卖出, 剩余：" + --number);
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
