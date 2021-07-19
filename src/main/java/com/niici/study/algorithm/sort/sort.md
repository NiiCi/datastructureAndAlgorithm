###时间频度
一个算法中语句执行次数成为时间频度，记为T(n)。
   - 忽略常数项 
   - 忽略低次项，如 2n^2+3n+10 和 2n^2 的时间频度接近, 低次项可以忽略。 
   - 忽略系数，当幂的次数不超过2时，系数可以忽略。2n² 和 3n²+10 前的系数可以忽略。
---

###时间复杂度
   - 算法中的基本操作语句的重复执行次数用T(n)表示， 
   - 若有某个辅助函数f(n)，n接近于无穷大时，T(n)/f(n)的值接近一个不等于零的常数，则f(n)是T(n)的同数量级函数，
   - 记作T(n)=O(f(n))，称O(f(n))为算法的渐进时间复杂度，简称为时间复杂度。
   - 如T(n)=n²+7n+6的f(n)为n2, 则时间复杂度为O(n²)。 
     
####常见的时间复杂度量级：
量级|操作
---|:---|
常数阶O(1)|加、减、乘、除、赋值
对数阶O(logN)|while循环
线性阶O(n)|for循环
线性对数阶O(nlogN)|for循环中嵌套while循环
平方阶O(n²)|两层for循环
立方阶O(n³)|三层for循环
K次方阶O(n^k)|n层for循环
指数阶(2^n)|

<font color=#FF0000 size=2>时间复杂度排序：由上述自顶向下一次增加。</font>

####常见排序算法时间复杂度
排序法|平均时间复杂度|最坏时间复杂度|稳定度|空间复杂度|备注
---|:---|:---|:---|:---|:---|
冒泡|O(n²)|O(n²)|稳定|O(1)|n小时较好
交换|O(n²)|O(n²)|不稳定|O(1)|n小时较好
选择|O(n²)|O(n²)|不稳定|O(1)|n小时较好
插入|O(n²)|O(n²)|稳定|O(1)|大部分数据已排序时较好
基数|O(logRB)|O(logRB)|稳定|O(n)|B是真数(0-9), R是基数(个十百)
Shell|O(nlogN)|O(n^s) 1<s<2|不稳定|O(1)|s是所选分组
快速|O(nlogN)|O(n²)|不稳定|O(nlogN)|n大时较好
归并|O(nlogN)|O(nlogN)|稳定|O(1)|n大时较好
堆|O(nlogN)|O(nlogN)|不稳定|O(1)|n大时较好

<font color=#FF0000 size=2>一般考虑的是最坏时间复杂度。</font>

---
