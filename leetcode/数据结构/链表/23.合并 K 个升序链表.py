# 给你一个链表数组，每个链表都已经按升序排列。

# 请你将所有链表合并到一个升序链表中，返回合并后的链表。

#

# 示例 1：

# 输入：lists = [[1,4,5],[1,3,4],[2,6]]
# 输出：[1,1,2,3,4,4,5,6]
# 解释：链表数组如下：
# [
#   1->4->5,
#   1->3->4,
#   2->6
# ]
# 将它们合并到一个有序链表中得到。
# 1->1->2->3->4->4->5->6
# 示例 2：

# 输入：lists = []
# 输出：[]
# 示例 3：

# 输入：lists = [[]]
# 输出：[]
#

# 提示：

# k == lists.length
# 0 <= k <= 10^4
# 0 <= lists[i].length <= 500
# -10^4 <= lists[i][j] <= 10^4
# lists[i] 按 升序 排列
# lists[i].length 的总和不超过 10^4

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
    def mergeKLists(self, lists: List[Optional[ListNode]]) -> Optional[ListNode]:
        amount = len(lists)
        interval = 1

        while interval < amount:
            for i in range(0, amount - interval, interval * 2):
                lists[i] = self.merge_two_list(lists[i], lists[i + interval])
            interval *= 2
        return lists[0] if amount > 0 else None

    def merge_two_list(
        self, list1: Optional[ListNode], list2: Optional[ListNode]
    ) -> Optional[ListNode]:
        if not list1:
            return list2
        if not list2:
            return list1
        head1, head2 = list1, list2
        merged = new = None
        while head1 or head2:
            if head1 and head2:
                if head1.val < head2.val:
                    if not merged:
                        merged = new = ListNode(head1.val)
                    else:
                        merged.next = ListNode(head1.val)
                        merged = merged.next
                    head1 = head1.next
                else:
                    if not merged:
                        merged = new = ListNode(head2.val)
                    else:
                        merged.next = ListNode(head2.val)
                        merged = merged.next
                    head2 = head2.next
            elif head1 and merged:
                merged.next = head1
                while head1:
                    head1 = head1.next
            elif head2 and merged:
                merged.next = head2
                while head2:
                    head2 = head2.next
        return new


lists = [[7], [49]]
linked = []
for one in lists:
    c = None
    for i in one[::-1]:
        n = ListNode(i)
        n.next = c
        c = n
    linked.append(c)
print(linked)
print(Solution().mergeKLists(linked))
