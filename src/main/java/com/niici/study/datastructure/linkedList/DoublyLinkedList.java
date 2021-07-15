package com.niici.study.datastructure.linkedList;

import com.niici.study.bean.DoubleHeroNode;
import com.niici.study.bean.HeroNode;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.Stack;

/**
 * 双向链表
 * 单向链表缺点分析：
 * 1. 单向链表，查找的方向只能是一个方向，而双向链表可以向前或者向后查找。
 * 2. 单向链表不能自我删除，需要依靠辅助节点，双向链表可以自我删除。
 * @author niici
 * Created by niici on 2021/6/29.
 */
@Slf4j
@Data
public class DoublyLinkedList extends LearnLinkedList {
    // 头节点, 仅存放next, 指向下一个节点
    private DoubleHeroNode headNode;

    public DoublyLinkedList() {
        headNode = new DoubleHeroNode();
    }

    public static void main(String[] args) {
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
        /*doublyLinkedList.headAdd(new DoubleHeroNode(1, "宋江", "宋江"));
        doublyLinkedList.headAdd(new DoubleHeroNode(2, "卢俊义", "玉麒麟"));
        doublyLinkedList.headAdd(new DoubleHeroNode(3, "吴用", "智多星"));
        doublyLinkedList.headAdd(new DoubleHeroNode(4, "林冲", "豹子头"));
        doublyLinkedList.headAdd(new DoubleHeroNode(5, "鲁智深", "鲁智深"));
        doublyLinkedList.headAdd(new DoubleHeroNode(6, "李逵", "李逵"));*/
        doublyLinkedList.addByDESC(new DoubleHeroNode(1, "宋江", "宋江"));
        doublyLinkedList.addByDESC(new DoubleHeroNode(6, "李逵", "李逵"));
        doublyLinkedList.addByDESC(new DoubleHeroNode(3, "吴用", "智多星"));
        doublyLinkedList.addByDESC(new DoubleHeroNode(2, "卢俊义", "玉麒麟"));
        doublyLinkedList.addByDESC(new DoubleHeroNode(5, "鲁智深", "鲁智深"));
        doublyLinkedList.addByDESC(new DoubleHeroNode(4, "林冲", "豹子头"));
        log.info(doublyLinkedList.count(doublyLinkedList.getHeadNode()) + "");
        doublyLinkedList.showAll();
        doublyLinkedList.modify(new DoubleHeroNode(3, "李逵", "黑旋风"));
        log.info("--------------------modify--------------------");
        doublyLinkedList.showAll();
        doublyLinkedList.delete(new DoubleHeroNode(3, "李逵", "黑旋风"));
        log.info("--------------------delete--------------------");
        doublyLinkedList.showAll();
        /*sinplyLinkedList.addByASC(3, "李逵", "李逵");
        sinplyLinkedList.showAll();
        log.info(sinplyLinkedList.find(2).toString());
        log.info("count: -------- {}", sinplyLinkedList.count(sinplyLinkedList.getHeadNode()));
        log.info("findLastNode: -------- {}", sinplyLinkedList.findLastNode(sinplyLinkedList.getHeadNode(), 3));
        log.info("单链表的逆序打印: -----------");
        sinplyLinkedList.reverseByLinkList(sinplyLinkedList).showAll();
        sinplyLinkedList.showAll();
        log.info("单链表的逆序打印(通过栈实现): -----------");
        sinplyLinkedList.reverseByStack(sinplyLinkedList);*/
    }

    /**
     * 尾插法
     * @param heroNode
     */
    public void taildAdd(DoubleHeroNode heroNode) {
        // 1.找到链表的最后一个节点
        // 2.将当前最后一个节点的next域指向新增节点
        // 3.将新增节点的pre指向当前最后一个节点
        DoubleHeroNode tempNode = headNode;
        while (true) {
            DoubleHeroNode nextNode = tempNode.getNext();
            // 节点的next为空时，则已到达最后一个节点, break时, tempNode已是最后一个节点
            if (Objects.isNull(nextNode)) {
                break;
            }
            // 不为空, 则tempNode为原tempNode的next
            tempNode = nextNode;
        }
        tempNode.setNext(heroNode);
        heroNode.setPre(tempNode);
    }

    /**
     * 头插法
     * @param heroNode
     */
    public void headAdd(DoubleHeroNode heroNode) {
        // 1.找到链表的第一个节点
        // 2.将新增节点与第一个节点交换位置, 实现头插法
        DoubleHeroNode tempNode = headNode;
        DoubleHeroNode nextNode = tempNode.getNext();
        if (Objects.nonNull(nextNode)) {
            heroNode.setNext(nextNode);
            heroNode.setPre(tempNode);
            nextNode.setPre(heroNode);
        }
        tempNode.setNext(heroNode);
    }

    public void addByASC(DoubleHeroNode heroNode) {
        // 1.找到链表的最后一个节点
        // 2.将当前最后一个节点的next域指向新增节点
        // 3.将新增节点的pre指向当前最后一个节点
        DoubleHeroNode tempNode = headNode;
        while (true) {
            DoubleHeroNode nextNode = tempNode.getNext();
            // 节点的next为空时，则已到达最后一个节点, break时, tempNode已是最后一个节点
            if (Objects.isNull(nextNode)) {
                break;
            }
            // 排序, 如果新增节点的number小于当前节点的next的number, 则交换位置
            if (heroNode.getNumber() < nextNode.getNumber()) {
                nextNode.setPre(heroNode);
                heroNode.setNext(nextNode);
                break;
            }
            // 不为空, 则tempNode为原tempNode的next
            tempNode = nextNode;
        }
        heroNode.setPre(tempNode);
        tempNode.setNext(heroNode);
    }

    public void addByDESC(DoubleHeroNode heroNode) {
        // 1.找到链表的最后一个节点
        // 2.将当前最后一个节点的next域指向新增节点
        DoubleHeroNode tempNode = headNode;
        while (true) {
            DoubleHeroNode nextNode = tempNode.getNext();
            // 节点的next为空时，则已到达最后一个节点, break时, tempNode已是最后一个节点
            if (Objects.isNull(nextNode)) {
                break;
            }
            // 排序, 如果新增节点的numberd大于当前节点的next的number, 则交换位置
            if (heroNode.getNumber() > nextNode.getNumber()) {
                nextNode.setPre(heroNode);
                heroNode.setNext(nextNode);
                break;
            }
            // 不为空, 则tempNode为原tempNode的next
            tempNode = nextNode;
        }
        heroNode.setPre(tempNode);
        tempNode.setNext(heroNode);
    }

    public void modify(DoubleHeroNode heroNode) {
        DoubleHeroNode tempNode = headNode;
        if (Objects.isNull(tempNode.getNext())) {
            log.warn("linked list is empty.");
            return;
        }
        boolean isSuccessed = false;
        while (true) {
            DoubleHeroNode nextNode = tempNode.getNext();
            if (Objects.isNull(nextNode)) {
                break;
            }
            if (heroNode.getNumber() == nextNode.getNumber()) {
                nextNode.setRealName(heroNode.getRealName());
                nextNode.setAliasName(heroNode.getAliasName());
                isSuccessed = true;
                break;
            }
            // 不为空, 则tempNode为原tempNode的next
            tempNode = nextNode;
        }
        if (!isSuccessed) {
            log.error("modify hero data to linked list error.");
        }
    }

    public void delete(DoubleHeroNode heroNode) {
        DoubleHeroNode tempNode = headNode;
        if (Objects.isNull(tempNode.getNext())) {
            log.warn("linked list is empty.");
            return;
        }
        boolean isSuccessed = false;
        while (true) {
            DoubleHeroNode nextNode = tempNode.getNext();
            if (Objects.isNull(nextNode)) {
                break;
            }
            // 删除时，将待删除节点的pre节点的next指向待删除节点的next
            // 将待删除节点的next节点的pre指向待删除节点的pre
            if (heroNode.getNumber() == nextNode.getNumber()) {
                nextNode.getPre().setNext(nextNode.getNext());
                // 当为最后一个节点时, 不需要执行交换操作
                if (Objects.nonNull(nextNode.getNext())) {
                    nextNode.getNext().setPre(nextNode.getPre());
                }
                isSuccessed = true;
                break;
            }
            // 不为空, 则tempNode为原tempNode的next
            tempNode = nextNode;
        }
        if (!isSuccessed) {
            log.error("delete hero data to double linked list error.");
        }
    }

    public DoubleHeroNode find(int number) {
        DoubleHeroNode tempNode = headNode;
        if (Objects.isNull(tempNode.getNext())) {
            log.warn("linked list is empty.");
            return null;
        }
        while (true) {
            DoubleHeroNode nextNode = tempNode.getNext();
            if (Objects.isNull(nextNode)) {
                break;
            }
            // 删除时, 直接将待删除节点的next赋值给当前节点的next即可
            if (number == nextNode.getNumber()) {
                return nextNode;
            }
            // 不为空, 则tempNode为原tempNode的next
            tempNode = nextNode;
        }
        return null;
    }

    /**
     * 遍历双向链表(正序遍历)
     */
    public void showAll() {
        DoubleHeroNode tempNode = headNode.getNext();
        if (Objects.isNull(tempNode)) {
            log.warn("double linked list is empty.");
        }
        while (true) {
            log.info(tempNode.toString());
            if (Objects.isNull(tempNode.getNext())) {
                break;
            }
            tempNode = tempNode.getNext();
        }
    }


    /**
     * 面试题：获取单链表的节点个数(如果是带头节点链表, 需要不统计头节点)
     * @param headNode 头节点
     * @return
     */
    public int count(DoubleHeroNode headNode) {
        DoubleHeroNode tempNode = headNode;
        if (Objects.isNull(tempNode.getNext())) {
            return 0;
        }
        int count = 0;
        // 不统计头节点
        DoubleHeroNode nextNode = tempNode.getNext();
        while(Objects.nonNull(nextNode)) {
            count++;
            nextNode = nextNode.getNext();
        }
        return count;
    }

    /**
     * 面试题：查找单链表中的倒数第k个节点 【新浪面试题】
     * @param headNode 头节点
     * @param index 倒数第n个节点
     * @return
     */
    public DoubleHeroNode findLastNode(DoubleHeroNode headNode, int index) {
        // 1.获取链表的长度
        // 2.从链表的第一个有效节点开始, 遍历size-index次, 获取节点
        int size = count(headNode);
        if (size == 0) {
            return null;
        }
        if (size < index) {
            log.error("index out of size.");
            return null;
        }
        int length = size - index;
        DoubleHeroNode tempNode = headNode;
        if (Objects.isNull(tempNode.getNext())) {
            return null;
        }
        // 不统计头节点
        DoubleHeroNode nextNode = tempNode.getNext();
        while(length > 0) {
            length--;
            nextNode = nextNode.getNext();
        }
        return nextNode;
    }

    /**
     * 面试题：单链表的逆序打印 【腾讯面试题】
     * 方式1：头插法
     * @param sinplyLinkedList 待反转单链表
     * @return
     */
    public SinplyLinkedList reverseByLinkList(SinplyLinkedList sinplyLinkedList) {
        SinplyLinkedList reverseSinplyLinkedList = new SinplyLinkedList();
        // 遍历原单项链表, 将第一个节点取出, 并使用头插法(插入到头部), 加入到反转链表中
        HeroNode headNode = sinplyLinkedList.getHeadNode();
        if (Objects.isNull(headNode)) {
            log.warn("single linked list is empty.");
            return reverseSinplyLinkedList;
        }
        HeroNode nextNode = headNode.getNext();
        while(Objects.nonNull(nextNode)) {
            // 将节点取出, 头插到新链表中
            HeroNode heroNode = new HeroNode();
            heroNode.setNumber(nextNode.getNumber());
            heroNode.setRealName(nextNode.getRealName());
            heroNode.setAliasName(nextNode.getAliasName());
            //头插法
            reverseSinplyLinkedList.headAdd(heroNode);
            // 将head的next域指向下一个节点
            nextNode = nextNode.getNext();
        }
        return reverseSinplyLinkedList;
    }

    /**
     * 面试题：单链表的逆序打印 【腾讯面试题】
     * 方式2：可以使用栈，将各个节点压入栈中, 利用栈先进后出的特点实现逆序打印 【推荐使用】
     * @param sinplyLinkedList 待反转单链表
     * @return
     */
    public void reverseByStack(DoublyLinkedList doublyLinkedList) {
        DoublyLinkedList reverseSinplyLinkedList = new DoublyLinkedList();
        // 遍历原单项链表, 将第一个节点取出, 并使用头插法(插入到头部), 加入到反转链表中
        DoubleHeroNode headNode = doublyLinkedList.getHeadNode();
        if (Objects.isNull(headNode)) {
            log.warn("single linked list is empty.");
            return;
        }
        Stack<DoubleHeroNode> heroNodeStack = new Stack<>();
        DoubleHeroNode nextNode = headNode.getNext();
        while(Objects.nonNull(nextNode)) {
            // 将节点取出, 头插到新链表中
            DoubleHeroNode heroNode = new DoubleHeroNode();
            heroNode.setNumber(nextNode.getNumber());
            heroNode.setRealName(nextNode.getRealName());
            heroNode.setAliasName(nextNode.getAliasName());
            // 使用栈插入, 实现逆序打印
            heroNodeStack.add(heroNode);
            // 将head的next域指向下一个节点
            nextNode = nextNode.getNext();
        }
        while(heroNodeStack.size() > 0) {
            log.info(heroNodeStack.pop().toString());
        }
    }
}
