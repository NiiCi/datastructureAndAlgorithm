package com.niici.study.algorithm.sort;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Random;

/**
 * 插入排序
 * 属于内部排序法，是对 待排序的元素以插入的方式找寻该元素适当的位置，以达到排序的目的。
 * 基本思想：
 *  把 n个 待排序的元素看成一个有序表和一个无序表，开始时有序表中只包含一个元素，无序表中包含有n-1个元素，
 *  排序过程中每次从无序表中取出第一个元素，把他的排序码依次与有序表元素的排序码进行比较，将它插入到有序表中的适当位置，成为新的有序表。
 * @author niici
 */
@Slf4j
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            Random random = new Random();
            arr[i] = random.nextInt(8000000);
        }
        //int[] arr = {101, 34, 119, 1};
        long time = System.currentTimeMillis();
        InsertSort.sort(arr);
        log.info("运行时间: {}ms", System.currentTimeMillis() - time);
        log.info("插入排序后的数组: {}", Arrays.toString(arr));
    }


    public static int[] sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            // 定义待插入的数
            int insertValue = arr[i];
            // 待插入的下标，从待插入的数前一个数开始
            int insertIndex = i - 1;
            /**
             * 给insertValue 寻找插入的位置
             * 说明：
             *  1. insertIndex >= 0 保证在insertValue 找到插入位置时，不会越界
             *  2. insertValue 小于 insertIndex下标所对应的数
             */
            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                // insertValue 小于 insertIndex下标所对应的数时，将insertIndex对应数向后移动一个位置
                arr[insertIndex + 1] = arr[insertIndex];
                // insertIndex 向前移动一个位置 (在有序列表中，查找下一个合适的插入位置)
                insertIndex--;
            }
            // 找到合适的插入位置后，将待插入的数插入有序列表
            arr[insertIndex + 1] = insertValue;
        }
        return arr;
    }
}
