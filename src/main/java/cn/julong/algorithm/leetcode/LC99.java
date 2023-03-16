package cn.julong.algorithm.leetcode;

import cn.julong.algorithm.common.TreeNode;
import cn.julong.algorithm.common.TreeUtil;

import java.util.ArrayList;
import java.util.List;

public class LC99 {

    public static void main(String[] args) {
        TreeNode root = TreeUtil.buildCom(new Integer[]{1, 3, null, null, 2});
        TreeUtil.print(root);
        recoverTree(root);
        TreeUtil.print(root);
    }

    public static void recoverTree(TreeNode root) {
        // inorderScan(root);
        morris(root);
    }

    /**
     * 中序遍历法
     * @param root
     */
    public static void inorder(TreeNode root) {
        List<TreeNode> ns = new ArrayList<>();
        ArrayList<Integer> indexes = new ArrayList<>(2);
        reccursive(root, ns, indexes);
        int len = indexes.size();
        int li = indexes.get(0), ri = len == 1 ? li + 1 : indexes.get(1);
        swap(ns.get(li), ns.get(ri));
    }

    public static void reccursive(TreeNode n, List<TreeNode> ns, List<Integer> indexes) {
        if (n == null) {
            return;
        }

        reccursive(n.left, ns, indexes);
        int size = ns.size();
        if (size > 0) {
            int li = ns.size() - 1;
            if (ns.get(li).val > n.val) {
                indexes.add(indexes.size() == 0 ? li : li + 1);
            }
        }
        ns.add(n);
        reccursive(n.right, ns, indexes);
    }

    /**
     * 莫里斯遍历法
     * @param root
     */
    public static void morris(TreeNode root) {
        if (root == null) {
            return;
        }

        // 当前节点
        TreeNode cur = root;
        // 上次访问的节点
        TreeNode last = null;
        // 前驱节点（中序遍历二叉排序树，结果是有序列表，pre表示在有序列表中当前节点的前驱节点）
        TreeNode pre = null;
        // 左侧交换节点
        TreeNode x = null;
        // 右侧交换节点
        TreeNode y = null;

        while (cur != null) {
            // 寻找，并连接或断开前驱节点
            pre = chainOrBreakPre(cur);

            if (pre != null) {// pre != null：说明左子树还没访问，需要继续访问左子树
                cur = cur.left;
            } else { // pre == null：说明左子树已经访问完了，可以访问当前节点，然后访问右子树了
                if (last != null && last.val > cur.val) {
                    y = cur;
                    x = x == null ? last : x;
                }
                last = cur;
                cur = cur.right;
            }
        }

        swap(x, y);
    }

    /**
     * 寻找前驱节点
     * @param cur
     * @return 找到前驱节点则返回pre，如果前驱节点不存在或者已经连接，则返回null
     */
    private static TreeNode chainOrBreakPre(TreeNode cur) {
        TreeNode pre = cur.left;
        // 不存在前驱节点
        if (pre == null) {
            return null;
        }
        // 寻找前驱节点
        while (pre.right != null && pre.right != cur) {
            pre = pre.right;
        }

        // 前驱节点未连接当前节点，则连接
        if (pre.right == null) {
            pre.right = cur;
            return pre;
        }

        // 前驱节点已连接当前节点，则断开
        pre.right = null;
        return null;
    }

    private static void swap(TreeNode ln, TreeNode rn) {
        ln.val = ln.val + rn.val;
        rn.val = ln.val - rn.val;
        ln.val = ln.val - rn.val;
    }
}
