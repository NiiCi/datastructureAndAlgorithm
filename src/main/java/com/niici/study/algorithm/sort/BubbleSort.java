package com.niici.study.algorithm.sort;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Random;

/**
 * 冒泡排序算法
 *  通过对 待排序序列 从前向后(从下标较小的元素开始)，依次比较相邻元素的值
 *  若发现逆序则交换，使值较大的元素逐渐从前移向后，就像水底下的气泡逐渐向上冒。
 *
 * 优化：
 *  因为排序的过程中，各元素不断接近自己的位置，如果一趟比较下来没有进行过交换，说明序列有序。
 *  因此要在排序过程中设置一个标志位flag判断元素是否进行过交换，从而减少不必要的比较。
 * @author niici
 */
@Data
@ToString
@Slf4j
public class BubbleSort extends BaseSort {
    public static void main(String[] args) {
        int[] arr = generateRandom();
        log.info(""+ Arrays.toString(arr));

        long time = System.currentTimeMillis();
        BubbleSort.sort(arr);
        log.info("运行时间: {}ms", System.currentTimeMillis() - time);
        log.info("冒泡排序后的数组: {}", Arrays.toString(arr));
    }

    public static int[] sort(int[] arr) {
        int temp;
        // 标识数据是否交换
        boolean flag = false;
        /**
         * 内层for循环，每次循环完成，都会将当前最大的数排到最后
         * 第一次循环，最大的数，第二次循环，第二大的数，依次类推
         * 因此下一次进入到内层for时，应将最后几个有序的数排除
         * 数组长度以5为例，第一次内层for执行4次，第二次内层for执行3次(最大的数已排到数组最后，少一次循环)
         */
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                // 下一个数小于当前数, 则交换位置
                if (arr[j + 1] < arr[j]) {
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                    flag = true;
                }
            }
            // 当前数组中的数据未发生交换, 则说明已排序完成
            if (!flag) {
                break;
            }
            flag = false;
        }
        return arr;
    }
}
