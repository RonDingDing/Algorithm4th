# HJ5 进制转换
# 题目
# 题解(110)
# 讨论(1k)
# 排行
# 面经new
# 简单  通过率：35.85%  时间限制：1秒  空间限制：32M
# 知识点
# 字符串
# warning 校招时部分企业笔试将禁止编程题跳出页面，为提前适应，练习时请使用在线自测，而非本地IDE。
# 描述
# 写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。

# 数据范围：保证结果在 1 \le n \le 2^{31}-1 \1≤n≤2
# 31
#  −1
# 输入描述：
# 输入一个十六进制的数值字符串。

# 输出描述：
# 输出该数值的十进制字符串。不同组的测试用例用\n隔开。

# 示例1
class Solution:
    def to_hex(self, line: str) -> int:
        string = line.replace("0X", "").replace("0x", "")
        dic = {"a": 10, "b": 11, "c": 12, "d": 13, "e": 14, "f": 15}
        result = 0
        for i, s in enumerate(string[::-1]):
            s = s.lower()
            if s in dic:
                num = dic[s]
            else:
                num = int(s)
            result += num * 16 ** i
        return result


while True:
    try:
        line = input()
        print(Solution().to_hex(line))
    except Exception:
        break
