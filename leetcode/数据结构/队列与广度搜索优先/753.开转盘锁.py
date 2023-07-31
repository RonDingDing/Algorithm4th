# 有一个需要密码才能打开的保险箱。密码是 n 位数, 密码的每一位都是范围 [0, k - 1] 中的一个数字。

# 保险箱有一种特殊的密码校验方法，你可以随意输入密码序列，保险箱会自动记住 最后 n 位输入 ，如果匹配，则能够打开保险箱。

# 例如，正确的密码是 "345" ，并且你输入的是 "012345" ：
# 输入 0 之后，最后 3 位输入是 "0" ，不正确。
# 输入 1 之后，最后 3 位输入是 "01" ，不正确。
# 输入 2 之后，最后 3 位输入是 "012" ，不正确。
# 输入 3 之后，最后 3 位输入是 "123" ，不正确。
# 输入 4 之后，最后 3 位输入是 "234" ，不正确。
# 输入 5 之后，最后 3 位输入是 "345" ，正确，打开保险箱。
# 在只知道密码位数 n 和范围边界 k 的前提下，请你找出并返回确保在输入的 某个时刻 能够打开保险箱的任一 最短 密码序列 。

#  

# 示例 1：

# 输入：n = 1, k = 2
# 输出："10"
# 解释：密码只有 1 位，所以输入每一位就可以。"01" 也能够确保打开保险箱。
# 示例 2：

# 输入：n = 2, k = 2
# 输出："01100"
# 解释：对于每种可能的密码：
# - "00" 从第 4 位开始输入。
# - "01" 从第 1 位开始输入。
# - "10" 从第 3 位开始输入。
# - "11" 从第 2 位开始输入。
# 因此 "01100" 可以确保打开保险箱。"01100"、"10011" 和 "11001" 也可以确保打开保险箱。
#  

# 提示：

# 1 <= n <= 4
# 1 <= k <= 10
# 1 <= kn <= 4096



from queue import Queue
from typing import List


class Solution:
    # 双向bfs搜索：从start到target和从target到start
    def openLock(self, deadends: List[str], target: str) -> int:
        start = "0000"

        disdic = {}.fromkeys(deadends, (-1, 0))
        if start in disdic or target in disdic:
            return -1
        if target == start:
            return 0

        queue = Queue()
        queue.put((start, 0, 1))
        queue.put((target, 0, -1))

        while not queue.empty():
            cur, step, clockwise = queue.get()

            for i in range(4):
                for p in (1, -1):
                    neighbour = cur[:i] + str((int(cur[i]) + p) % 10) + cur[i + 1 :]
                    if neighbour in disdic:
                        dis, ty = disdic[neighbour]
                        if dis != -1 and ty != 0:
                            if ty + clockwise == 0:
                                return dis + step + 1
                    else:
                        if neighbour == target and clockwise == 1:
                            return step + 1
                        if neighbour == start and clockwise == -1:
                            return step + 1
                        else:
                            disdic[neighbour] = (step + 1, clockwise)
                            queue.put((neighbour, step + 1, clockwise))

        return -1


class Solution2:
    # 双向bfs搜索：从start到target和从target到start
    def openLock(self, deadends: List[str], target: str) -> int:
        start = "0000"

        deadends_yes = set(deadends)
        deadends_no = set(deadends)

        if start in deadends_yes or target in deadends_yes:
            return -1
        if target == start:
            return 0

        queue_yes = Queue()
        queue_yes.put((start, 0))
        yes_has = not queue_yes.empty()

        queue_no = Queue()
        queue_no.put((target, 0))
        no_has = not queue_no.empty()

        while yes_has or no_has:
            if yes_has:
                result = self.operate_queue(queue_yes, deadends_yes, target)
                if result:
                    return result
            if no_has:
                result = self.operate_queue(queue_no, deadends_no, start)
                if result:
                    return result

            yes_has = not queue_yes.empty()
            no_has = not queue_no.empty()

        return -1

    def operate_queue(self, queue, deadend, final) -> int:
        cur, step = queue.get()
        deadend.add(cur)
        for i in range(4):
            for p in (1, -1):
                neighbour = cur[:i] + str((int(cur[i]) + p) % 10) + cur[i + 1 :]
                if neighbour == final:
                    return step + 1

                if neighbour not in deadend:
                    queue.put((neighbour, step + 1))
                    deadend.add(neighbour)

        return 0


class Solution3:
    # 单向bfs搜索：从start到target
    def openLock(self, deadends: List[str], target: str) -> int:
        start = "0000"
        deadend = set(deadends)
        if start in deadends or target in deadends:
            return -1
        if target == start:
            return 0
        queue = Queue()
        queue.put((start, 0))
        while not queue.empty():
            cur, step = queue.get()
            deadend.add(cur)
            for i in range(4):
                for p in (1, -1):
                    neighbour = cur[:i] + str((int(cur[i]) + p) % 10) + cur[i + 1 :]
                    if neighbour == target:
                        return step + 1

                    if neighbour not in deadends:
                        queue.put((neighbour, step + 1))
                        deadend.add(neighbour)

        return -1


if __name__ == "__main__":
    s = Solution()
    print(s.openLock(deadends=["0001"], target="0000"))
