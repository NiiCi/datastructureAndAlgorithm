package com.niici.study.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 英雄节点，用于测试链表数据结构
 * @author niici
 * Created by niici on 2021/6/22.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoubleHeroNode {
    // 节点地址
    private int number;
    // 英雄真名
    private String realName;
    // 英雄别名
    private String aliasName;
    // 上一个节点
    private DoubleHeroNode pre;
    // 下一个节点
    private DoubleHeroNode next;

    public DoubleHeroNode(int number, String realName, String aliasName) {
        this.number = number;
        this.realName = realName;
        this.aliasName = aliasName;
    }

    @Override
    public String toString() {
        return "DoubleHeroNode{" +
                "number=" + number +
                ", realName='" + realName + '\'' +
                ", aliasName='" + aliasName + '\'' +
                ", pre=" + pre +
                '}';
    }
}
