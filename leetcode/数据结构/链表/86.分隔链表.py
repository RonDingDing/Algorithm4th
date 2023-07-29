# 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。

# 你应当 保留 两个分区中每个节点的初始相对位置。

#

# 示例 1：


# 输入：head = [1,4,3,2,5,2], x = 3
# 输出：[1,2,2,4,3,5]
# 示例 2：

# 输入：head = [2,1], x = 2
# 输出：[1,2]
#

# 提示：

# 链表中节点的数目在范围 [0, 200] 内
# -100 <= Node.val <= 100
# -200 <= x <= 200

from typing import Optional


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

    def __repr__(self) -> str:
        cur = self
        string = ""
        while True:
            if cur is None:
                break
            string += f"{cur.val}->"
            cur = cur.next
        return string


class Solution:
    def partition(self, head: Optional[ListNode], x: int) -> Optional[ListNode]:
        left, right = ListNode(), ListNode()
        cur = head
        left_cur, right_cur = left, right
        while cur:
            if cur.val < x:
                left_cur.next = cur
                left_cur = left_cur.next
                cur = cur.next
            else:
                right_cur.next = cur
                right_cur = right_cur.next
                cur = cur.next
        right_cur.next = None
        left_cur.next = right.next
        return left.next


heads = [1, 4, 3, 2, 5, 2]
c = None
for i in heads[::-1]:
    n = ListNode(i)
    n.next = c
    c = n

x = 3
l = Solution().partition(c, x)
print(l)
