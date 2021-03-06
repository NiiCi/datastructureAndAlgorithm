package com.niici.study.algorithm.sort;

import java.util.Random;

/**
 * 排序
 * 将一组数据，依制定的顺序进行排列的过程。
 * 排序的分类：
 *  1. 内部排序法：将需要处理的所有数据都加载到内存中进行排序。
 *  2. 外部排序法：数据量过大，无法全部加载到内存中，需要借助外部存储进行排序。
 * 内部排序法：
 *  1. 插入排序：直接插入排序、希尔排序
 *  2. 选择排序：简单选择排序、堆排序
 *  3. 交换排序：冒泡排序、快速排序
 *  4. 归并排序
 *  5. 基数排序
 * 外部排序法：使用内存和外存结合
 *
 * 时间频度和时间复杂度见sort.md
 */
public abstract class BaseSort {
    public static int[] generateRandom() {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            Random random = new Random();
            arr[i] = random.nextInt(8000000);
        }
        return arr;
    }
}
