package cn.julong.algorithm.leetcode;

import cn.julong.algorithm.common.TreeNode;
import cn.julong.algorithm.common.TreeUtil;

public class LC110 {
    public static void main(String[] args) {
        TreeNode root = TreeUtil.buildCom(new Integer[]{3, 9, 20, null, null, 15, 7});
        // TreeNode root = TreeUtil.buildCom(new Integer[]{1, 2, 2, 3, 3, null, null, 4, 4});
        TreeUtil.print(root);
        System.out.println(isBalanced(root));
    }

    public static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        int h = dfs(root, 1);
        System.out.println(h);
        return h > 0;
    }

    /**
     * 递归计算数高
     * @param n 当前子树根节点
     * @param h 当前子树根节点高度
     * @return 如果左右子树不平衡，则返回0，否则返回当前子树真实高度
     */
    static int dfs(TreeNode n, int h) {
        if (n.left == null && n.right == null) {
            return h;
        }

        int hl = n.left == null ? h : dfs(n.left, h + 1);
        int hr = n.right == null ? h : dfs(n.right, h + 1);

        if (hl == 0 || hr == 0) {
            return 0;
        }
        return Math.abs(hl - hr) <= 1 ? Math.max(hl, hr) : 0;
    }
}
