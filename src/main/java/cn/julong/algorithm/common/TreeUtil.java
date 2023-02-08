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
            TreeNode l = treeNodes[li] != null ? treeNodes[li] : arr[li] != null ? new TreeNode(arr[li]) : null;
            TreeNode r = treeNodes[ri] != null ? treeNodes[ri] : arr[ri] != null ? new TreeNode(arr[ri]) : null;
            treeNodes[i] = null != arr[i] ? new TreeNode(arr[i], l, r) : null;
        }
        return treeNodes[0];
    }
}
