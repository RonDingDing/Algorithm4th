# 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。

# 在「杨辉三角」中，每个数是它左上方和右上方的数的和。


# 示例 1:

# 输入: numRows = 5
# 输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
# 示例 2:

# 输入: numRows = 1
# 输出: [[1]]


# 提示:

# 1 <= numRows <= 30
from typing import List


class Solution:
    def __init__(self) -> None:
        self.result = [[1]]

    def generate(self, numRows: int) -> List[List[int]]:
        if numRows <= len(self.result):
            return self.result[:numRows]
        for i in range(len(self.result), numRows):
            middle = i - 1
            r = [1] + middle*[0] + [1]
            for j in range(1, i):
                r[j] = self.result[i-1][j - 1] + self.result[i-1][j]
            self.result.append(r)
        return self.result


s = Solution()
print(s.generate(10))
print(s.generate(6))
print(s.generate(1))
print(s.generate(2))
print(s.generate(3))
