package com.niici.study.algorithm.sort;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Random;

/**
 * 选择排序
 * 是从 待排序的数据中，按指定的规则轩主某一元素，再按照规定交换位置后达到排序的目的。
 * @author niici
 */
@Data
@Slf4j
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            Random random = new Random();
            arr[i] = random.nextInt(8000000);
        }
        long time = System.currentTimeMillis();
        SelectSort.sort(arr);
        log.info("运行时间: {}ms", System.currentTimeMillis() - time);
        log.info("选择排序后的数组: {}", Arrays.toString(arr));
    }

    /**
     * 选择排序共有 数组大小 - 1轮排序。
     * 每一轮排序，又是一个循环。
     * 内层循环：
     *  假设当前的数为最小数，与相邻数比较，小于则设相邻数为最小数，更新下标最小值下标。
     * 外层循环：
     *  从数组第一个数开始遍历，将内层循环找出的最小值下标对应的值，与当前外层循环下标对应的数进行交换。
     * 将最小数与原最小数交换位置。
     */
    public static int[] sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            // 初始最小值下标
            int minIndex = i;
            int min = arr[minIndex];
            // 起始位置为当前最小值的下一个位置，即为i + 1
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            // 值交换
            arr[minIndex] = arr[i];
            arr[i] = min;
        }
        return arr;
    }
}
