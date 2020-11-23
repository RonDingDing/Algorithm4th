from typing import List


class CountingSort:
    def __init__(self, array: List):
        n = len(array)
        if n <= 1:
            return

        max_num = max(array)
        count = [0 for i in range(0, max_num + 1)]
        for i in range(n):
            count[array[i]] += 1
        print(count)

        for i in range(1, max_num + 1):
            count[i] = count[i - 1] + count[i]
        print(count)

        rank = [0 for i in range(0, n)]
        for i in range(n - 1, -1, -1):
            index = count[array[i]] - 1
            rank[index] = array[i]
            count[array[i]] -= 1
        array = rank
        print(array)


if __name__ == "__main__":
    a = [4, 7, 2, 1, 3, 4]
    CountingSort(a)
