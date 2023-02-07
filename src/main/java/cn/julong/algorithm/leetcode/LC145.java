package cn.julong.algorithm.leetcode;

import cn.julong.algorithm.common.TreeNode;
import cn.julong.algorithm.common.TreeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LC145 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1,
                null,
                new TreeNode(2,
                        new TreeNode(3),
                        null)
        );
        List<Integer> r = postorderTraversal(root);
        System.out.println(r);
    }
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> r = new ArrayList<>();
        if (null == root) {
            return r;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);

        TreeNode last = null;
        while(!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if ((node.left == null && node.right == null)
                    || (last == node.left && node.right == null)
                    || (last != null && last == node.right)) {
                r.add(node.val);
                last = stack.pop();
            } else {
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
            }
        }
        return r;
    }
}

