# 给定一个字符串 s 和一个字符串数组 words。 words 中所有字符串 长度相同。

#  s 中的 串联子串 是指一个包含  words 中所有字符串以任意顺序排列连接起来的子串。

# 例如，如果 words = ["ab","cd","ef"]， 那么 "abcdef"， "abefcd"，"cdabef"， "cdefab"，"efabcd"， 和 "efcdab" 都是串联子串。 "acdbef" 不是串联子串，因为他不是任何 words 排列的连接。
# 返回所有串联字串在 s 中的开始索引。你可以以 任意顺序 返回答案。

#  

# 示例 1：

# 输入：s = "barfoothefoobarman", words = ["foo","bar"]
# 输出：[0,9]
# 解释：因为 words.length == 2 同时 words[i].length == 3，连接的子字符串的长度必须为 6。
# 子串 "barfoo" 开始位置是 0。它是 words 中以 ["bar","foo"] 顺序排列的连接。
# 子串 "foobar" 开始位置是 9。它是 words 中以 ["foo","bar"] 顺序排列的连接。
# 输出顺序无关紧要。返回 [9,0] 也是可以的。
# 示例 2：

# 输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
# 输出：[]
# 解释：因为 words.length == 4 并且 words[i].length == 4，所以串联子串的长度必须为 16。
# s 中没有子串长度为 16 并且等于 words 的任何顺序排列的连接。
# 所以我们返回一个空数组。
# 示例 3：

# 输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
# 输出：[6,9,12]
# 解释：因为 words.length == 3 并且 words[i].length == 3，所以串联子串的长度必须为 9。
# 子串 "foobarthe" 开始位置是 6。它是 words 中以 ["foo","bar","the"] 顺序排列的连接。
# 子串 "barthefoo" 开始位置是 9。它是 words 中以 ["bar","the","foo"] 顺序排列的连接。
# 子串 "thefoobar" 开始位置是 12。它是 words 中以 ["the","foo","bar"] 顺序排列的连接。
#  

# 提示：

# 1 <= s.length <= 104
# 1 <= words.length <= 5000
# 1 <= words[i].length <= 30
# words[i] 和 s 由小写英文字母组成

from typing import List
from collections import Counter


class Solution:
    def findSubstring(self, s: str, words: List[str]) -> List[int]:
        cnt = {}
        for w in words:
            hashes = hash(w)
            if hashes not in cnt:
                cnt[hashes] = 1
            else:
                cnt[hashes] += 1
        num = len(words)
        each_len = len(words[0])
        all_len = num * each_len
        result = []
        for j in range(each_len):
            start = j
            while True:
                sub = s[start: start + all_len]
                if len(sub) < all_len:
                    break
                this_cnt = {}
                for i in range(len(sub) // each_len):
                    chunk = sub[i*each_len: (i+1)*each_len]
                    has = hash(chunk)
                    if has not in this_cnt:
                        this_cnt[has] = 1
                    else:
                        this_cnt[has] += 1
                if cnt == this_cnt:
                    result.append(start)
                start += each_len
        return result


class Solution1:
    def findSubstring(self, s: str, words: List[str]) -> List[int]:
        res = []
        m, n, ls = len(words), len(words[0]), len(s)
        for i in range(n):
            if i + m * n > ls:
                break
            differ = Counter()
            for j in range(m):
                word = s[i + j * n: i + (j + 1) * n]
                differ[word] += 1
            for word in words:
                differ[word] -= 1
                if differ[word] == 0:
                    del differ[word]
            for start in range(i, ls - m * n + 1, n):
                if start != i:
                    word = s[start + (m - 1) * n: start + m * n]
                    differ[word] += 1
                    if differ[word] == 0:
                        del differ[word]
                    word = s[start - n: start]
                    differ[word] -= 1
                    if differ[word] == 0:
                        del differ[word]
                if len(differ) == 0:
                    res.append(start)
        return res


class Solution3:
    def findSubstring(self, s: str, words: List[str]) -> List[int]:
        if not words:
            return []
        word_len, word_num = len(words[0]), len(s)
        all_word_len = word_len * len(words)  # 子串的长度

        word_dict = {}  # words的哈希表
        for word in words:
            word_dict[word] = word_dict.get(word, 0) + 1

        ans = []
        for offset in range(word_len):
            lo, lo_max = offset, word_num - all_word_len
            while lo <= lo_max:
                tmp_dict = word_dict.copy()
                match = True
                for hi in range(lo + all_word_len, lo, -word_len):    # 从尾到头搜索单词
                    word = s[hi - word_len: hi]
                    if word not in tmp_dict or tmp_dict.get(word, 0) == 0:
                        match = False
                        break   # 当前单词不符合要求 直接停止这个子串的搜索
                    tmp_dict[word] -= 1
                if match:
                    ans.append(lo)
                lo = hi     # 对lo直接赋值 这就是相比法二优化的地方
        return ans


s = "barfoothefoobarman"
"barfoo"
"arfoot"
"rfooth"
"foothe"
words = ["foo", "bar"]

print(Solution3().findSubstring(s, words))
