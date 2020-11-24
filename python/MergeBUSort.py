from typing import List


class MergeBU:
    def __init__(self, array: List):
        self.sort_len(array)

    def sort_len(self, array):
        self.list = [None] * len(array)
        self.sort(array)

    def sort(self, array: List):
        sz = 1
        while sz < len(array):
            low = 0
            while low < len(array) - sz:
                seperate = low + sz - 1
                high = min(low + sz + sz - 1, len(array) - 1)
                self.merge(a, low, seperate, high)
                low += sz + sz
            sz = sz + sz

    def merge(self, array: List, low: int, seperate: int, high: int):
        if not low <= seperate < high:
            return
        i = low
        j = seperate + 1

        for k in range(low, high + 1):
            self.list[k] = array[k]
        for k in range(low, high + 1):
            if i > seperate:
                array[k] = self.list[j]
                j += 1
            elif j > high:
                array[k] = self.list[i]
                i += 1
            elif self.less(self.list, j, i):
                array[k] = self.list[j]
                j += 1
            else:
                array[k] = self.list[i]
                i += 1

    def less(self, array: List, a: int, b: int):
        return array[a] < array[b]

    def exch(self, array: List, a: int, b: int):
        array[a], array[b] = array[b], array[a]


if __name__ == "__main__":
    from random import randint

    b = randint(0, 100)
    c = randint(1, 10)
    print(b)
    print(c)
    a = list(range(0, b, c))

    from random import shuffle

    print(a)
    shuffle(a)
    print(a)
    Merge(a)
    print(a)
