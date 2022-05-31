package cn.julong.algorithm.binary.tree.traversal;

import java.util.List;

public interface Traversal {
    /**
     * 递归
     * @param root
     * @return
     */
    List<Integer> traversalByRecursive(TreeNode root);

    /**
     * 非递归
     * @param root
     * @return
     */
    List<Integer> traversalByUnRecursive(TreeNode root);
}
