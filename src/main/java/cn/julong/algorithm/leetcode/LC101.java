package cn.julong.algorithm.leetcode;

import cn.julong.algorithm.common.TreeNode;
import cn.julong.algorithm.common.TreeUtil;

import java.util.Stack;

public class LC101 {

    public static void main(String[] args) {

        TreeNode root = TreeUtil.buildCom(new Integer[]{1, 2, 2, 3, 4, 4, 3});
        // TreeNode root = TreeUtil.buildCom(new Integer[]{1, 2, 2, null, 3, null, 3});
        TreeUtil.print(root);
        System.out.println(isSymmetric(root));
    }

    public static boolean isSymmetric(TreeNode root) {
        // return dfs(root);
        return loop(root);
    }

    public static boolean dfs(TreeNode root) {
        if (root == null) {
            return true;
        }
        return recursive(root.left, root.right);
    }

    public static boolean recursive(TreeNode l, TreeNode r) {
        if (l == null && r == null) {
            return true;
        }

        return l != null && r != null && l.val == r.val
                && recursive(l.left, r.right) && recursive(l.right, r.left);
    }

    public static boolean loop(TreeNode root) {
        if (null == root) {
            return true;
        }

        Stack<TreeNode> sl = new Stack<>();
        Stack<TreeNode> sr = new Stack<>();

        sl.push(root);
        sr.push(root);

        while (!sl.isEmpty() || !sr.isEmpty()) {
            if (sl.size() != sr.size()) {
                return false;
            }
            TreeNode nl = sl.pop();
            TreeNode nr = sr.pop();

            if ((nl == null && nr != null) || (nl != null && nr == null) || (nl != null && nr != null && !nl.val.equals(nr.val))) {
                return false;
            }

            if (nl == null && nr == null) {
                continue;
            }
            sl.push(nl.right == null ? null : nl.right);
            sl.push(nl.left == null ? null : nl.left);

            sr.push(nr.left == null ? null : nr.left);
            sr.push(nr.right == null ? null : nr.right);
        }

        return true;
    }
}
