package cn.julong.algorithm.common;

public class TreeUtil {
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
}
