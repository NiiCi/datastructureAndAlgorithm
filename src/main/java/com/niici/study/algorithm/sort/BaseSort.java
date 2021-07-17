package com.niici.study.algorithm.sort;

/**
 * 排序
 * 将一组数据，依制定的顺序进行排列的过程。
 * 排序的分类：
 *  1. 内部排序法：将需要处理的所有数据都加载到内存中进行排序。
 *  2. 外部排序法：数据量过大，无法全部加载到内存中，需要借助外部存储进行排序。
 * 内部排序法：
 *  1. 插入排序：直接插入排序、希尔排序
 *  2. 选择排序：简单选择排序、堆排序
 *  3. 交换排序：冒泡排序、快速排序
 *  4. 归并排序
 *  5. 基数排序
 * 外部排序法：使用内存和外存结合
 *
 * 时间频度：一个算法中语句执行次数成为时间频度，记为T(n)。
 *  1. 忽略常数项
 *  2. 忽略低次项，如 2n^2+3n+10 和 2n^2 的时间频度接近, 低次项可以忽略。
 *  3. 忽略系数，当幂的次数不超过2时，系数可以忽略。2n² 和 3n²+10 前的系数可以忽略。
 *
 *  时间复杂度：
 *  算法中的基本操作语句的重复执行次数用T(n)表示，
 *  若有某个辅助函数f(n)，n接近于无穷大时，T(n)/f(n)的值接近一个不等于零的常数，则f(n)是T(n)的同数量级函数，
 *  记作T(n)=O(f(n))，称O(f(n))为算法的渐进时间复杂度，简称为时间复杂度。
 *  如T(n)=n²+7n+6的f(n)为n2, 则时间复杂度为O(n²)。
 *
 *  常见的时间复杂度量级：
 *  常数阶O(1)                 加、减、乘、除、赋值
 *  对数阶O(logN)              while循环
 *  线性阶O(n)                 for循环
 *  线性对数阶O(nlogN)          for循环中嵌套while循环
 *  平方阶O(n²)                两层for循环
 *  立方阶O(n³)                三层for循环
 *  K次方阶O(n^k)              n层for循环
 *  指数阶(2^n)
 *  时间复杂度排序：由上述自顶向下一次增加
 */
public interface BaseSort {
}
