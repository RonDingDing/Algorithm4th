# 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。

# 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。

#

# 示例 1：

# 输入: s = "leetcode", wordDict = ["leet", "code"]
# 输出: true
# 解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
# 示例 2：

# 输入: s = "applepenapple", wordDict = ["apple", "pen"]
# 输出: true
# 解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
#      注意，你可以重复使用字典中的单词。
# 示例 3：

# 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
# 输出: false
#

# 提示：

# 1 <= s.length <= 300
# 1 <= wordDict.length <= 1000
# 1 <= wordDict[i].length <= 20
# s 和 wordDict[i] 仅有小写英文字母组成
# wordDict 中的所有字符串 互不相同

from queue import Queue
from typing import List


class Solution:
    def wordBreak(self, s: str, wordDict: List[str]) -> bool:
        que = [s]

        while que:
            this = que.pop()
            for word in wordDict:
                if not this.startswith(word):
                    continue
                that = this[:]
                if that.startswith(word):
                    that = that[len(word) :]
                if not that:
                    return True
                elif that not in que:
                    que = [that] + que

        return False


class Solution2:
    def wordBreak(self, s: str, wordDict: List[str]) -> bool:
        length = len(s)
        dp = [False] * (length + 1)
        dp[0] = True
        for i in range(length):
            if dp[i]:
                for w in wordDict:
                    if w == s[i : i + len(w)]:
                        dp[i + len(w)] = True
        return dp[-1]


s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab"
wordDict = [
    "a",
    "aa",
    "aaa",
    "aaaa",
    "aaaaa",
    "aaaaaa",
    "aaaaaaa",
    "aaaaaaaa",
    "aaaaaaaaa",
    "aaaaaaaaaa",
]

# s = "catskicatcats"
# wordDict = ["cats", "cat", "dog", "ski"]
print(Solution2().wordBreak(s, wordDict))
