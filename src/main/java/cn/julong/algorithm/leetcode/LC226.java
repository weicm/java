package cn.julong.algorithm.leetcode;

import cn.julong.algorithm.common.TreeNode;
import cn.julong.algorithm.common.TreeUtil;

public class LC226 {
    public static void main(String[] args) {
        // TreeNode root = TreeUtil.buildCom(new Integer[]{4, 2, 7, 1, 3, 6, 9});
        TreeNode root = TreeUtil.buildCom(new Integer[]{1, 2});
        TreeUtil.print(root);
        TreeUtil.print(invertTree(root));
    }

    public static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        return dfs(root);
    }

    static TreeNode dfs(TreeNode n) {
        if (n.left == null && n.right == null) {
            return n;
        }

        TreeNode tmp = n.left;
        n.left = n.right;
        n.right = tmp;

        if (n.left != null) {
            dfs(n.left);
        }
        if (n.right != null) {
            dfs(n.right);
        }
        return n;
    }
}
