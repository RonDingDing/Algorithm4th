# 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。

#

# 示例 1：


# 输入：head = [1,2,3,4,5], k = 2
# 输出：[4,5,1,2,3]
# 示例 2：


# 输入：head = [0,1,2], k = 4
# 输出：[2,0,1]
#

# 提示：

# 链表中节点的数目在范围 [0, 500] 内
# -100 <= Node.val <= 100
# 0 <= k <= 2 * 109


from typing import Optional, List


# Definition for singly-linked list.
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
    def rotateRight(self, head: ListNode, k: int) -> ListNode:
        if head is None or head.next is None:
            return head
        length = 0
        cur = head
        while cur:
            length += 1
            cur = cur.next
        m = k % length
        if m == 0:
            return head
        fast = slow = head
        for i in range(m):
            fast = fast.next
        while fast.next:
            fast = fast.next
            slow = slow.next
        new_head = slow.next
        slow.next = None
        fast.next = head
        return new_head


heads = [1, 2, 3, 4, 5]
c = None
for i in heads[::-1]:
    n = ListNode(i)
    n.next = c
    c = n
print(c)
print(Solution().rotateRight(c, 2))
