package com.niici.study.juc.sync;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * synchronized 售票例子
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
@AllArgsConstructor
@NoArgsConstructor
class Ticket {
    private Integer number;
    public synchronized void sale() {
        if (number > 0) {
            log.info(Thread.currentThread().getName() + "卖出, 剩余：" + --number);
        }
    }
}
