# 举例：一个ip地址为10.0.3.193
# 每段数字             相对应的二进制数
# 10                   00001010
# 0                    00000000
# 3                    00000011
# 193                  11000001

# 组合起来即为：00001010 00000000 00000011 11000001,转换为10进制数就是：167773121，即该IP地址转换后的数字就是它了。

# 数据范围：保证输入的是合法的 IP 序列

# 输入描述：
# 输入 
# 1 输入IP地址
# 2 输入10进制型的IP地址

# 输出描述：
# 输出
# 1 输出转换成10进制的IP地址
# 2 输出转换后的IP地址

# 示例1
# 输入：
# 10.0.3.193
# 167969729
# 复制
# 输出：
# 167773121
# 10.3.3.193

class Solution:
    def change(self, line: str) -> str:
        if line.count(".") == 3:
            return self.from_ip(line)
        else:
            return self.from_int(line)

    def from_ip(self, line: str) -> str:
        shift = 24
        result = 0
        for li in line.split("."):
            result += int(li) << shift
            shift -= 8
        return str(result)

    def from_int(self, line: str) -> str:
        result = ""
        shift = 24
        amp = [0xff000000,  0x00ff0000, 0x0000ff00, 0x000000ff]
        for i in range(4):
            a = amp[i]
            result += str((int(line) & a) >> shift)
            if i != 3:
                result += '.'
            shift -= 8
        return result


while True:
    try:
        line = input()
        result = Solution().change(line)
        print(result)
    except Exception:
        break
