package cn.julong.algorithm.binary.tree.traversal;

import cn.julong.algorithm.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 层级遍历
 */
public class LevelTraversal implements Traversal{

    @Override
    public List<Integer> traversalByRecursive(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (null == root) {
            return new ArrayList<>();
        }
        localRecursive(result, root, 0);
        return result.stream().flatMap(List::stream).collect(Collectors.toList());
    }

    private void localRecursive(List<List<Integer>> result, TreeNode node, int level) {
        if (null == node) {
            return;
        }
        List<Integer> levelResult = result.size() > level ? result.get(level) : null;
        if (null == levelResult) {
            levelResult = new ArrayList<>();
            result.add(levelResult);
        }
        levelResult.add(node.val);
        localRecursive(result, node.left, level + 1);
        localRecursive(result, node.right, level + 1);
    }

    @Override
    public List<Integer> traversalByUnRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        List<TreeNode> items = new ArrayList<>();
        items.add(root);
        while (!items.isEmpty()) {
            int size = items.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = items.remove(0);
                if (node.left != null) {
                    items.add(node.left);
                }
                if (node.right != null) {
                    items.add(node.right);
                }
                result.add(node.val);
            }
        }
        return result;
    }
}
