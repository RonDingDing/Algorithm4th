# 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

# 说明：每次只能向下或者向右移动一步。

#

# 示例 1：


# 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
# 输出：7
# 解释：因为路径 1→3→1→1→1 的总和最小。
# 示例 2：

# 输入：grid = [[1,2,3],[4,5,6]]
# 输出：12
#

# 提示：

# m == grid.length
# n == grid[i].length
# 1 <= m, n <= 200
# 0 <= grid[i][j] <= 200
from typing import List


class Solution:
    def minPathSum(self, grid: List[List[int]]) -> int:
        if not grid or not grid[0]:
            return 0
        m = len(grid[0])
        n = len(grid)
        dp = [float("inf")] * m
        for j in range(n):
            for i in range(m):
                cur = grid[j][i]
                if i == j == 0:
                    dp[i] = cur
                elif i == 0:
                    dp[i] = cur + dp[i]
                elif j == 0:
                    dp[i] = cur + dp[i - 1]
                else:
                    dp[i] = cur + min(dp[i - 1], dp[i])
        return int(dp[-1])


grid = [[3, 4, 9, 5], [3, 1, 6, 4], [7, 8, 2, 10]]
print(Solution().minPathSum(grid))
