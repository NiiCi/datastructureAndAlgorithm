package com.niici.study.datastructure.stack;

import com.niici.study.bean.HeroNode;
import com.niici.study.datastructure.linkedList.SinplyLinkedList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 栈
 * 先进后出的数据结构
 * 应用场景：
 * 1. 子程序的调用：在跳往子程序前，会先将下个指令的地址存到堆栈中，直到子程序执行完后将地址取出，以回到原来的程序中；
 * 2. 处理递归调用：和子程序的调用类似，只是除了存储下一个指令的地址外，也将参数、局部变量等数据存入堆栈中；
 * 3. 表达式的转换(中缀表达式转后缀表达式)与求值(实际解决)；
 * 4. 二叉树的遍历；
 * 5. 图形的深度优先(depth-first)搜索算法
 * @author niici
 * Created by niici on 2021/6/23.
 */
@Slf4j
public class LearnStack {
    public static void main(String[] args) {
        Stack<String> strs = new Stack<>();
        // 压栈, 将数据压入栈底
        strs.add("str1");
        strs.add("str2");
        strs.add("str3");
        strs.add("str4");

        // 出栈, 将栈顶的数据取出
        log.info(strs.pop());
        log.info(strs.pop());
        log.info(strs.pop());
        ArrayStack arrayStack = new ArrayStack(5);
        arrayStack.push(1);
        arrayStack.push(2);
        log.info("firstElement: {}", arrayStack.firstElement());
        arrayStack.list();

        LinkedListStack linkedListStack = new LinkedListStack(5);
        linkedListStack.push(1);
        linkedListStack.push(2);
        log.info("firstElement: {}", linkedListStack.firstElement());
        linkedListStack.list();

        log.info(LearnStack.calculateExpression("7*6/6+4/2").toString());
        log.info(LearnStack.againstPolandCalculator("30 4 + 5 * 6 - ").toString());
        // 中缀表达式
        String infixExperssion = "1+20+3*4-5";
        LearnStack.transFormToSuffix(infixExperssion);
        log.info(LearnStack.againstPolandCalculator(LearnStack.transFormToSuffix(infixExperssion)).toString());
    }


    /**
     * 栈实现综合计算器
     * 输入一个表达式，计算结果
     * 1. 定义两个栈：一个数字栈、一个运算符号栈
     * 2. 定义一个index表示表达式的索引
     * 3. 将数组存入数栈，运算符存入符号栈，当存入的运算符优先级小于或等于当前栈中的符号时，
     *    从数栈中出栈2个数字，符号栈中出栈一个运算符进行运算，完成后，将运算结果入栈到数栈中，新的运算符入栈符号栈
     *    否则直接入栈
     * 4. 最后进行运算，数栈出栈2个数，符号栈出栈一个运算符进行计算
     */
    public static Double calculateExpression(String str) {
        if (StringUtils.isBlank(str)) {
            log.error("expression is empty.");
            return null;
        }
        int index = 0;
        String tempStr;
        Stack<Double> numStack = new Stack<>();
        Stack<String> strStack = new Stack<>();
        for (int i = 1; i <= str.length(); i++) {
            tempStr = str.substring(index, i);
            String nextStr = "";

            // 判断当前是否为字符串
            if (isNum(tempStr)) {
                // 获取下一个字符串
                if (i < str.length()) {
                    nextStr = str.substring(index, i + 1);
                    // 当下一个字符串仍未数字时, 跳过, 此时index不累加
                    if (isNum(nextStr)) {
                        continue;
                    }
                }
                // 当下一个字符串为字符时, 将当前数字入栈
                // 7 -> index 0, i 1, 70 -> index 0 i 2 (此时实际上index应为1), 70+ -> index 2 i 3
                // index = i - 1 + 1, index本身需要自增
                numStack.push(Double.valueOf(tempStr));
                index = i;
            } else {
                // 符号栈为空，则直接入栈
                if (strStack.isEmpty()) {
                    strStack.push(tempStr);
                } else {
                    // 获取前一个运算符
                    String preStr = strStack.pop();
                    // 判断当前运算符的优先级, 更优先则直接存入, 否则需从数栈中取出2个数, 与前一个运算符进行运算
                    // 将运算结果入栈，再将当前运算符入栈
                    if (!isCurrentPrior(preStr, tempStr)) {
                        Double result = caculate(preStr, numStack.pop(), numStack.pop());
                        numStack.push(result);
                    } else {
                        // 当前运算符优先，则将前一个运算符放回栈中
                        strStack.push(preStr);
                    }
                    strStack.push(tempStr);
                }
                index++;
            }
        }
        // 当运算符栈为空时，运算结束
        while (strStack.size() > 0) {
            Double result = caculate(strStack.pop(), numStack.pop(), numStack.pop());
            numStack.push(result);
        }
        return numStack.pop();
    }

    public static boolean isNum(String str) {
        for (int i = str.length(); --i >= 0; ) {
            int chr = str.charAt(i);
            if (chr < 48 || chr > 57) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否当前运算符更优先
     * @param preStr 上一个运算符
     * @param currentStr 当前与悬浮
     * @return
     */
    public static boolean isCurrentPrior(String preStr, String currentStr) {
        Map<String, Integer> priorMap = new HashMap<>();
        priorMap.put("*", 2);
        priorMap.put("/", 2);
        priorMap.put("+", 1);
        priorMap.put("-", 1);
        priorMap.put("(", 0);
        priorMap.put(")", 0);
        Integer preStrPrior = priorMap.get(preStr);
        Integer currentStrPrior = priorMap.get(currentStr);
        if (preStrPrior < currentStrPrior) {
            return true;
        }
        return false;
    }

    /**
     * 计算
     * @param str
     * @param num1
     * @param num2
     * @return
     */
    public static Double caculate(String str, Double num1, Double num2) {
        Double result = null;
        switch (str) {
            case "*" :
                result = num2 * num1;
                break;
            case "/" :
                result = num2 / num1;
                break;
            case "+" :
                result = num2 + num1;
                break;
            case "-" :
                result = num2 - num1;
                break;
            default:
                break;
        }
        return result;
    }

    /**
     * 中缀表达式转后缀表达式
     * @param infixStr
     * @return
     */
    public static String transFormToSuffix(String infixStr) {
        // (3+4+5)*5-6 ==> 3 4 + 5 + 5 * 6 -
        // (3+4+5)*5-6
        // 3+4*(5-6) ==> 3 4 5 6 - * +
        String tempStr;
        Integer index = 0;
        // 定义两个栈: 数栈和运算符栈
        Stack<String> strStack = new Stack<>();
        Stack<String> numStack = new Stack<>();
        List<String> strList = new ArrayList<>();
        // 方案一：将表达式转为list进行遍历

        /*for (int i = 1; i <= infixStr.length(); i++) {
            String tempStr1 = infixStr.substring(index, i);
            if (!isNum(tempStr1)) {
                strList.add(tempStr1);
                index++;
            } else {
                // 获取下一个字符串
                if (i < infixStr.length()) {
                    String nextStr = infixStr.substring(index, i + 1);
                    if (isNum(nextStr)) {
                        continue;
                    }
                }
                // 当下标为最后一个元素时, 为数字则直接添加到列表中
                strList.add(tempStr1);
                index = i;
            }
        }*/


        /**
         * 前缀转后缀表达式逻辑
         * 1. 运算符栈为空, 直接入栈 index++
         * 2. 运算符栈栈顶元素为左括号, 将左括号放回, 直接入栈 index++
         * 3. 运算符为左括号, 直接入栈 index++
         * 4. 当前运算符优先级大于运算符栈栈顶元素, 直接入栈index++
         * 5. 当前运算符为右括号, 取出运算符栈栈顶元素, 压入数栈, 直到栈顶元素为左括号, 当前运算符入栈, index++
         * 6. 当前运算符优先级小于运算符栈栈顶元素, 将运算符栈栈顶元素, 压入数栈, 直到优先级大于栈顶元素，当前运算符入栈, index++
         */
        /*strList.stream().forEach(str -> {
            if (isNum(str)) {
                numStack.push(str);
            } else {
                if (strStack.isEmpty()) {
                    strStack.push(str);
                } else {
                    // 获取栈顶运算符
                    String topStr = !strStack.isEmpty() ? strStack.pop() : null;
                    // 当前运算符入栈时, 需要将前一个运算符先入栈
                    // 栈顶运算符或者当前运算符是左括号、优先级大于栈顶元素, 将栈顶元素放回, 当前运算符入栈
                    if ("(".equals(str) || "(".equals(topStr) || isCurrentPrior(topStr, str)) {
                        strStack.push(topStr);
                        strStack.push(str);

                        // 当前运算符为右括号, 取出运算符栈栈顶元素, 压入数栈, 直到栈顶元素为左括号, 当前运算符入栈
                    } else if (")".equals(str)) {
                        while (!"(".equals(topStr)) {
                            numStack.push(topStr);
                            // 运算符栈为空时, 跳出while循环, 不需要将右括号入栈
                            if (strStack.isEmpty()) {
                                break;
                            }
                            topStr = strStack.pop();
                        }

                        // 当前运算符优先级小于运算符栈栈顶元素, 将运算符栈栈顶元素, 压入数栈, 直到优先级大于栈顶元素，当前运算符入栈, index++
                    } else if (!isCurrentPrior(topStr, str)) {
                        while (!isCurrentPrior(topStr, str)) {
                            numStack.push(topStr);
                            // 运算符栈为空时, 直接入栈跳出while循环
                            if (strStack.isEmpty()) {
                                strStack.push(str);
                                break;
                            }
                            topStr = strStack.pop();
                            // 运算符栈栈顶元素为左括号时, 将当前运算符入运算符栈
                            if ("(".equals(topStr)) {
                                strStack.push(str);
                            }
                        }
                    }
                }
            }
        });*/


        // 方案二：直接遍历
        for (int i = 1; i <= infixStr.length(); i++) {
            tempStr = infixStr.substring(index, i);
            if (isNum(tempStr)) {
                // 获取下一个字符串
                if (i < infixStr.length()) {
                    String nextStr = infixStr.substring(index, i + 1);
                    if (isNum(nextStr)) {
                        continue;
                    }
                }
                // 当下标为最后一个元素时, 为数字则直接添加到列表中
                numStack.push(tempStr);
                index = i;
            } else {
                if (strStack.isEmpty()) {
                    strStack.push(tempStr);
                } else {
                    // 获取栈顶运算符
                    String topStr = !strStack.isEmpty() ? strStack.pop() : null;
                    // 当前运算符入栈时, 需要将前一个运算符先入栈
                    // 栈顶运算符或者当前运算符是左括号、优先级大于栈顶元素, 将栈顶元素放回, 当前运算符入栈
                    if ("(".equals(tempStr) || "(".equals(topStr) || isCurrentPrior(topStr, tempStr)) {
                        strStack.push(topStr);
                        strStack.push(tempStr);

                    // 当前运算符为右括号, 取出运算符栈栈顶元素, 压入数栈, 直到栈顶元素为左括号, 当前运算符入栈
                    } else if (")".equals(tempStr)) {
                        while (!"(".equals(topStr)) {
                            numStack.push(topStr);
                            // 运算符栈为空时, 跳出while循环, 不需要将右括号入栈
                            if (strStack.isEmpty()) {
                                break;
                            }
                            topStr = strStack.pop();
                        }

                    // 当前运算符优先级小于运算符栈栈顶元素, 将运算符栈栈顶元素, 压入数栈, 直到优先级大于栈顶元素，当前运算符入栈, index++
                    } else if (!isCurrentPrior(topStr, tempStr)) {
                        while (!isCurrentPrior(topStr, tempStr)) {
                            numStack.push(topStr);
                            // 运算符栈为空时, 直接入栈跳出while循环
                            if (strStack.isEmpty()) {
                                strStack.push(tempStr);
                                break;
                            }
                            topStr = strStack.pop();
                            // 运算符栈栈顶元素为左括号时, 将当前运算符入运算符栈
                            if ("(".equals(topStr)) {
                                strStack.push(tempStr);
                            }
                        }
                    }
                }
                index++;
            }
        }

        // 将运算符栈中的元素出栈, 压入数栈中
        while(!strStack.isEmpty()) {
            numStack.push(strStack.pop());
        }

        String suffixExpression;
        StringBuilder suffixExpressionSb = new StringBuilder();
        numStack.stream().forEach(num -> {
            suffixExpressionSb.append(num).append(" ");
        });
        suffixExpression = suffixExpressionSb.toString();
        suffixExpression = suffixExpression.substring(0, suffixExpression.lastIndexOf(" "));
        log.info("后缀表达式suffixExpression: {}", suffixExpression);
        return suffixExpression;
    }

    /**
     * 后缀表达式计算器(逆波兰计算器)
     * 输入一个逆波兰表达式, 使用栈, 计算其结果
     * 支持小括号和多位数整数
     * 3 4 + 5 * 6 -
     */
    public static Double againstPolandCalculator(String againstPolandStr) {
        if (StringUtils.isBlank(againstPolandStr)) {
            log.error("against poland expression is empty.");
            return null;
        }

        // 将表达式字符串转为list
        ArrayList<String> strList = new ArrayList<>();
        String[] strArr = againstPolandStr.split(" ");
        Collections.addAll(strList, strArr);

        Stack<Double> numStack = new Stack<>();
        strList.stream().forEach(str -> {
            if (isNum(str)) {
                numStack.push(Double.valueOf(str));
            } else {
                Double result = caculate(str, numStack.pop(), numStack.pop());
                numStack.push(result);
            }
        });
        return numStack.pop();
    }
}

/**
 * 数组实现栈
 * 定义一个top来表示栈顶，初始化为-1
 * 入栈：当有数据加入到栈时，top加1，将数据加入stack[top]
 * 出栈：将栈顶数据取出，top减1，并将数据取出
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
class ArrayStack {
    /**
     * 栈的大小
     */
    private int maxSize;
    /**
     * 栈顶指针
     */
    private int top = -1;
    /**
     * 定义数组，将数据存入数组中
     */
    private int[] arr;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    /**
     * 添加数据
     * @param value
     */
    public void push(Integer value) {
        if (top == maxSize - 1) {
            log.info("stack is full.");
            return;
        }
        top++;
        arr[top] = value;
    }

    /**
     * 取出数据
     * @return
     */
    public Integer pop() {
        if (top == -1) {
            log.info("stack is empty.");
            return null;
        }
        Integer value = arr[top];
        top--;
        return value;
    }

    /**
     * 获取栈底元素
     * @return
     */
    public int firstElement() {
        return arr[0];
    }

    /**
     * 遍历时从栈顶开始显示
     */
    public void list() {
        if (top == -1) {
            log.info("stack is empty.");
            return;
        }
        for (int i = top; i >= 0; i--) {
            log.info("value: {}", arr[i]);
        }
    }
}

/**
 * 单向链表实现栈
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
class LinkedListStack {
    // 栈的大小
    private int maxSize;
    // 栈顶指针
    private int top = -1;
    // 单向链表
    private SinplyLinkedList sinplyLinkedList;

    public LinkedListStack(int maxSize) {
        this.maxSize = maxSize;
        sinplyLinkedList = new SinplyLinkedList();
    }

    /**
     * 添加数据
     * @param value
     */
    public void push(Integer value) {
        if (top == maxSize - 1) {
            log.info("stack is full.");
            return;
        }
        top++;
        HeroNode heroNode = new HeroNode(value);
        sinplyLinkedList.headAdd(heroNode);
    }

    /**
     * 取出数据
     * @return
     */
    public Integer pop() {
        if (top == -1) {
            log.info("stack is empty.");
            return null;
        }
        HeroNode topNode = sinplyLinkedList.getHeadNode().getNext();
        Integer value = topNode.getNumber();
        sinplyLinkedList.getHeadNode().setNext(topNode.getNext());
        top--;
        return value;
    }

    /**
     * 获取栈底元素
     * @return
     */
    public int firstElement() {
        HeroNode tempNode = sinplyLinkedList.getHeadNode().getNext();
        while (true) {
            if (tempNode.getNext() == null) {
                return tempNode.getNumber();
            }
            tempNode = tempNode.getNext();
        }
    }

    /**
     * 遍历时从栈顶开始显示
     */
    public void list() {
        if (top == -1) {
            log.info("stack is empty.");
            return;
        }
        HeroNode tempNode = sinplyLinkedList.getHeadNode().getNext();
        while (true) {
            log.info("value: {}", tempNode.getNumber());
            if (tempNode.getNext() == null) {
                break;
            }
            tempNode = tempNode.getNext();
        }
    }
}

/**
 * 前缀、中缀、后缀表达式(逆波兰表达式)
 *
 * 前缀表达式(波兰表达式)：
 * 1. 前缀表达式又称波兰式, 前缀表达式的运算符位于操作数之前
 * 2. 如: (3+4)*5-6对应的前缀表达式为 - * + 3 4 5 6
 *          3+4*5-6对应的前缀表达式为 - + 3 * 4 5 6
 * 3. 前缀表达式的计算机求值:
 *     i. 从右向左扫描前缀表达式, 遇到数字压入数栈, 遇到运算符, 弹出数栈栈顶的两个数, 用运算符计算后, 将结果入栈
 *    ii. 重复上述过程直到前缀表达式的最左端, 得出的值为表达式的结果
 *
 *
 * 中缀表达式:
 * 1. 就是常见的运算表达式, 如(3+4)*5-6就是(3+4)*5-6, 保持不变
 * 2. 中缀表达式的求值对我们来说最熟悉, 但是对计算机却不好操作(calculateExpression采用的是中缀表达式的处理)
 * 3. 因此在计算结果时, 通常会将中缀表达式转成其他表达式来操作(一般转成后缀表达式)
 *
 *
 * 后缀表达式(逆波兰表达式):
 * 1. 后缀表达式又称逆波兰表达式, 与前缀表达式相似, 只是运算符位于操作数之后
 * 2. 如: (3+4)*5-6对应的后缀表达式为 3 4 + 5 * 6 -
 * 3. 再举例:
 *    a+b        ==>  a b +
 *    a+(b-c)    ==>  a b c - +
 *    a+(b-c)*d  ==>  a b c - d * +
 *    a+d*(b-c)  ==>  a d b c - * +
 *    a=1+3      ==>  a 1 3 + =
 * 4. 后缀表达式的计算机求值:
 *     i. 从左向右扫描后缀表达式, 遇到数字时, 将数字压入数栈, 遇到运算符时, 弹出栈顶的两个数, 用运算符进行计算, 将结果入栈
 *    ii. 重复上述过程直到后缀表达式的最右端, 得出的值为表达式的结果
 */
