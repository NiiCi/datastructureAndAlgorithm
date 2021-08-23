package com.niici.study.algorithm.search;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        List<Integer> list = IntStream.rangeClosed(0, 800000).boxed().collect(Collectors.toList());
        Integer[] arr = new Integer[list.size()];
        list.toArray(arr);
        long time = System.currentTimeMillis();
        int index = BinarySearch.search(arr, 0, arr.length - 1, 40000);
        log.info("运行时间: {}ms", System.currentTimeMillis() - time);
        if (index != -1) {
            log.info("二分查找匹配到的数的下标为：{}", index);
        } else {
            log.error("二分查找未找到匹配的数");
        }

        Integer[] arr2 = {-1, 1, 1, 9, 11, 34, 34, 89};
        List<Integer> indexs = BinarySearch.boostSearch(arr2, 0, arr2.length - 1, 34);
        log.info("二分查找匹配到的数的下标为：{}", indexs);
    }

    public static int search(Integer[] arr, int left, int right, int value) {
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

    /**
     * 增强二分查找，当多个数相同时，返回多个下标
     * 思路分析：
     *  1. 在找到mid时，不要马上返回
     *  2. 向mid索引值的左边扫描，将所有满足条件的元素的下标，加入到一个集合中
     *  3. 向mid索引值的右边扫描，将所有满足条件的元素的下标，加入到一个集合中
     * @param arr
     * @param left
     * @param right
     * @param value
     * @return
     */
    public static List<Integer> boostSearch(Integer[] arr, int left, int right, int value) {
        // 向左递归查找时, mid-1后 rightIndex可能小于0，证明数组已经遍历完成
        // 向右递归查找时, mid+1后 leftIndex可能大于arr.length - 1，证明数组已经遍历完成
        if (left > right) {
            return new ArrayList<>();
        }
        int mid = (left + right) / 2;
        // 待查找数大于arr[mid]，则向右递归查找
        if (value > arr[mid]) {
            return boostSearch(arr, mid + 1, right, value);
        } else if (value < arr[mid]){
            return boostSearch(arr, left, mid - 1, value);
        } else {
            List<Integer> indexList = new ArrayList<>();
            // 向左遍历
            int temp = mid - 1;
            // 有序数组，相同的元素一定相邻
            while(temp >=0 && value == arr[temp]) {
                indexList.add(temp);
                temp--;
            }
            indexList.add(mid);

            // 向右遍历
            temp = mid + 1;
            // 有序数组，相同的元素一定相邻
            while(temp <= right && value == arr[temp]) {
                indexList.add(temp);
                temp++;
            }
            return indexList;
        }
    }
}
