# 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。

# 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。

#  

# 示例 1：

# 输入：n = 12
# 输出：3 
# 解释：12 = 4 + 4 + 4
# 示例 2：

# 输入：n = 13
# 输出：2
# 解释：13 = 4 + 9
#  
# 提示：

# 1 <= n <= 104

import math
from 数据结构.队列与广度搜索优先.队列 import ListQueue as Queue


class Solution:
    def numSquares(self, n: int) -> int:
        queue = Queue()
        queue.put((n, 0))

        while not queue.empty():
            num, step = queue.get()
            num_square_list = [i * i for i in range(int(math.sqrt(num)), 0, -1)]
            step += 1
            for square in num_square_list:
                left = num - square
                if left in num_square_list:
                    return step + 1
                elif left == 0:
                    return step
                else:
                    queue.put((left, step))


if __name__ == "__main__":
    a = Queue()
    a.put(1)
    print(a)
    a.put(2)
    print(a)
    b = a.get()
    print(b)
    print(a)
# c = a.get()
# print(c)
# print(a)
# d = a.get()
# print(d)
# print(a)

# s = Solution()
# print(s.numSquares(7))
