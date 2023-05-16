package cn.julong.algorithm.leetcode;

import cn.julong.algorithm.common.TreeNode;
import cn.julong.algorithm.common.TreeUtil;

/**
 * 二叉树展开为链表
 *
 * @author: weichangming@baidu.com
 * @date: 2023/5/15
 */
public class LC114 {
    public static void main(String[] args) {
        TreeNode root = TreeUtil.buildByCompressArr(new Integer[]{1, 2, 5, 3, 4, null, 6});

        flatten(root);

        TreeUtil.print(root);
    }

    static void flatten(TreeNode root) {
        TreeNode h = new TreeNode();
        dfs(root, h);
    }

    static TreeNode dfs(TreeNode node, TreeNode preNode) {
        if(node == null) {
            return null;
        }

        TreeNode ln = node.left, rn = node.right;

        preNode.left = null;
        preNode.right = node;

        if (ln == null && rn == null) {
            return node;
        }

        TreeNode lastNode = dfs(ln, node);

        if(lastNode != null) {
            lastNode = dfs(rn, lastNode);
        } else {
            lastNode = dfs(rn, node);
        }
        return lastNode;
    }
}
