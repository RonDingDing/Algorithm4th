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
        deadends = set(deadends)
        if start in deadends or target in deadends:
            return -1
        if target == start:
            return 0
        queue = Queue()
        queue.put((start, 0))
        while not queue.empty():
            cur, step = queue.get()
            deadends.add(cur)
            for i in range(4):
                for p in (1, -1):
                    neighbour = cur[:i] + str((int(cur[i]) + p) % 10) + cur[i + 1 :]
                    if neighbour == target:
                        return step + 1

                    if neighbour not in deadends:
                        queue.put((neighbour, step + 1))
                        deadends.add(neighbour)

        return -1


if __name__ == "__main__":
    s = Solution()
    print(s.openLock(deadends=["0001"], target="0000"))
