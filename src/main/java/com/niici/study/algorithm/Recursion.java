package com.niici.study.algorithm;

import lombok.extern.slf4j.Slf4j;

/**
 * 递归
 *  i.递归就是方法自己调用自己, 每次调用时传入不同的变量(必须), 递归有助于解决复杂问题, 同时也可以让代码变得简洁。
 * 递归调用规则
 *  i.当程序执行到一个方法时, 就会在栈中开辟一个独立的空间(压栈), 如果一直调用则可能出现栈溢出的异常(必须指定边界条件)。
 *  ii.每个空间的数据(栈帧的局部变量表), 是独立的。
 *  iii.递归执行时, 需等待最内层的调用返回后, 才会进行后续的操作(栈先进后出)。
 *  iiii.如果方法中使用的是引用类型变量(数组、列表), 就会共享该引用类型的数据(引用传递, 使用的都是同一个对象)。
 * 递归用于解决的问题
 *  i.各种数学问题如: 八皇后问题、汉诺塔问题、阶乘问题、迷宫问题、球和篮子问题、谷歌编程大赛题。
 *  ii.各种算法中也使用递归, 如：快速排序、归并排序、二分查找、分治算法等。
 *  iii.使用栈解决的问题, 使用递归可以让代码更简洁。
 * @author niici
 */
@Slf4j
public class Recursion {

    public static void main(String[] args) {
        Recursion.test(5);
        log.info(Recursion.factorial(4) + "");
    }

    /**
     * 打印
     * @param n
     */
    public static void test(int n) {
        if (n > 2) {
            test(n - 1);
        }
        log.info("n= {}", n);
    }

    /**
     * 阶乘
     * @param n
     * @return
     */
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return factorial(n - 1) * n;
    }
}
