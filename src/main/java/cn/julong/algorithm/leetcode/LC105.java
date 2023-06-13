package cn.julong.algorithm.leetcode;

import cn.julong.algorithm.common.TreeNode;
import cn.julong.algorithm.common.TreeUtil;

/**
 * 从前序与中序遍历序列构造二叉树
 *
 * @author: weichangming@baidu.com
 * @date: 2023/5/14
 */
public class LC105 {
    public static void main(String[] args) {
        int[] preorder = new int[]{1, 2, 3};
        int[] inorder = new int[]{3, 2, 1};

        LC105 dirver = new LC105();
        TreeNode tree = dirver.buildTree(preorder, inorder);

        TreeUtil.print(tree);
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        return dfs(preorder, 0, n - 1, inorder, 0, n - 1);
    }

    TreeNode dfs(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if(preStart > preEnd || inStart > inEnd) {
            return null;
        }
        TreeNode node = new TreeNode(preorder[preStart]);

        int inSplitIndex = inSplit(node.val, inorder, inStart, inEnd);

        int leftLen = inSplitIndex - inStart;

        node.left = dfs(preorder, preStart + 1, preStart + leftLen, inorder, inStart, inSplitIndex - 1);
        node.right = dfs(preorder, preStart + leftLen + 1, preEnd, inorder, inSplitIndex + 1, inEnd);

        return node;
    }

    int inSplit(int rootVal, int[] inorder, int start, int end) {
        for(int i = start; i <= end; i++) {
            if(inorder[i] == rootVal) {
                return i;
            }
        }
        return -1;
    }
}
