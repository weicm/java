package cn.julong.algorithm.leetcode;

import cn.julong.algorithm.common.TreeNode;
import cn.julong.algorithm.common.TreeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LC145 {
    public static void main(String[] args) {
        TreeNode root = TreeUtil.buildCom(new Integer[]{1, null, 2, 3});
                TreeUtil.print(root);
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
            // 1探测栈顶元素
            TreeNode node = stack.peek();

            // 2判断是否出栈还是把子节点入栈
            // 2.1左右均为空
            if ((node.left == null && node.right == null)
                    // 2.2上次访问节点为左节点，且右节点为空
                    || (last == node.left && node.right == null)
                    // 2.3上次访问节点是右节点（注意判断非空，避免 last == node.right == null）
                    || (last != null && last == node.right)) {
                last = stack.pop();
                r.add(node.val);
            } else {
                // 3确保入栈顺序先右后座，才能保证出栈顺序为先左后右
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

