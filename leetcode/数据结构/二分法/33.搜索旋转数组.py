# 整数数组 nums 按升序排列，数组中的值 互不相同 。

# 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。

# 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。

# 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。

#  

# 示例 1：

# 输入：nums = [4,5,6,7,0,1,2], target = 0
# 输出：4
# 示例 2：

# 输入：nums = [4,5,6,7,0,1,2], target = 3
# 输出：-1
# 示例 3：

# 输入：nums = [1], target = 0
# 输出：-1
#  

# 提示：

# 1 <= nums.length <= 5000
# -104 <= nums[i] <= 104
# nums 中的每个值都 独一无二
# 题目数据保证 nums 在预先未知的某个下标上进行了旋转
# -104 <= target <= 104

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
