# 994. 腐烂的橘子
# 在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：

# 值 0 代表空单元格；
# 值 1 代表新鲜橘子；
# 值 2 代表腐烂的橘子。
# 每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。

# 返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。


# 示例 1：


# 输入：grid = [[2,1,1],[1,1,0],[0,1,1]]
# 输出：4
# 示例 2：

# 输入：grid = [[2,1,1],[0,1,1],[1,0,1]]
# 输出：-1
# 解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个正向上。
# 示例 3：

# 输入：grid = [[0,2]]
# 输出：0
# 解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。


# 提示：

# m == grid.length
# n == grid[i].length
# 1 <= m, n <= 10
# grid[i][j] 仅为 0、1 或 2
from typing import List


class Solution:
    def orangesRotting(self, grid: List[List[int]]) -> int:
        if not grid:
            return 0
        n = len(grid)
        m = len(grid[0])
        fresh = []
        rotten = []
        for i in range(n):
            for j in range(m):
                orange = grid[i][j]
                if orange == 2:
                    rotten.append((i, j, 0))
                elif orange == 1:
                    fresh.append((i, j))
        while rotten and fresh:
            u, c, minu = rotten.pop()
            for a, b in ((1, 0), (0, 1), (-1, 0), (0, -1)):
                left_right = u + a
                up_down = c + b
                if (
                    left_right < 0
                    or up_down < 0
                    or left_right > n - 1
                    or up_down > m - 1
                ):
                    continue
                this = (left_right, up_down)
                if this in fresh:
                    fresh.remove(this)
                    rotten = [(left_right, up_down, minu + 1)] + rotten
        if fresh:
            return -1
        elif not rotten:
            return 0
        else:
            return max((i[2] for i in rotten))


class Solution2:
    def orangesRotting(self, grid: List[List[int]]) -> int:
        n = len(grid)
        m = len(grid[0])
        rotten = set()
        fresh = set()
        for i in range(n):
            for j in range(m):
                orange = grid[i][j]
                if orange == 2:
                    rotten.add((i, j))
                elif orange == 1:
                    fresh.add((i, j))
        times = 0
        while fresh:            
            new_rotten = set()
            for u, c in rotten:
                for a, b in ((1, 0), (0, 1), (-1, 0), (0, -1)):
                    left_right = u + a
                    up_down = c + b
                    if (
                        left_right < 0
                        or up_down < 0
                        or left_right > n - 1
                        or up_down > m - 1
                    ):
                        continue
                    if (left_right, up_down) in fresh:
                        fresh.remove((left_right, up_down))
                        new_rotten.add((left_right, up_down))
            
            times += 1
            if not new_rotten:
                return -1
            rotten = new_rotten
        return times


grid = [[2, 1, 1], [1, 1, 0], [0, 1, 1]]
# print(Solution().orangesRotting(grid))
print(Solution2().orangesRotting(grid))
