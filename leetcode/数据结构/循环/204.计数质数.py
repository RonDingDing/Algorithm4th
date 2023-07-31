# 给定整数 n ，返回 所有小于非负整数 n 的质数的数量 。

#

# 示例 1：

# 输入：n = 10
# 输出：4
# 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
# 示例 2：

# 输入：n = 0
# 输出：0
# 示例 3：

# 输入：n = 1
# 输出：0
#

# 提示：


# 0 <= n <= 5 * 106
class Solution:
    def countPrimes(self, n: int) -> int:
        if n < 2:
            return 0
        is_primes = [1] * n
        is_primes[0] = is_primes[1] = 0
        for i in range(2, int(n**0.5) + 1):
            if is_primes[i] == 1:
                is_primes[i * i : n : i] = [0] * len(is_primes[i * i : n : i])
        return sum(is_primes)


class Solution2:
    def countPrimes(self, n: int) -> int:
        if n < 2:
            return 0
        is_primes = [1] * n
        is_primes[0] = is_primes[1] = 0
        for i in range(2, int(n**0.5) + 1):
            if is_primes[i] == 1:
                for j in range(i * i, n, i):
                    is_primes[j] = 0
        return sum(is_primes)


print(Solution2().countPrimes(100))
