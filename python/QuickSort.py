from random import shuffle
from typing import List


class Quick:
    def __init__(self, array: List):
        shuffle(array)
        self.sort(array, 0, len(array) - 1)

    def sort(self, array: List, low: int, high: int):
        if high <= low:
            return
        seperate = self.partition(array, low, high)
        if self.less(array, low, seperate) and self.less(array, seperate + 1, high):
            return
        self.sort(array, low, seperate)
        self.sort(array, seperate + 1, high)

    def partition(self, array: List, low: int, high: int):
        pivot, i = low, low
        j = high
        while True:
            while self.less(array, i, pivot):
                if i == high:
                    break
                i += 1
            while self.less(array, pivot, j):
                if j == low:
                    break
                j -= 1
            if i >= j:
                break
            self.exch(array, i, j)
        self.exch(array, low, j)
        return j

    def less(self, array: List, a: int, b: int):
        return array[a] < array[b]

    def exch(self, array: List, a: int, b: int):
        array[a], array[b] = array[b], array[a]


class QuickSort:
    def __init__(self, array: List):
        self.sort(array, 0, len(array) - 1)

    def sort(self, array: List, start: int, end: int):
        if start >= end:
            return
        pivot = self.partition(array, start, end)
        self.sort(array, start, pivot - 1)
        self.sort(array, pivot + 1, end)

    def partition(self, array: List, start: int, end: int):
        pivot = end
        i = start
        for j in range(start, end):
            ele = array[j]
            if ele <= pivot:
                self.exchange(array, i, j)
                i += 1
        self.exchange(array, i, pivot)

        return pivot

    def exchange(self, array: List, a: int, b: int):
        array[a], array[b] = array[b], array[a]


if __name__ == "__main__":
    a = [1, 2, 3, 4]

    QuickSort(a)
    print(a)
