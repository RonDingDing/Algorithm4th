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
    def mergeTwoLists(
        self, list1: Optional[ListNode], list2: Optional[ListNode]
    ) -> Optional[ListNode]:
        this1 = list1
        this2 = list2
        head = prev = None
        b = False
        while (this1 or this2) and not b:
            if this1 is not None and this2 is not None:
                if this1.val <= this2.val:
                    val = this1.val
                    this1 = this1.next
                else:
                    val = this2.val
                    this2 = this2.next
                cur = ListNode(val)
            elif this1 is None:
                cur = this2
                b = True
            else:
                cur = this1
                b = True

            if head is None:
                head = prev = cur
            else:
                prev.next = cur
                prev = cur
        return head

lists = [[7],[49]]
linked = []
for one in lists:
    c = None
    for i in one[::-1]:
        n = ListNode(i)
        n.next = c
        c = n
    linked.append(c)
print(linked)
print(Solution().mergeTwoLists(*linked))
