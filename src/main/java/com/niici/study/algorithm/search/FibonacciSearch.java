package com.niici.study.algorithm.search;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * 斐波那契(黄金分割点)查找
 *  黄金分割点是指把一条线段分割为两部分，使其中一部分与全长之比等于另一部分与与这部分之比
 *  取其前三位数字的近似值为0.618，由于按此比例设计的造型十分美丽，因此成为黄金粉笔，也成为中外比。
 *
 *  斐波那契数列{1 1 2 3 5 8 13 21 34 55}，发现斐波那契数列的像个相邻数的比例，无线接近黄金分隔值0.618
 *
 *  斐波那契查找原理：
 *    与前两种查找类似，仅仅改变了中间节点(mid)的位置，不再是中间值或者是插值得到，而是位于黄金分割点附近
 *    即 mid = left + F[k-1] - 1，F代表斐波那契数列，k代表数列的第几个元素
 *
 *    F(k - 1) - 1的理解：
 *    1. 由斐波那契数列F(k) = F(k-1) + F(k-2)，可以得到 F(k)-1 = (F(k-1)-1) + (F(k-2)-1) + 1，
 *    该式说明，只要顺序表的长度为F(k)-1，则可以将该表分为长度为F(k-1)-1 和 F(k-2)-1两段，而中间位置为mid=low+F(k-1)-1
 *    2. 类似的，每一子段也可以用相同的方式分割
 *    3. 但顺序表的长度n不一定刚好等于F(k)-1，所以需要将原来的顺序表长度增加至F(k)-1，这里的k值只要能使得F(k)-1恰好大于或者等于n即可，
 *
 * @author niici
 */
public class FibonacciSearch extends BaseSearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
    }

    public List<Integer> search() {
        List<Integer> objects = Lists.newArrayList();
        return objects;
    }
}
