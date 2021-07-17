package com.niici.study.algorithm.recursion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 八皇后问题
 * 在8*8的国际象棋上摆放八个皇后, 使其不能互相攻击。
 * 即: 任意两个皇后都不能处于同一行、同一列或者同一斜线上, 问有多少种摆法。
 *
 * @author niici
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class EightQueens {
    /**
     * 皇后个数
     */
    private int max;
    /**
     * 保存皇后放置位置的结果, 如 arr = {0, 4, 7, 5, 2, 6, 1, 3}
     * 数组下标对应皇后的顺序, 值对应皇后的位置, key/value形式
     */
    private int[] arr;

    private int count = 0;

    public EightQueens(int max) {
        this.max = max;
        this.arr = new int[max];
    }

    public static void main(String[] args) {
        EightQueens eightQueens = new EightQueens(8);
        eightQueens.setQueen(0);
/*        eightQueens.setQueen(1);
        eightQueens.setQueen(2);
        eightQueens.setQueen(3);
        eightQueens.setQueen(4);
        eightQueens.setQueen(5);
        eightQueens.setQueen(6);
        eightQueens.setQueen(7);*/

        log.info("共有{}种摆放方式!", eightQueens.count);
    }

    /**
     * 放置皇后
     * @param num 第n个皇后(皇后的下标从0开始)
     */
    private void setQueen(int num) {
        // num最大到8, 递归调用最大为7+1, 此时8个皇后已经放完, num为8时直接打印, 返回
        if (num == (max)) {
            // 打印皇后放置位置
            print();
            count++;
            return;
        }
        // 依次放入皇后, 并判断是否冲突
        for (int i = 0; i < max; i++) {
            // 皇后从当前i行的第一列开始放置
            arr[num] = i;
            /**
             * 判断当前皇后是否和之前放置的皇后位置冲突
             * 1. 冲突，则进入for循环下一次，将皇后放置到下一列, 继续判断是否冲突, 继续setQueen, 继续for循环, 形成回溯。
             * 2. 不冲突, 则放置下一个皇后。
             */
            if (!isConflict(num)) {
                // return时，表示已找到一个正确解，当前皇后放置到下一列，查找其他正确解。
                setQueen(num + 1);
            }
        }
    }

    /**
     * 校验当前皇后与之前的皇后位置是否冲突
     * @param num 第几个皇后(皇后的下标从0开始)
     * @return
     */
    private boolean isConflict(int num) {
        // 与之前的几个皇后比较位置, 判断是否冲突
        for (int i = 0; i < num; i++) {
            // 在同一列或者在同一斜线上
            // 判断是否在同一斜线上：下标差值等于值的差值, 则认为是在同一斜线上
            if (arr[i] == arr[num] || Math.abs((num) - i) == Math.abs(arr[num] - arr[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * 输出皇后摆放的位置
     */
    private void print() {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /**
     * 八皇后问题算法思路分析(回溯算法)
     * 1. 第一个皇后先放在第一行第一列。
     * 2. 第二个皇后放在第二行第一列, 判断是否OK, 如果不OK, 继续放在第二列、第三列, 依次把所有列都放完, 找到一个合适的位置。
     * 3. 继续第三个皇后, 还是第一列、第二列...直到第八个皇后也能放在一个不冲突的位置, 即找到一个正确解。
     * 4. 当得到一个正确解时, 在栈回退到上一个栈时, 就会开始回溯, 即将第一个皇后, 放到第一列的所有正确解, 全部得到。
     * 5. 将第一个皇后放到第二列, 依次类推, 得到所有的正确解。
     */
    public void solution() {



    }

    private boolean isFind() {
        return false;
    }
}
