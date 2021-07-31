package com.niici.study.algorithm.sort;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Random;

/**
 * 希尔算法
 * 也是一种插入算法，是简单插入排序经过改进后的一个更高效版本，也称为缩小增量排序。
 *
 * 基本思想：
 *  希尔排序是把数据，按下标的一定增量进行分组，对每组使用直接插入排序算法排序；
 *  随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止；
 * @author niici
 */
@Slf4j
public class HillSort extends BaseSort {
    public static void main(String[] args) {
        int[] arr = generateRandom();
        //int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        long time = System.currentTimeMillis();
        HillSort.changeSort(arr);
        log.info("交换式运行时间: {}ms", System.currentTimeMillis() - time);
        //log.info("交换式希尔排序后的数组: {}", Arrays.toString(arr));

        arr = generateRandom();
        //int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        time = System.currentTimeMillis();
        HillSort.moveSort(arr);
        log.info("移动式运行时间: {}ms", System.currentTimeMillis() - time);
        log.info("移动式希尔排序后的数组: {}", Arrays.toString(arr));
    }

    /**
     * 交换式希尔算法
     * @param arr
     * @return
     */
    public static int[] changeSort(int[] arr) {
        for (int step = arr.length / 2; step > 0; step /= 2) {
            int temp;
            for (int i = step; i < arr.length; i++) {
                // 每step步增量的数进行比较
                for (int j = i - step; j >= 0; j -= step) {
                    if (arr[j] > arr[j + step]) {
                        temp = arr[j + step];
                        arr[j + step] = arr[j];
                        arr[j] = temp;
                    }
                }
            }
        }

        /**
         * 第一轮排序
         * 步长: 10 / 2 = 5
         * 将10个数据，看作是5个分组: [8,3]、[9,5]、[1,4]、[7,6]、[2,0]
         */
        /*for (int i = 5; i < arr.length; i++) {
            // 进行第一轮排序
            // 每5步增量的数进行比较
            for (int j = i - 5; j >= 0; j = j - 5) {
                if (arr[j] > arr[j + 5]) {
                    temp = arr[j + 5];
                    arr[j + 5] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        log.info("希尔排序第一轮排序后的数组: {}", Arrays.toString(arr));*/
        /**
         * 第二轮排序
         * 步长: 5 / 2 = 2
         * 将10个数据，看作是2个分组: [3,1,0,9,7]、[5,6,8,4,2]
         */
        /*for (int i = 2; i < arr.length; i++) {
            // 进行第一轮排序
            // 每2步增量的数进行比较
            for (int j = i - 2; j >= 0; j = j - 2) {
                if (arr[j] > arr[j + 2]) {
                    temp = arr[j + 2];
                    arr[j + 2] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        log.info("希尔排序第二轮排序后的数组: {}", Arrays.toString(arr));*/

        /**
         * 第三轮排序
         * 步长: 2 / 2 = 1
         * 将10个数据，看作是1个分组: [0, 2, 1, 4, 3, 5, 7, 6, 9, 8]
         */
        /*for (int i = 1; i < arr.length; i++) {
            // 进行第三轮排序
            // 每1步增量的数进行比较
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }*/
        //log.info("希尔排序第三轮排序后的数组: {}", Arrays.toString(arr));
        return arr;
    }


    /**
     * 移动式希尔算法
     * @param arr
     * @return
     */
    public static int[] moveSort(int[] arr) {
        for (int step = arr.length / 2; step > 0; step /= 2) {
            // 从step下标处，开始排序
            for (int i = step; i < arr.length; i++) {
                // 定义待插入的下标
                int insertIndex = i;
                // 定义待插入的值
                int insertValue = arr[i];
                // 每step步增量的数进行比较，找到插入的下标
                while (insertIndex - step >= 0 && insertValue < arr[insertIndex - step]) {
                    arr[insertIndex] = arr[insertIndex - step];
                    // 查到待插入的下标
                    insertIndex = insertIndex - step;
                }
                // 将值插入
                arr[insertIndex] = insertValue;
            }
        }
        return arr;
    }
}
