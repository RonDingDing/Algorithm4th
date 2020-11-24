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


class BinarySearchFirstValue:
    def __init__(self, array: List, value: int):
        a = array[:]
        QuickSort(a)
        self.result_first = self.search_first(a, value)
        self.result_last = self.search_last(a, value)
        self.result_first_greater_equal_to = self.search_first_greater_equal_to(a, value)
        self.result_last_smaller_equal_to = self.search_last_smaller_equal_to(a, value)

    def search_first(self, array: List, value: int):
        # 查找第一个值等于给定值的元素
        length = len(array)
        start = 0
        end = length - 1

        while start <= end:
            mid = start + (end - start) // 2
            mid_val = array[mid]
            if mid_val >= value:
                end = mid - 1
            elif mid_val <= value:
                start = mid + 1

        if (start < length and array[start] == value):
            return start
        else:
            return -1

    def search_last(self, array: List, value: int):
        # 查找最后一个值等于给定值的元素
        length = len(array)
        start = 0
        end = length - 1

        while start <= end:
            mid = start + (end - start) // 2
            mid_val = array[mid]
            if mid_val <= value:
                start = mid + 1
            elif mid_val >= value:
                end = mid - 1

        if (end < length and array[end] == value):
            return end
        else:
            return -1

    def search_first_greater_equal_to(self, array: List, value: int):
        # 查找第一个大于等于给定值的元素
        length = len(array)
        start = 0
        end = length - 1

        while start <= end:
            mid = start + (end - start) // 2
            mid_val = array[mid]

            if mid_val >= value:
                if mid == 0 or array[mid - 1] < value:
                    return mid
                else:
                    end = mid - 1
            else:
                start = mid + 1

        return -1

    def search_last_smaller_equal_to(self, array: List, value: int):
        # 查找最后一个小于等于给定值的元素
        length = len(array)
        start = 0
        end = length - 1

        while start <= end:
            mid = start + (end - start) // 2
            mid_val = array[mid]

            if mid_val > value:
                end = mid - 1
            else:
                if mid == length - 1 or array[mid + 1] > value:
                    return mid
                else:
                    start = mid + 1

        return -1


if __name__ == "__main__":
    a = BinarySearch([1, 2, 3, 4, 4, 99], 4)
    b = BinarySearchFirstValue([1, 2, 3, 3, 4, 4, 4, 4, 99], 4)
    print(b.result_first)
    print(b.result_last)
    print(b.result_first_greater_equal_to)
    print(b.result_last_smaller_equal_to)
