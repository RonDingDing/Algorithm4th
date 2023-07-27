from typing import Optional


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

    def __repr__(self) -> str:
        head = self
        string = ""
        while getattr(head, 'val', None) is not None:
            string += f"{head.val}->"
            head = head.next
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
