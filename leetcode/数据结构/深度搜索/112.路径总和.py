# 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。

# 叶子节点 是指没有子节点的节点。


# 示例 1：


# 输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
# 输出：true
# 解释：等于目标和的根节点到叶节点路径如上图所示。
# 示例 2：


# 输入：root = [1,2,3], targetSum = 5
# 输出：false
# 解释：树中存在两条根节点到叶子节点的路径：
# (1 --> 2): 和为 3
# (1 --> 3): 和为 4
# 不存在 sum = 5 的根节点到叶子节点的路径。
# 示例 3：

# 输入：root = [], targetSum = 0
# 输出：false
# 解释：由于树是空的，所以不存在根节点到叶子节点的路径。


# 提示：

# 树中节点的数目在范围 [0, 5000] 内
# -1000 <= Node.val <= 1000
# -1000 <= targetSum <= 1000
from typing import Optional, List


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def __init__(self) -> None:
        self.result = set()

    def hasPathSum(self, root: Optional[TreeNode], targetSum: int) -> bool:
        if root is None:
            return False

        self.dfs(root, 0)
        return targetSum in self.result

    def dfs(self, node: Optional[TreeNode], sum_n: int = 0) -> None:
        if node is None:
            return
        sum_n += node.val
        if node.left is None and node.right is None:
            self.result.add(sum_n)
            return
        self.dfs(node.left, sum_n)
        self.dfs(node.right, sum_n)


class Solution2(object):
    def hasPathSum(self, root: Optional[TreeNode], targetSum: int) -> bool:
        if not root:
            return False
        if not root.left and not root.right:
            return targetSum == root.val
        residue = targetSum - root.val
        return self.hasPathSum(root.left, residue) or self.hasPathSum(root.right, residue)


def make_tree(root: List) -> Optional[TreeNode]:
    if not root:
        return None
    tree = [None] + [None if e is None else TreeNode(e) for e in root]
    for i in range(1, len(tree) // 2 + 1):
        this = tree[i]
        if this is not None:
            if 2 * i < len(tree):
                this.left = tree[2 * i]
            if 2 * i + 1 < len(tree):
                this.right = tree[2 * i + 1]

    return tree[1]


null = None
root = [5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1]
print(Solution().hasPathSum(make_tree(root), 5))
