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
        int[] preorder = new int[]{3, 9, 20, 15, 7};
        int[] inorder = new int[]{9, 3, 15, 20, 7};

        TreeNode tree = buildTree(preorder, inorder);

        TreeUtil.print(tree);
    }
    static TreeNode buildTree(int[] preorder, int[] inorder) {
        return dfs(preorder, inorder, 0, new TreeNode(preorder[0]));
    }

    static TreeNode dfs(int preorder[], int[] inorder, int i, TreeNode n) {

        int len = preorder.length;

        if(i >= len - 1 || (preorder[i + 1] != inorder[i] && preorder[i + 1] != inorder[i + 1])) {
            return n;
        }

        n.left = preorder[i + 1] == inorder[i] ? dfs(preorder, inorder, i + 1, new TreeNode(preorder[i + 1])) : null;

        if(preorder[i + 1] == inorder[i]) {
            n.right = i < len - 2 ? dfs(preorder, inorder, i + 2, new TreeNode(preorder[i + 2])) : null;
        } else {
            n.right = preorder[i] == inorder[i] ? dfs(preorder, inorder, i + 1, new TreeNode(preorder[i + 1])) : null;
        }

        return n;
    }
}
