package com.niici.study.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 英雄节点，用于测试链表数据结构
 * @author niici
 * Created by niici on 2021/6/22.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HeroNode {
    // 节点地址
    private int number;
    // 英雄真名
    private String realName;
    // 英雄别名
    private String aliasName;
    // 下一个节点
    private HeroNode next;

    public HeroNode(int number, String realName, String aliasName) {
        this.number = number;
        this.realName = realName;
        this.aliasName = aliasName;
    }

    public HeroNode(int number) {
        this.number = number;
    }
}
