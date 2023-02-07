package cn.julong.algorithm.binary.tree.traversal;

import cn.julong.algorithm.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 中序遍历：左中右
 */
public class InorderTraversal implements Traversal {

    @Override
    public List<Integer> traversalByRecursive(TreeNode root) {
        List<Integer> items = new ArrayList<>();
        localTraversal(items, root);
        return items;
    }

    public void localTraversal(List<Integer> items, TreeNode node) {
        if (node == null) {
            return;
        }
        localTraversal(items, node.left);
        items.add(node.val);
        localTraversal(items, node.right);
    }

    /**
     * 非递归
     * @param root
     * @return
     */
    @Override
    public List<Integer> traversalByUnRecursive(TreeNode root) {
        List<Integer> items = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();

        TreeNode cur = root;
        while(cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode node = stack.pop();
            items.add(node.val);
            cur = node.right;
        }
        return items;
    }
}
