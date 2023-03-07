package cn.julong.algorithm.leetcode;

import cn.julong.algorithm.common.TreeNode;
import cn.julong.algorithm.common.TreeUtil;

public class LC98 {
    public static void main(String[] args) {
        // TreeNode root = TreeUtil.buildCom(new Integer[]{5, 4, 6, null, null, 3, 7});
        TreeNode root = TreeUtil.buildCom(new Integer[]{20, 10, 30, 5, 15, 25, 35, 4, 6, 14, 16, 24, 26, 34, 36});
        // TreeNode root = TreeUtil.buildCom(new Integer[]{5,1,4,null,null,3,6});
        // TreeNode root = TreeUtil.buildCom(new Integer[]{0});
        // TreeNode root = TreeUtil.buildCom(new Integer[]{5, 1, 4, null, null, 3, 6});
        // TreeNode root = TreeUtil.buildCom(new Integer[]{2147483647});
        TreeUtil.print(root);
        System.out.println(isValidBST(root));
    }

    public static boolean isValidBST(TreeNode root) {
        return recursive(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean recursive(TreeNode node, long min, long max) {
        // 递归出口
        if (node == null) {
            return true;
        }

        // 当前子树根节点满足范围要求 && 当前子树满足左小右大要求 && 递归左节点 && 递归右节点
        return min < node.val && node.val < max
                && (node.left == null || node.left.val < node.val)
                && (node.right == null || node.val < node.right.val)
                && recursive(node.left, min, node.val)
                && recursive(node.right, node.val, max);
    }

}
