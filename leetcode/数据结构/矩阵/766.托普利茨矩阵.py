from typing import List


class Solution:
    def isToeplitzMatrix(self, matrix: List[List[int]]) -> bool:

        len_y = len(matrix)
        if len_y == 0:
            return True

        len_x = len(matrix[0])

        for i in range(len_x):
            start_point_x = 0
            start_point_y = i
            value = matrix[start_point_x][start_point_y]
            while start_point_x < len_y and start_point_y < len_x:
                print(
                    start_point_x,
                    start_point_y,
                    "-",
                    matrix[start_point_x][start_point_y],
                )
                if matrix[start_point_x][start_point_y] != value:
                    return False
                start_point_x += 1
                start_point_y += 1
            print()

        for i in range(1, len_y):
            start_point_x = i
            start_point_y = 0

            value = matrix[start_point_x][start_point_y]
            while start_point_x < len_y and start_point_y < len_x:
                print(
                    start_point_x,
                    start_point_y,
                    "-",
                    matrix[start_point_x][start_point_y],
                )
                if matrix[start_point_x][start_point_y] != value:
                    return False
                start_point_x += 1
                start_point_y += 1
            print()
        return True


matrix = [[1, 2, 3, 4], [5, 1, 2, 3], [9, 5, 1, 2]]
print(Solution().isToeplitzMatrix(matrix))
