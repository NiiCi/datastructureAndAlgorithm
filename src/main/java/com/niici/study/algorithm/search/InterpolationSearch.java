package com.niici.study.algorithm.search;

import com.google.common.collect.Lists;
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
 *  插值查找注意事项：
 *    对于数据量较大，关键字分布比较均匀的查找表来说，采用插值查找，速度较快。
 *    关键字分布不均匀的情况下，该方法不一定比二分查找要好。
 * @author niici
 */
@Slf4j
public class InterpolationSearch extends BaseSearch {
    public static void main(String[] args) {
        Integer[] arr2 = {-1, 1, 1, 9, 11, 34, 34, 89};
        List<Integer> indexs = InterpolationSearch.search(arr2, 0, arr2.length - 1, 1);
        log.info("插值查找匹配到的数的下标为：{}", indexs);
    }

    /**
     * 插值查找，当多个数相同时，返回多个下标
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
        if (left > right || value < arr[0] || value > arr[arr.length - 1]) {
            return new ArrayList<>();
        }
        int mid = left + (right - left) * (value - arr[left]) / (arr[right] - arr[left]);
        // 待查找数大于arr[mid]，则向右递归查找
        if (value > arr[mid]) {
            return search(arr, mid + 1, right, value);
        } else if (value < arr[mid]){
            return search(arr, left, mid - 1, value);
        } else {
            // 已找到相等的数，则向左向右递归，查找是否仍存在相等的数
            List<Integer> indexList = Lists.newArrayList();
            // 向左遍历(向左移一位开始遍历排除当前已找到的数)
            int temp = mid - 1;
            // 有序数组，相同的元素一定相邻
            while(temp >= 0 && value == arr[temp]) {
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
