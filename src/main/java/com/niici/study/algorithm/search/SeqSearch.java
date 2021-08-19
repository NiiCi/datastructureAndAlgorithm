package com.niici.study.algorithm.search;

import lombok.extern.slf4j.Slf4j;

/**
 * 顺序查找
 * 是逐一比对，发现有相同值，就返回下标
 *
 * @author niici
 */
@Slf4j
public class SeqSearch extends BaseSearch {
    public static void main(String[] args) {
        int[] arr = {1, 9, 11, -1, 34, 89};
        long time = System.currentTimeMillis();
        int index = SeqSearch.search(arr, 12);
        log.info("运行时间: {}ms", System.currentTimeMillis() - time);
        if (index != -1) {
            log.info("顺序查找匹配到的数的下标为：{}", index);
        } else {
            log.error("顺序查找未找到匹配的数");
        }
    }

    /**
     * 实现找到一个匹配的数的下标就返回的顺序查找
     *
     * @param arr
     * @param value
     * @return
     */
    public static int search(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
