package cn.julong.algorithm.leetcode;

import cn.julong.algorithm.common.TreeNode;
import cn.julong.algorithm.common.TreeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LC94 {
    public static void main(String[] args) {
        // TreeNode root = TreeUtil.build(new Integer[]{1, 2, 3, 4, 5, 6, null});
        // TreeNode root = TreeUtil.buildTree(new Integer[]{1, null, 2, null, null, 3, null});
        // TreeNode root = TreeUtil.buildAvl(new Integer[]{3, 2, null, null, 4, null, null, null, null, 1, null, null,null, null, null});
        TreeNode root = TreeUtil.buildCom(new Integer[]{3, 2, null, null, 4, 1});
        TreeUtil.print(root);
        List<Integer> r = inorderTraversal(root);
        System.out.println(r);
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> r = new ArrayList<>();
        if (null == root) {
            return r;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);

        TreeNode last = null;
        while (!stack.isEmpty()) {
            // 1探测top节点
            TreeNode node = stack.peek();
            // 2判断是出栈还是入栈
            // 2.1左右节点均为空，出栈
            // 2.2左节点为空出，栈
            // 2.3上次访问节点为左节点，出栈
            if ((null == node.left && null == node.right)
                    || null == node.left
                    || last == node.left) {
                last = stack.pop();
                r.add(node.val);
                if (null != node.right) {
                    stack.push(node.right);
                }
            } else {
                // 3如果左节点不为空，入栈
                if (null != node.left) {
                    stack.push(node.left);
                    // 3.1走过后标记为空（特别注意该位置，标识为已经访问过左节点，避免重复访问）
                    node.left = null;
                }
            }
        }
        return r;
    }
}
