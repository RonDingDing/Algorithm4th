from typing import List


class Solution:
    def search(self, nums: List[int], target: int) -> int:
        start = 0
        end = len(nums) - 1
        while start <= end:
            mid = start + ((end - start) >> 1)

            if nums[mid] == target:
                return mid
            elif nums[start] == target:
                return start
            elif nums[end] == target:
                return end
            elif target < nums[end]:
                if nums[end] < nums[mid] or nums[mid] < target:
                    start = mid + 1
                elif target < nums[mid]:
                    end = mid - 1
                else:
                    return -1
            elif nums[end] < target:
                if target < nums[mid] or nums[mid] < nums[end]:
                    end = mid - 1
                elif nums[end] < nums[mid]:
                    start = mid + 1
                else:
                    return -1

        return -1

    def search2(self, nums: List[int], target: int) -> int:
        start = 0
        end = len(nums) - 1
        while start <= end:
            mid = start + ((end - start) >> 1)
            if nums[mid] == target:
                return mid
            elif nums[mid] < nums[end]:
                if target < nums[mid] or target > nums[end]:
                    end = mid - 1
                else:
                    start = mid + 1
            else:
                if target < nums[start]  or target > nums[mid]:
                    start = mid + 1
                else:
                    end = mid - 1

        return -1

if __name__ == '__main__':
    nums = [ 4, 5, 6, 7, 0, 1, 2,]
    target = 2
    a = Solution()
    print(a.search2(nums, target))
