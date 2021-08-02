package com.niici.study.algorithm.sort;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 快速排序
 *  是冒泡排序的一种改进。
 * 基本思想：
 *  通过一趟排序，将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，
 *  再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。
 * @author nys2881
 */
@Slf4j
public class QuickSort extends BaseSort{
    public static void main(String[] args) {
        int[] arr = generateRandom();
        //int[] arr = {2, 10, 8, 22, 34, 5, 12, 28, 21, 11};
        //int[] arr = {-9, 78, 0, 23, -567, 70};
        long time = System.currentTimeMillis();
        QuickSort.sort(arr, 0, arr.length - 1);
        log.info("运行时间: {}ms", System.currentTimeMillis() - time);
        log.info("快速排序后的数组: {}", Arrays.toString(arr));
    }

    /**
     *
     * @param arr
     * @param left 向左递归时的起始下标(基数左边开始递归的下标)
     * @param right 向右递归时的起始下标(基数右边开始递归的下标)
     * @return
     */
    public static int[] sort(int[] arr, int left, int right) {
        int leftIndex = left;
        int rightIndex = right;
        // 比较基数
        int baseValue = arr[(leftIndex + rightIndex) / 2];
        int temp;
        // 基数为中轴数，则只有在leftIndex < rightIndex场景下，才进行排序
        while (leftIndex < rightIndex) {
            // 小于基数的数，放置在左边(从左向右开始遍历，index++)
            // 左边找到大于等于基数的数时, 退出while循环
            while (arr[leftIndex] < baseValue) {
                leftIndex++;
            }
            // 大于基数的数，放置在右边(从右向左开始遍历，index--)
            // 右边找到小于等于基数的数时, 退出while循环
            while (arr[rightIndex] > baseValue) {
                rightIndex--;
            }

            // 左右index相等时，说明当前 基数左边的数都小于基数，基数右边的数都大于基数，提前结束循环
            if (leftIndex == rightIndex) {
                break;
            }
            // 左右两边的数，交换位置
            temp = arr[leftIndex];
            arr[leftIndex] = arr[rightIndex];
            arr[rightIndex] = temp;

            // arr[leftIndex] 为原 arr[rightIndex]，当等于基数时，index--
            if (arr[leftIndex] == baseValue) {
                rightIndex--;
            }
            // arr[rightIndex] 为原 arr[leftIndex]，当等于基数时，index++
            if (arr[rightIndex] == baseValue) {
                leftIndex++;
            }
        }

        if (leftIndex == rightIndex) {
            leftIndex++;
            rightIndex--;
        }

        // 向左递归(左边排序)
        if (left < rightIndex) {
            sort(arr, left, rightIndex);
        }

        // 向右递归(右边排序)
        if (right > leftIndex) {
            sort(arr, leftIndex, right);
        }
        return arr;
    }
}
