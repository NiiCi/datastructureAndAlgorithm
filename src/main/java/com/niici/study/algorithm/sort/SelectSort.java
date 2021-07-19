package com.niici.study.algorithm.sort;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Random;

/**
 * 选择排序
 * 是从 待排序的数据中，按制定的规则轩主某一元素，再按照规定交换位置后达到排序的目的。
 * @author niici
 */
@Data
@Slf4j
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            Random random = new Random();
            arr[i] = random.nextInt(80000);
        }
        long time = System.currentTimeMillis();
        SelectSort.sort(arr);
        log.info("运行时间: {}ms", System.currentTimeMillis() - time);
        log.info("选择排序后的数组: {}", Arrays.toString(arr));
    }

    public static int[] sort(int[] arr) {
        /**
         * 选择排序共有 数组大小 - 1轮排序。
         * 每一轮排序，又是一个循环。
         * 假设当前的数为最小数，与相邻数比较，小于则设相邻数为最小数，依次类推，找到当前数组中的最小数。
         * 将最小数与原最小数交换位置。
         */
        for (int i = 0; i < arr.length - 1; i++) {
            int min = arr[i];
            // 每次进入当前循环时，第i个数之前的数已经是有序的，因此内层for是由j = i开始
            for (int j = i; j < arr.length - 1; j++) {
                if (min > arr[j + 1]) {
                    min = arr[j + 1];
                    arr[j + 1] = arr[i];
                    arr[i] = min;
                }
            }
        }
        return arr;
    }
}
