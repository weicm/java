package cn.julong.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 7. 整数反转
 */
public class LC102BinaryTreeLevelOrderTraversal {
    public static void main(String[] args) {
        // 输出：[[3],[9,20],[15,7]]
        TreeNode root = buildTree(new Integer[]{3, 9, 20, null, null, 15, 7});
        // System.out.println(levelOrderByQueue(root));
        System.out.println(levelOrderByRecursive(root));
    }

    public static TreeNode buildTree(Integer[] arr) {
        TreeNode[] treeNodes = new TreeNode[arr.length];
        int endIndex = arr.length / 2 - 1;
        for (int i = endIndex; i >= 0; i--) {
            int li = 2 * i + 1, ri = 2 * i + 2;
            TreeNode l = treeNodes[li] == null ? arr[li] == null ? null : new TreeNode(arr[li]) : treeNodes[li];
            TreeNode r = treeNodes[ri] == null ? arr[ri] == null ? null : new TreeNode(arr[ri]) : treeNodes[ri];
            treeNodes[i] = new TreeNode(arr[i], l, r);
        }
        return treeNodes[0];
    }

    /**
     * 递归实现
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrderByRecursive(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (null == root) {
            return result;
        }

        recursive(result, root, 0);
        return result;
    }

    private static void recursive(List<List<Integer>> result, TreeNode node, int level) {
        if (null == node) {
            return;
        }
        List<Integer> levelResult = result.size() > level ? result.get(level) : null;
        if (null == levelResult) {
            levelResult = new ArrayList<>();
            result.add(levelResult);
        }
        levelResult.add(node.val);
        recursive(result, node.left, level + 1);
        recursive(result, node.right, level + 1);
    }


    /**
     * 队列实现
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrderByQueue(TreeNode root) {
        List<List<Integer>> result = new ArrayList();
        if (null == root) {
            return result;
        }
        List<TreeNode> nodes = new ArrayList();
        nodes.add(root);

        while (!nodes.isEmpty()) {
            int size = nodes.size();
            List<Integer> levelResult = new ArrayList(size);
            result.add(levelResult);
            for (int i = 0; i < size; i++) {
                TreeNode node = nodes.remove(0);
                if (node.left != null) {
                    nodes.add(node.left);
                }
                if (node.right != null) {
                    nodes.add(node.right);
                }
                levelResult.add(node.val);
            }

        }
        return result;

    }

    public static class TreeNode {
        Integer val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(Integer val) {
            this.val = val;
        }

        TreeNode(Integer val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
