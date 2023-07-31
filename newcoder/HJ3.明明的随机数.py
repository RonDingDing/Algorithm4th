
# 数组
# warning 校招时部分企业笔试将禁止编程题跳出页面，为提前适应，练习时请使用在线自测，而非本地IDE。
# 描述
# 明明生成了NN个1到500之间的随机整数。请你删去其中重复的数字，即相同的数字只保留一个，把其余相同的数去掉，然后再把这些数从小到大排序，按照排好的顺序输出。

# 数据范围： 1 \le n \le 1000 \1≤n≤1000  ，输入的数字大小满足 1 \le val \le 500 \1≤val≤500
# 输入描述：
# 第一行先输入随机整数的个数 N 。 接下来的 N 行每行输入一个整数，代表明明生成的随机数。 具体格式可以参考下面的"示例"。
# 输出描述：
# 输出多行，表示输入数据处理后的结果

# 示例1
# 输入：
# 3
# 2
# 2
# 1
# 复制
# 输出：
# 1
# 2
# 复制
# 说明：
# 输入解释：
# 第一个数字是3，也即这个小样例的N=3，说明用计算机生成了3个1到500之间的随机整数，接下来每行一个随机数字，共3行，也即这3个随机数字为：
# 2
# 2
# 1
# 所以样例的输出为：
# 1
# 2
from typing import Generator


class Solution:
    def random(self, num: int) -> Generator[int, None, None]
        members = set()
        for i in range(num):
            members.add(int(input()))
        for x in sorted(members):
            yield x


while True:
    try:
        num = int(input())
        for x in Solution().random(num):
            print(x)

    except Exception:
        break
