# 开发一个坐标计算工具， A表示向左移动，D表示向右移动，W表示向上移动，S表示向下移动。从（0,0）点开始移动，从输入字符串里面读取一些坐标，并将最终输入结果输出到输出文件里面。

# 输入：

# 合法坐标为A(或者D或者W或者S) + 数字（两位以内）

# 坐标之间以;分隔。

# 非法坐标点需要进行丢弃。如AA10;  A1A;  $%$;  YAD; 等。

# 下面是一个简单的例子 如：

# A10;S20;W10;D30;X;A1A;B10A11;;A10;

# 处理过程：

# 起点（0,0）

# +   A10   =  （-10,0）

# +   S20   =  (-10,-20)

# +   W10  =  (-10,-10)

# +   D30  =  (20,-10)

# +   x    =  无效

# +   A1A   =  无效

# +   B10A11   =  无效

# +  一个空 不影响

# +   A10  =  (10,-10)

# 结果 （10， -10）

from typing import Tuple


class Solution:
    def move(self, line: str, sit: Tuple[int, ...]) -> Tuple[int, ...]:
        for l in line.split(";"):
            if not l:
                continue
            f = l[0]
            if f not in "ASWD":
                continue
            try:
                num = int(l[1:])
                if num > 99:
                    raise ValueError

                if f == "A":
                    sit = (sit[0] - num, sit[1])
                elif f == "D":
                    sit = (sit[0] + num, sit[1])
                elif f == "W":
                    sit = (sit[0], sit[1] + num)
                elif f == "S":
                    sit = (sit[0], sit[1] - num)
            except ValueError:
                continue
        return sit


sit = (0, 0)
while True:
    try:
        line = input()
        sit = Solution().move(line, sit)
        print(f"{sit[0] },{sit[1]}")
    except Exception:
        break
