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
