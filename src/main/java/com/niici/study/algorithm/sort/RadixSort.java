package com.niici.study.algorithm.sort;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 基数排序
 *  又称"桶子法"或者bin sort，通过键值的各个位的值，将要排序的元素分配值某些"桶"中，达到排序的作用。
 *  基数排序法属于稳定性的排序，基数排序法是效率高的稳定性排序法。
 *  基数排序是桶排序的拓展。
 *  基本思路：将整数按位数切割成不同的数字，然后按每个位数分别比较。
 */
@Slf4j
public class RadixSort extends BaseSort{
    public static void main(String[] args) {
        int[] arr = generateRandom();
        //int[] arr = {53, 3, 542, 748, 14, 214};
        long time = System.currentTimeMillis();
        RadixSort.sort(arr);
        log.info("运行时间: {}ms", System.currentTimeMillis() - time);
        log.info("基数排序后的数组: {}", Arrays.toString(arr));
    }


    public static int[] sort(int[] arr) {
        int maxElement = getMaxElement(arr);
        /**
         * {53, 3, 542, 748, 14, 214}
         * 第一轮排序：
         *  根据每个元素的个位数数值，将元素保存到个位数值对应的桶中(0到9的10个一维数组);
         *  按照桶的顺序(一维数组的下标依次取出数据，放入原来的数组中);
         * 第二轮排序：
         *  根据每个元素的十位数数值，将元素保存到十位数值对应的桶中(0到9的10个一维数组);
         *  按照桶的顺序(一维数组的下标依次取出数据，放入原来的数组中);
         * 第三轮排序：
         *  根据每个元素的百位数数值，将元素保存到百位数值对应的桶中(0到9的10个一维数组);
         *  按照桶的顺序(一维数组的下标依次取出数据，放入原来的数组中);
         */
        // 定义一个二维数组, 表示10个桶, 每个桶就是一个一维数组, 每个桶最多含有arr.length个元素
        int[][] buckets = new int[10][arr.length];
        // 为了记录每个桶中实际存放了多少个数据, 定义一个一维数组, 记录各个桶每次放入的数据个数
        int[] bucketElementCount = new int[10];

        for (int i = 0; i < String.valueOf(maxElement).length(); i++) {
            // 根据每个元素的个位数数值，将元素保存到个位数值对应的桶中(0到9的10个一维数组);
            for (int j = 0; j < arr.length; j++) {
                // 取出每个元素的个位数
                int digitElement = arr[j] / (int)Math.pow(10, i) % 10;
                // 将元素放入到个位数对应的桶中
                // bucketElementCount[digitElement]表示个位数为digitElement的桶对应的数据个数计数, 默认为0
                buckets[digitElement][bucketElementCount[digitElement]] = arr[j];
                // 对应桶中存放的数据计数加1
                bucketElementCount[digitElement]++;
            }
            // 按照桶的顺序(一维数组的下标依次取出数据，放入原来的数组中);
            // 遍历每个桶, 将桶中的数据，放入原数组;
            int index = 0;
            for (int k = 0; k < buckets.length; k++) {
                // 对应桶粗放的数据计数不为0，才进行放入到原数组的操作
                if (bucketElementCount[k] != 0) {
                    for (int l = 0; l < bucketElementCount[k]; l++) {
                        arr[index] = buckets[k][l];
                        index++;
                    }
                    // 数据存入到原数组后，计数桶中的计数需要清零
                    bucketElementCount[k] = 0;
                }
            }
            //log.info("第{}轮排序后的数组: {}", i+1, arr);
        }
        return arr;
    }

    private static int getMaxElement(int[] arr) {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
}
