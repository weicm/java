package cn.julong.algorithm.binary.tree.traversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 前序遍历：中左右
 */
public class PreorderTraversal implements Traversal{
    @Override
    public List<Integer> traversalByRecursive(TreeNode root) {
        List<Integer> items = new ArrayList<>();
        localRecursive(items, root);
        return items;
    }

    public void localRecursive(List<Integer> items, TreeNode node) {
        if (node == null) {
            return;
        }
        items.add(node.val);
        localRecursive(items, node.left);
        localRecursive(items, node.right);
    }

    @Override
    public List<Integer> traversalByUnRecursive(TreeNode root) {
        return unRecursiveByPushRoot(root);
    }

    public List<Integer> unRecursiveByPointer(TreeNode root) {
        List<Integer> items = new ArrayList<>();
        if (null == root) {
            return items;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode p = root;
        while (null != p || !stack.isEmpty()) {
            while (p != null) {
                items.add(p.val);
                stack.addFirst(p);
                p = p.left;
            }

            if (!stack.isEmpty()) {
                TreeNode node = stack.removeFirst();
                p = node.right;
            }
        }
        return items;
    }

    public List<Integer> unRecursiveByPushRoot(TreeNode root) {
        List<Integer> items = new ArrayList<>();
        if (null == root) {
            return items;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            items.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return items;
    }

}
