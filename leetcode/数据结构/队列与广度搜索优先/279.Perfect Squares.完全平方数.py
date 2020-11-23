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
