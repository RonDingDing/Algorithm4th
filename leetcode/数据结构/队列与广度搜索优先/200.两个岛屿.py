# 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。

# 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。

# 此外，你可以假设该网格的四条边均被水包围。

#  

# 示例 1：

# 输入：grid = [
#   ["1","1","1","1","0"],
#   ["1","1","0","1","0"],
#   ["1","1","0","0","0"],
#   ["0","0","0","0","0"]
# ]
# 输出：1
# 示例 2：

# 输入：grid = [
#   ["1","1","0","0","0"],
#   ["1","1","0","0","0"],
#   ["0","0","1","0","0"],
#   ["0","0","0","1","1"]
# ]
# 输出：3
#  

# 提示：

# m == grid.length
# n == grid[i].length
# 1 <= m, n <= 300
# grid[i][j] 的值为 '0' 或 '1'

from queue import Queue
from typing import List


class Solution:
    def numIslands(self, grid: List[List[str]]) -> int:
        if not grid:
            return 0
        num_island = 0
        self.grid = grid
        self.row_len = len(grid[0])
        self.col_len = len(grid)
        self.around = ((0, 1), (1, 0), (0, -1), (-1, 0))
        for x in range(self.col_len):
            for y in range(self.row_len):
                if grid[x][y] == "1":
                    num_island += 1
                    self.bfs(x, y)
        return num_island

    def bfs(self, x, y):
        queue = Queue()
        queue.put((x, y))
        while not queue.empty():
            i, j = queue.get()
            if self.grid[i][j] == "1":
                self.grid[i][j] = "0"
                for a, b in self.around:
                    a += i
                    b += j
                    if (
                        0 <= a < self.col_len
                        and 0 <= b < self.row_len
                        and self.grid[a][b] == "1"
                    ):
                        queue.put((a, b))


class Solution2:
    def numIslands(self, grid: List[List[str]]) -> int:
        if not grid:
            return 0
        num_island = 0
        self.grid = grid
        self.row_len = len(grid[0])
        self.col_len = len(grid)
        for x in range(self.col_len):
            for y in range(self.row_len):
                if grid[x][y] == "1":
                    self.dfs(x, y)
                    num_island += 1
        return num_island

    def dfs(self, x, y):
        if (
            x < 0
            or x >= self.col_len
            or y < 0
            or y >= self.row_len
            or self.grid[x][y] != "1"
        ):
            return

        self.grid[x][y] = "0"
        self.dfs(x + 1, y)
        self.dfs(x - 1, y)
        self.dfs(x, y - 1)
        self.dfs(x, y + 1)


if __name__ == "__main__":
    s = Solution2()
    b = s.numIslands(
        [
            [
                "1",
                "1",
                "1",
            ],
            [
                "0",
                "1",
                "1",
            ],
            [
                "0",
                "0",
                "0",
            ],
            [
                "0",
                "0",
                "0",
            ],
        ]
    )
    print(b)
