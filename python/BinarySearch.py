from QuickSort import QuickSort
from typing import List


class BinarySearch:
    def __init__(self, array: List, value: int):
        a = array[:]
        QuickSort(a)
        # self.result = self.search(a, value)
        self.result = self.search_recursive(a, value, 0, len(a) - 1)

    def search(self, array: List, value: int):
        start = 0
        end = len(array) - 1
        while start <= end:
            mid = start + (end - start) // 2
            mid_val = array[mid]
            if mid_val == value:
                return mid
            elif mid_val < value:
                start = mid + 1
            else:
                end = mid - 1

        return -1

    def search_recursive(self, array: List, value: int, start: int, end: int):
        if start > end:
            return -1
        mid = start + (end - start) // 2
        mid_val = array[mid]
        if mid_val == value:
            return mid
        elif mid_val > value:
            return self.search_recursive(array, value, 0, mid - 1)
        elif mid_val < value:
            return self.search_recursive(array, value, mid + 1, end)



#TODO 变体一：查找第一个值等于给定值的元素下标
class BinarySearchFirstValuse:
    def __init__(self, array: List, value: int):
        a = array[:]
        QuickSort(a)




if __name__ == "__main__":
    a = BinarySearch([1, 2, 3, 4, 99], 99)
    print(a.result)
