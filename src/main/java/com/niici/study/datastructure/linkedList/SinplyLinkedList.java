package com.niici.study.datastructure.linkedList;

import com.niici.study.bean.HeroNode;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.Stack;

/**
 * 单向链表
 * @author niici
 * Created by niici on 2021/6/22.
 */
@Slf4j
@Data
public class SinplyLinkedList extends LearnLinkedList {
    // 头节点, 仅存放next, 指向下一个节点
    private HeroNode headNode;

    public SinplyLinkedList() {
        headNode = new HeroNode();
    }

    public static void main(String[] args) {
        SinplyLinkedList sinplyLinkedList = new SinplyLinkedList();
        sinplyLinkedList.addByASC(2, "宋江", "宋江");
        sinplyLinkedList.addByASC(1, "林冲", "豹子头");
        sinplyLinkedList.addByASC(4, "鲁智深", "鲁智深");
        sinplyLinkedList.addByASC(3, "李逵", "李逵");
        sinplyLinkedList.showAll();
        sinplyLinkedList.modify(new HeroNode(3, "李逵", "黑旋风"));
        sinplyLinkedList.showAll();
        sinplyLinkedList.delete(new HeroNode(3, "李逵", "黑旋风"));
        sinplyLinkedList.showAll();
        sinplyLinkedList.addByASC(3, "李逵", "李逵");
        sinplyLinkedList.showAll();
        log.info(sinplyLinkedList.find(2).toString());
        log.info("count: -------- {}", sinplyLinkedList.count(sinplyLinkedList.getHeadNode()));
        log.info("findLastNode: -------- {}", sinplyLinkedList.findLastNode(sinplyLinkedList.getHeadNode(), 3));
        log.info("单链表的逆序打印: -----------");
        sinplyLinkedList.reverseByLinkList(sinplyLinkedList).showAll();
        sinplyLinkedList.showAll();
        log.info("单链表的逆序打印(通过栈实现): -----------");
        sinplyLinkedList.reverseByStack(sinplyLinkedList);
    }

    /**
     * 尾插法
     * @param heroNode
     */
    public void taildAdd(HeroNode heroNode) {
        // 1.找到链表的最后一个节点
        // 2.将当前最后一个节点的next域指向新增节点
        HeroNode tempNode = headNode;
        while (true) {
            HeroNode nextNode = tempNode.getNext();
            // 节点的next为空时，则已到达最后一个节点, break时, tempNode已是最后一个节点
            if (Objects.isNull(nextNode)) {
                break;
            }
            // 不为空, 则tempNode为原tempNode的next
            tempNode = nextNode;
        }
        tempNode.setNext(heroNode);
    }

    /**
     * 头插法
     * @param heroNode
     */
    public void headAdd(HeroNode heroNode) {
        // 1.找到链表的第一个节点
        // 2.将新增节点与第一个节点交换位置, 实现头插法
        HeroNode tempNode = headNode;
        HeroNode nextNode = tempNode.getNext();
        if (Objects.nonNull(nextNode)) {
            heroNode.setNext(nextNode);
        }
        tempNode.setNext(heroNode);
    }

    public void addByASC(int number, String realName, String aliasName) {
        HeroNode heroNode = new HeroNode(number, realName, aliasName);
        // 1.找到链表的最后一个节点
        // 2.将当前最后一个节点的next域指向新增节点
        HeroNode tempNode = headNode;
        while (true) {
            HeroNode nextNode = tempNode.getNext();
            // 节点的next为空时，则已到达最后一个节点, break时, tempNode已是最后一个节点
            if (Objects.isNull(nextNode)) {
                break;
            }
            // 排序, 如果新增节点的number小于当前节点的next的number, 则交换位置
            if (number < nextNode.getNumber()) {
                heroNode.setNext(nextNode);
                //tempNode.setNext(heroNode);
                break;
            }
            // 不为空, 则tempNode为原tempNode的next
            tempNode = nextNode;
        }
        tempNode.setNext(heroNode);
    }

    public void addByDESC(HeroNode heroNode) {
        // 1.找到链表的最后一个节点
        // 2.将当前最后一个节点的next域指向新增节点
        HeroNode tempNode = headNode;
        while (true) {
            HeroNode nextNode = tempNode.getNext();
            // 节点的next为空时，则已到达最后一个节点, break时, tempNode已是最后一个节点
            if (Objects.isNull(nextNode)) {
                break;
            }
            // 排序, 如果新增节点的numberd大于当前节点的next的number, 则交换位置
            if (heroNode.getNumber() > nextNode.getNumber()) {
                heroNode.setNext(nextNode);
                //tempNode.setNext(heroNode);
                break;
            }
            // 不为空, 则tempNode为原tempNode的next
            tempNode = nextNode;
        }
        tempNode.setNext(heroNode);
    }

    public void modify(HeroNode heroNode) {
        HeroNode tempNode = headNode;
        if (Objects.isNull(tempNode.getNext())) {
            log.warn("linked list is empty.");
            return;
        }
        boolean isSuccessed = false;
        while (true) {
            HeroNode nextNode = tempNode.getNext();
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

    public void delete(HeroNode heroNode) {
        HeroNode tempNode = headNode;
        if (Objects.isNull(tempNode.getNext())) {
            log.warn("linked list is empty.");
            return;
        }
        boolean isSuccessed = false;
        while (true) {
            HeroNode nextNode = tempNode.getNext();
            if (Objects.isNull(nextNode)) {
                break;
            }
            // 删除时, 直接将待删除节点的next赋值给当前节点的next即可
            if (heroNode.getNumber() == nextNode.getNumber()) {
                tempNode.setNext(nextNode.getNext());
                isSuccessed = true;
                break;
            }
            // 不为空, 则tempNode为原tempNode的next
            tempNode = nextNode;
        }
        if (!isSuccessed) {
            log.error("delete hero data to linked list error.");
        }
    }

    public HeroNode find(int number) {
        HeroNode tempNode = headNode;
        if (Objects.isNull(tempNode.getNext())) {
            log.warn("linked list is empty.");
            return null;
        }
        while (true) {
            HeroNode nextNode = tempNode.getNext();
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

    public void showAll() {
        HeroNode tempNode = headNode;
        while (true) {
            if (Objects.isNull(tempNode.getNext())) {
                break;
            }
            log.info(tempNode.toString());
            tempNode = tempNode.getNext();
        }
    }


    /**
     * 面试题：获取单链表的节点个数(如果是带头节点链表, 需要不统计头节点)
     * @param headNode 头节点
     * @return
     */
    public int count(HeroNode headNode) {
        HeroNode tempNode = headNode;
        if (Objects.isNull(tempNode.getNext())) {
            return 0;
        }
        int count = 0;
        // 不统计头节点
        HeroNode nextNode = tempNode.getNext();
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
    public HeroNode findLastNode(HeroNode headNode, int index) {
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
        HeroNode tempNode = headNode;
        if (Objects.isNull(tempNode.getNext())) {
            return null;
        }
        // 不统计头节点
        HeroNode nextNode = tempNode.getNext();
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
    public void reverseByStack(SinplyLinkedList sinplyLinkedList) {
        SinplyLinkedList reverseSinplyLinkedList = new SinplyLinkedList();
        // 遍历原单项链表, 将第一个节点取出, 并使用头插法(插入到头部), 加入到反转链表中
        HeroNode headNode = sinplyLinkedList.getHeadNode();
        if (Objects.isNull(headNode)) {
            log.warn("single linked list is empty.");
            return;
        }
        Stack<HeroNode> heroNodeStack = new Stack<>();
        HeroNode nextNode = headNode.getNext();
        while(Objects.nonNull(nextNode)) {
            // 将节点取出, 头插到新链表中
            HeroNode heroNode = new HeroNode();
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
