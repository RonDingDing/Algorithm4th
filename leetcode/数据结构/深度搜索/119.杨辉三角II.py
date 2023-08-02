# 给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。

# 在「杨辉三角」中，每个数是它左上方和右上方的数的和。


# 示例 1:

# 输入: rowIndex = 3
# 输出: [1,3,3,1]
# 示例 2:

# 输入: rowIndex = 0
# 输出: [1]
# 示例 3:

# 输入: rowIndex = 1
# 输出: [1,1]


# 提示:

# 0 <= rowIndex <= 33


# 进阶：

# 你可以优化你的算法到 O(rowIndex) 空间复杂度吗？
from typing import List


class Solution:
    def __init__(self) -> None:
        self.result = [[1]]

    def getRow(self, numRows: int) -> List[int]:
        if numRows < len(self.result):
            return self.result[numRows]
        for i in range(len(self.result), numRows+1):
            middle = i - 1
            r = [1] + middle*[0] + [1]
            for j in range(1, i):
                r[j] = self.result[i-1][j - 1] + self.result[i-1][j]
            self.result.append(r)
        return self.result[numRows]


print(Solution().getRow(1))
