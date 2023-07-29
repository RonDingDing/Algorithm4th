from typing import List, Optional


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
    def removeNthFromEnd(self, head: Optional[ListNode], n: int) -> Optional[ListNode]:
        if not head:
            return None
        cur = head
        count = 0
        while cur:
            count += 1
            cur = cur.next

        new = point = head
        num = count - n % count
        if num == count:
            return head.next
        for _ in range(num - 1):
            if point:
                point = point.next
            else:
                point = None
        if point and point.next:
            point.next = point.next.next
        return new


class Solution2:
    def removeNthFromEnd(self, head: Optional[ListNode], n: int) -> Optional[ListNode]:
        new = slow = fast = ListNode(0, head)
        for _ in range(n + 1):
            fast = fast.next
        while fast:
            fast = fast.next
            slow = slow.next
        slow.next = slow.next.next
        return new.next


heads = [1, 2, 3, 4, 5]
n = 3
link = None
for i in heads[::-1]:
    node = ListNode(i)
    node.next = link
    link = node
print(link)
# print(Solution().removeNthFromEnd(link, n))
print(Solution2().removeNthFromEnd(link, n))
