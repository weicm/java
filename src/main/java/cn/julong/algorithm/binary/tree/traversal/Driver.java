package cn.julong.algorithm.binary.tree.traversal;


import cn.julong.algorithm.common.TreeNode;

public class Driver {

    public static void main(String[] args) {
        TreeNode root = buildRoot();
        Traversal t = new PreorderTraversal();
        // Traversal t = new InorderTraversal();
        // Traversal t = new PostorderTraversal();
        // Traversal t = new LevelTraversal();
        System.out.println(t.traversalByRecursive(root));
        System.out.println(t.traversalByUnRecursive(root));
    }

    /**
     * 前序遍历：1  2  4  5  7  8  3  6
     * 中序遍历：4  2  7  5  8  1  3  6
     * 后序遍历：4  7  8  5  2  6  3  1
     * 层次遍历：1  2  3  4  5  6  7  8
     */
    public static TreeNode buildRoot() {
        return new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4),
                        new TreeNode(5,
                                new TreeNode(7),
                                new TreeNode(8))),
                new TreeNode(3,
                        null,
                        new TreeNode(6))
        );
    }

}
