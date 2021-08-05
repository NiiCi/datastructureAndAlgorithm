package com.niici.study.algorithm.sort;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 归并排序
 *  是利用归并的西江实现的排序算法。
 * 该算法采用经典的分治策略
 *  (分治法将问题分成一些小的问题然后递归求解，而治的阶段则将分的阶段得到的各答案'修补'在一起，即分而治之。)
 * @author niici
 */
@Slf4j
public class MergeSort extends BaseSort{
    public static void main(String[] args) {
        //int[] arr = generateRandom();
        int[] arr = {5, 6, 7, 20, 1, 10, 13, 15, 21};
        //MergeSort.sort(arr);
        int[] tempArr = new int[arr.length];
        long time = System.currentTimeMillis();
        MergeSort.sort(arr, 0, arr.length - 1, tempArr);
        //MergeSort.merge(arr, 0, 4, arr.length - 1, tempArr);
        log.info("运行时间: {}ms", System.currentTimeMillis() - time);
        log.info("归并排序后的数组: {}", Arrays.toString(arr));
    }

    public static void sort(int[] arr, int left, int right, int[] tempArr) {
        // 向左递归时, mid是向左递归的right，最终会等于right
        // 向右递归时, mid是向右递归的left，最终会等于right
        if (left < right) {
            int mid = (left + right) / 2;
            // 先向左递归分解
            sort(arr, left, mid, tempArr);
            // 再向右递归分解
            sort(arr, mid + 1, right, tempArr);
            // 每递归分解完成一次，就合并一次
            merge(arr, left, mid, right, tempArr);
        }
    }

    /**
     * 归并排序 -- 合并方法
     * @param arr 待排序的数组
     * @param left 左边有序序列的起始索引
     * @param mid  中间索引
     * @param right 右边有序序列的结束索引
     * @param tempArr 临时数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] tempArr) {
        // 初始化leftIndex，左边有序序列的初始索引
        int leftIndex = left;
        // 初始化rightIndex，右边有序序列的初始索引
        int rightIndex = mid + 1;
        int tempIndex = 0;
        /**
         * 1. 将左右两边的数据，按顺序填充到temp数组中，直到左右两边的有序序列，其中一边的数据已全部填充到temp数组中。
         * 2. 将有剩余数据的一边，直接填充到temp数组中。
         * 3. 将temp数组赋值给原数组。
         */
        // 1. 将左右两边的数据，按顺序填充到temp数组中，直到左右两边的有序序列，其中一边的数据已全部填充到temp数组中。
        while (leftIndex <= mid && rightIndex <= right) {
            // leftIndex下标对应的值小于等于rightIndex下标对应的值，则将leftIndex对应的值插入数组
            // leftIndex后移, tempIndex后移
            if (arr[leftIndex] <= arr[rightIndex]) {
                tempArr[tempIndex] = arr[leftIndex];
                leftIndex++;
            } else {
                tempArr[tempIndex] = arr[rightIndex];
                rightIndex++;
            }
            tempIndex++;
        }
        
        // 2. 将有剩余数据的一边，直接填充到temp数组中。
        while (leftIndex <= mid) {
            // 将左边序列剩余的元素填充到tempArr
            tempArr[tempIndex] = arr[leftIndex];
            leftIndex++;
            tempIndex++;
        }

        while (rightIndex <= right) {
            // 将右边序列剩余的元素填充到tempArr
            tempArr[tempIndex] = arr[rightIndex];
            rightIndex++;
            tempIndex++;
        }

        // 3. 将temp数组拷贝给原数组。注意：并不是每次都拷贝tempArr的所有元素到arr中，每次合并可能只有部分元素组成一个tempArr
        tempIndex = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = tempArr[tempIndex];
            tempLeft++;
            tempIndex++;
        }
    }
}
