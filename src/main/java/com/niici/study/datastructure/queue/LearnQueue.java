package com.niici.study.datastructure.queue;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * 队列
 * 队列是一个有序列表，可以用数组或者链表来实现
 * 遵循先入先出的原则
 *
 * @author niici
 * Created by niici on 2021/6/11.
 */

@Slf4j
public class LearnQueue {
    public static void main(String[] args) {
        //ArrayQueue arrayQueue = new ArrayQueue(3);
        // 环形队列预留了一个空间，实际空间为4
        ArrayCircularQueue arrayCircularQueue = new ArrayCircularQueue(5);
        char key = ' '; //接受用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出队列");
            System.out.println("h(head): 查看队列头数据");
            System.out.println("e(exit): 退出");
            key = scanner.next().charAt(0); //接受一个字符
            switch (key) {
                case 's' :
                    arrayCircularQueue.showQueue();
                    break;
                case 'a' :
                    System.out.println("请输入(add): 添加数据到队列");
                    int value = scanner.nextInt();
                    arrayCircularQueue.addQueue(value);
                    break;
                case 'g' :
                    try {
                        int result = arrayCircularQueue.getQueue();
                        System.out.printf("g(get): 从队列取出队列: %d\n", result);
                    } catch (Exception e) {
                        log.error(e.getMessage());
                    }
                    break;
                case 'h' :
                    try {
                        int result = arrayCircularQueue.peek();
                        System.out.printf("h(head): 查看队列头数据: %d\n", result);
                    } catch (Exception e) {
                        log.error(e.getMessage());
                    }
                    break;
                case 'e' :
                    System.out.println("e(exit): 退出");
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
    }
}


// 使用数组模拟队列

/**
 * 问题分析并优化
 * 1. 目前数组使用一次就不能继续使用，没有达到复用的效果；
 * 2. 将这个数组使用算法，改进成一个环境数组
 * create by nichao 2021/6/21
 */
@Slf4j
class ArrayQueue {
    // 数组最大容量
    private int maxSize;
    // 指向队列头的前一个位置
    private int front = -1;
    // 指定队列尾的数据
    private int rear = -1;
    // 用于存放数据，模拟队列
    private int[] arr;

    private ObjectMapper objectMapper = new ObjectMapper();

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    public boolean isFull() {
        return rear == maxSize - 1;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public void addQueue(int data) {
        if (isFull()) {
            log.warn("Queue is full, can not add data to queue.");
            return;
        }
        // rear + 1, 并将data赋值到arr中
        rear++;
        arr[rear] = data;
    }

    public int getQueue() {
        if (isEmpty()) {
            //log.warn("Queue is empty, can not get data from queue");
            throw new RuntimeException("Queue is empty, can not get data from queue");
        }
        // front + 1, 队首下标 + 1取队首的元素值，并返回
        front++;
        return arr[front];
    }

    public void showQueue() {
        if (isEmpty()) {
            log.warn("队列为空, 不打印队列");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            log.info("arr[{}]={}", i, arr[i]);
        }
    }

    public int peek(){
        if (isEmpty()) {
            throw new RuntimeException("队列为空, 不打印队列");
        }
        return arr[front + 1];
    }
}

// 数组环形队列
@Slf4j
class ArrayCircularQueue {
    // 数组最大容量
    private int maxSize;
    // 指向队列的第一个元素
    private int front = 0;
    // 指向队列的最后一个元素的后一个位置(空出一个空间做为约定)
    private int rear = 0;
    // 用于存放数据，模拟队列
    private int[] arr;

    private ObjectMapper objectMapper = new ObjectMapper();

    public ArrayCircularQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    /**
     * rear + 1 对maxsize取模，等于front则说明队列已满
     * @return
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * 获取队列中的有效个数
     * rear 有可能会跑到front之前，需要+maxsize保证值的正确性
     */
    public int effectiveNumber() {
        return (rear + maxSize - front) % maxSize;
    }

    public void addQueue(int data) {
        if (isFull()) {
            log.warn("Queue is full, can not add data to queue.");
            return;
        }
        // rear指定队列最后一个元素的后一个位置，可以直接赋值
        arr[rear] = data;
        // rear位置往后移, 考虑到为环形队列，需要取模
        rear = (rear + 1) % maxSize;
    }

    public int getQueue() {
        if (isEmpty()) {
            //log.warn("Queue is empty, can not get data from queue");
            throw new RuntimeException("Queue is empty, can not get data from queue");
        }
        // front执行队列的第一个元素，所以直接取出
        int result = arr[front];
        // front与rear一样，考虑到为环形队列，需要取模
        front = (front + 1) % maxSize;
        return result;
    }

    public void showQueue() {
        if (isEmpty()) {
            log.warn("队列为空, 不打印队列");
            return;
        }
        // 需要从front开始遍历，遍历队列中的有效个数次
        for (int i = front; i < front + effectiveNumber(); i++) {
            // 下标i可能会超过maxsize, 需要取模
            log.info("arr[{}]={}", i % maxSize, arr[i % maxSize]);
        }
    }

    public int peek(){
        if (isEmpty()) {
            throw new RuntimeException("队列为空, 不打印队列");
        }
        return arr[front];
    }
}

// 使用链表模拟队列
@Slf4j
class LinkQueue {
    /**
     * 链表：有序的列表，是以节点的方式组成；
     * 节点：由地址、data域、next域组成；
     *      地址：链表中每个节点都有相应的地址；
     *      data域：用于存放数据；
     *      next域：用于指向下一个节点，如next域为150，则指向的为地址为150的节点；
     * 链表并非是连续的；
     * 链表分为带头节点和不带头节点的链表
     */
}