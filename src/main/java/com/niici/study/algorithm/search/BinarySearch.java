package com.niici.study.algorithm.search;

import lombok.extern.slf4j.Slf4j;

/**
 * 二分查找
 *  必须是一个有序数组才可以进行二分查找
 * 基本思路：
 *  1. 首先确定该数组的中间的下标mid。
 *  2. 让待查找的数，和arr[mid]比较，如果大于arr[mid]，则继续往右二分查找，如果小于arr[mid]，则继续往左二分查找。
 *
 * @author niici
 */
@Slf4j
public class BinarySearch extends BaseSearch {
    public static void main(String[] args) {
        int[] arr = {-1, 1, 9, 11, 34, 89};
        long time = System.currentTimeMillis();
        int index = BinarySearch.search(arr, 0, arr.length - 1, 89);
        log.info("运行时间: {}ms", System.currentTimeMillis() - time);
        if (index != -1) {
            log.info("二分查找匹配到的数的下标为：{}", index);
        } else {
            log.error("二分查找未找到匹配的数");
        }
    }

    public static int search(int[] arr, int left, int right, int value) {
        // 向左递归查找时, mid-1后 rightIndex可能小于0，证明数组已经遍历完成
        // 向右递归查找时, mid+1后 leftIndex可能大于arr.length - 1，证明数组已经遍历完成
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        // 待查找数大于arr[mid]，则向右递归查找
        if (value > arr[mid]) {
            return search(arr, mid + 1, right, value);
        } else if (value < arr[mid]){
            return search(arr, left, mid - 1, value);
        } else {
            return mid;
        }
    }
}
