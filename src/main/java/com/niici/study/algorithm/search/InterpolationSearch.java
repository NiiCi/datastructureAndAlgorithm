package com.niici.study.algorithm.search;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 插值查找
 *  插值查找类似于二分查找，不同的是插值查找每次从自适应mid处开始查找。
 *  mid = left + (right - left) * (value - arr[left]) / (arr[right] - arr[left])
 *  大大减少了查找的次数(相比于二分查找)
 *
 *  二分查找和插值查找区别：
 *   二分查找mid: mid = left + 1/2 * (right - left)
 *   插值查找mid：mid = left + ((value - arr[left]) / (arr[right] - arr[left])) * (right - left)
 *   left + x * (right - left) x为系数，二分查找系数为1/2，插值查找为(value - arr[left]) / (arr[right] - arr[left])
 *
 * @author niici
 */
@Slf4j
public class InterpolationSearch extends BaseSearch {
    public static void main(String[] args) {
        Integer[] arr2 = {-1, 1, 1, 9, 11, 34, 34, 89};
        List<Integer> indexs = InterpolationSearch.search(arr2, 0, arr2.length - 1, -1);
        log.info("插值查找匹配到的数的下标为：{}", indexs);
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
    public static List<Integer> search(Integer[] arr, int left, int right, int value) {
        // 向左递归查找时, mid-1后 rightIndex可能小于0，证明数组已经遍历完成
        // 向右递归查找时, mid+1后 leftIndex可能大于arr.length - 1，证明数组已经遍历完成
        if (left > right) {
            return new ArrayList<>();
        }
        int mid = left + (right - left) * (value - arr[left]) / (arr[right] - arr[left]);
        // 待查找数大于arr[mid]，则向右递归查找
        if (value > arr[mid]) {
            return search(arr, mid + 1, right, value);
        } else if (value < arr[mid]){
            return search(arr, left, mid - 1, value);
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
