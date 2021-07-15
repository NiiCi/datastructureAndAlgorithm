package com.niici.study.datastructure.linkedList;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * 单向环形列表
 * 约瑟夫(Josephu)问题：
 *  设编号为1,2, ...n的n个人围坐一圈
 *  约定编号为k(1<=k<=n)的人从1开始报数，数到m的那个人出列
 *  依次类推，直到所有人出列位置，由此产生一个出队编号的序列。
 *  提示：
 *  1. 用一个不带头节点的循环链表来处理，先构成一个有n个节点的单向环形链表
 *  2. 然后由k节点开始从1开始计数，计数到m时，对应节点从链表中删除
 *  3. 然后再从被删除节点的下一个节点开始从1开始计数，直到最后一个节点从链表中删除
 *
 * @author niici
 * Created by niici on 2021/6/11.
 */
@Slf4j
@Data
public class SinglyCircularLinkedList {
    private Boy firstNode; //first节点
    private Boy currentNode;
    private int size;

    public SinglyCircularLinkedList(int size) {
        this.size = size;
        this.init(size);
    }

    public static void main(String[] args) {
        SinglyCircularLinkedList singlyCircularLinkedList = new SinglyCircularLinkedList(25);
        singlyCircularLinkedList.showAll();
        singlyCircularLinkedList.josephu(2, 2);
    }

    /**
     * 初始化单向环形列表
     * @param size
     */
    private void init(int size) {
        if (size < 1) {
            log.error("size is invaild.");
            return;
        }
        Boy currentBoy = null; // 临时变量，用于帮助构建环形链表
        for (int i = 1; i <= size ; i++) {
            // 根据size初始化
            Boy boy = new Boy(i);
            if (i == 1) {
                // 编号等于1, 初始化时则认为是第一个节点
                firstNode = boy;
                // 将第一个节点的next指向自己, 构成环形(如果size为1)
                firstNode.setNext(firstNode);
                // currentBoy指向第一个节点
                currentBoy = firstNode;
            }
            // 非首节点添加, 则将currentBoy的next指向新增节点
            currentBoy.setNext(boy);
            // 新增节点的next指向首节点, 构成环形
            boy.setNext(firstNode);
            // currentBoy变量向后移动
            currentBoy = boy;
        }
    }

    /**
     * 遍历单向环形链表
     */
    private void showAll() {
        if (Objects.isNull(firstNode) || Objects.isNull(firstNode.getNext())) {
            log.info("Singly circular linked list is empty.");
            return;
        }
        // 将第一个节点赋值给临时变量
        Boy tempNode = firstNode;
        while(true) {
            log.info(tempNode.toString());
            // 当临时节点的下一个节点等于第一个节点, 则认为已遍历完成
            if (tempNode.getNext().equals(firstNode)) {
                break;
            }
            // 临时节点位置向后移动
            tempNode = tempNode.getNext();
        }
    }

    /**
     *
     * @param start 从第几个开始计数
     * @param step 数几次
     */
    private void josephu(int start, int step) {
        if (start < 1 || step < 1 || start > size) {
            log.error("params is error.");
            return;
        }
        // 获取开始计数的节点
        Boy startNode = getStartNode(start);
        // 获取最后一个节点
        Boy lastNode = getLastNode(startNode);
        while (true) {
            // last节点和start节点编号相同时, 环形链表中只剩下一个节点
            if (startNode.getNumber() == startNode.getNext().getNumber()) {
                log.info("number: {}", startNode.getNumber());
                break;
            }
            // start节点和last节点移动 step - 1 次
            for (int i = 0; i < step - 1; i++) {
                startNode = startNode.getNext();
                lastNode = lastNode.getNext();
            }
            log.info("number: {}", startNode.getNumber());
            // 下一次报数时的起始节点为当前节点的next节点
            startNode = startNode.getNext();
            // last节点的next节点指向新的start节点, 完成出列
            lastNode.setNext(startNode);
        }
    }

    /**
     * 获取last节点
     * @param startNode 开始计数的start节点
     * @return
     */
    private Boy getLastNode(Boy startNode) {
        Boy lastNode = startNode;
        while (true) {
            if (lastNode.getNext().getNumber() == startNode.getNumber()) {
                break;
            }
            lastNode = lastNode.getNext();
        }
        log.info("lastNode: {}", lastNode.getNumber());
        return lastNode;
    }

    /**
     * 获取开始节点
     * @param start
     * @return
     */
    private Boy getStartNode(int start) {
        Boy startNode = firstNode;
        for (int i = 1; i <= start; i++) {
            if (startNode.getNumber() == start) {
                break;
            }
            startNode = startNode.getNext();
        }
        log.info("startNode: {}", startNode.getNumber());
        return startNode;
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
class Boy {
    // 编号
    private int number;
    // 下一个节点, 默认为null
    private Boy next;

    public Boy(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "number=" + number +
                '}';
    }
}
