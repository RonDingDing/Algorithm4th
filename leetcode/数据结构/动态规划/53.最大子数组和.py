# 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

# 子数组 是数组中的一个连续部分。


# 示例 1：

# 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
# 输出：6
# 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
# 示例 2：

# 输入：nums = [1]
# 输出：1
# 示例 3：

# 输入：nums = [5,4,-1,7,8]
# 输出：23


# 提示：

# 1 <= nums.length <= 105
# -104 <= nums[i] <= 104

from typing import List, Union


class Solution:
    def maxSubArray(self, nums: List[int]) -> Union[float, int]:
        if not nums:
            return float("-inf")
        res = nums[0]
        pre_max = nums[0]
        pre_min = nums[0]
        for num in nums[1:]:
            cur_max = max(pre_max + num, pre_min + num, num)
            cur_min = min(pre_max + num, pre_min + num, num)
            res = max(res, cur_max)
            pre_max = cur_max
            pre_min = cur_min
        return res
