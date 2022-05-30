package cn.julong.algorithm.leetcode;

/**
 * 7. 整数反转
 */
public class LC101SymmetricTree {
    public static void main(String[] args) {

        // TreeNode root = buildTree(new Integer[] {1,2,2,3,4,4,3});
        TreeNode root = buildTree(new Integer[] {1,2,2,null,3,null,3});
        System.out.println(isSymmetric(root));
    }

    /**
     * 数组构建二叉树
     *
     * @param arr
     * @return 根节点
     */
    public static TreeNode buildTree(Integer[] arr) {
        TreeNode[] treeNodes = new TreeNode[arr.length];
        int endIndex = arr.length / 2 - 1;
        for (int i = endIndex; i >= 0; i--) {
            int li = 2 * i + 1, ri = 2 * i + 2;
            TreeNode l = treeNodes[li] == null ? new TreeNode(arr[li]) : treeNodes[li];
            TreeNode r = treeNodes[ri] == null ? new TreeNode(arr[ri]) : treeNodes[ri];
            treeNodes[i] = new TreeNode(arr[i], l, r);
        }
        return treeNodes[0];
    }
    public static class TreeNode {
        Integer val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(Integer val) { this.val = val; }
        TreeNode(Integer val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static boolean isSymmetric(TreeNode root) {
        // 根节点为空，返回true
        if(root == null) {
            return true;
        }
        // 判断左右子树对称
        return equalSubTree(root.left, root.right);
    }

    /**
     * 递归判断子树对称
     *
     * @param l
     * @param r
     * @return true: 相等，false: 不等
     */
    private static boolean equalSubTree(TreeNode l, TreeNode r) {
        // 如果子树根节点都为空，则对称
        if(l == null && r == null) {
            return true;
        }
        // 如果子树根节点一个为空一个不为空，则不对称
        if((l == null && r != null ) || l != null && r == null ) {
            return false;
        }
        // 两个子树根都不为空，判断对称条件：两个子树根节点值相等 && 左节点左子树等于有节点右子树 && 左节点右子树等于右节点左子树
        return l.val == r.val && equalSubTree(l.left, r.right) && equalSubTree(l.right, r.left);
    }
}
