from typing import List, Generator, Tuple
from itertools import combinations


class Solution:
    def combination(
        self, elements: List, num: int
    ) -> Generator[Tuple[int, ...], None, None]:
        len_element = len(elements)
        indices = list(range(num))
        i = num - 1
        running = True
        while running:
            while indices[i] < len_element:
                yield tuple(elements[p] for p in indices)
                indices[i] += 1

            while indices[i] >= len_element and i > 0:
                i -= 1
            indices[i] += 1
            n = 1
            for m in range(i + 1, num):
                indices[m] = indices[i] + n
                n += 1
            i = num - 1
            if indices[0] > len_element - num:
                break


s = Solution()
for t in s.combination(
    [0, 1, 9, 3, 4],
    3,
):
    print(t)
