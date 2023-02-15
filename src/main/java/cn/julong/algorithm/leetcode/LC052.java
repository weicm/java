package cn.julong.algorithm.leetcode;

import cn.julong.algorithm.common.TreeNode;
import cn.julong.algorithm.common.TreeUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class LC052 {
    public static void main(String[] args) {
        TreeNode root = TreeUtil.buildCom(new Integer[]{5, 3, 6, 2, 4, null, 8, 1, null, null, null, 7, 9});
        TreeUtil.print(root);
        TreeNode resultRoot = increasingBST(root);
        TreeUtil.print(resultRoot, 1);
    }

    public static TreeNode increasingBST(TreeNode root) {
        // return dfsAndLoop(root);
        return recursive(root);
    }

    public static TreeNode dfsAndLoop(TreeNode root) {
        if (null == root) {
            return null;
        }

        TreeMap<Integer, TreeNode> v2n = new TreeMap<Integer, TreeNode>();
        scanDfs(v2n, root);

        List<TreeNode> ns = new ArrayList<>(v2n.values());
        for (int i = 0; i < ns.size(); i++) {
            TreeNode n = ns.get(i);
            n.left = null;
            if (i == ns.size() - 1) {
                n.right = null;
            } else {
                n.right = ns.get(i + 1);
            }
        }

        return ns.get(0);
    }

    static void scanDfs(TreeMap<Integer, TreeNode> v2n, TreeNode n) {
        if (n == null) {
            return;
        }

        scanDfs(v2n, n.left);
        v2n.put(n.val, n);
        scanDfs(v2n, n.right);
    }

    static TreeNode recursive(TreeNode root) {
        if (null == root) {
            return null;
        }

        LinkedList<TreeNode> r = new LinkedList<>();
        scanAndPutDfs(r, root);
        return r.get(0);
    }

    static void scanAndPutDfs(LinkedList<TreeNode> r, TreeNode n) {
        if (n == null) {
            return;
        }

        scanAndPutDfs(r, n.left);
        Integer i = findDfs(r, 0, r.size() - 1, n);
        TreeNode nn = new TreeNode(n.val);
        if (i == -1) {
            nn.right = r.isEmpty() ? null : r.getFirst();
            r.addFirst(nn);
        } else if (i == r.size()) {
            r.getLast().right = nn;
            r.addLast(nn);
        } else {
            TreeNode fn = r.get(i);
            nn.right = fn.right;
            fn.right = nn;
            r.add(i, nn);
        }
        scanAndPutDfs(r, n.right);
    }

    static Integer findDfs(List<TreeNode> r, Integer si, Integer ei, TreeNode n) {
        if (r.size() == 0) {
            return -1;
        }
        if (si > ei) {
            return null;
        }
        Integer mi = (si + ei) / 2;

        boolean atRight = r.get(mi).val <= n.val;

        Integer i = atRight ? findDfs(r, mi + 1, ei, n) : findDfs(r, si, mi - 1, n);

        return i == null ? atRight ? ei + 1 : si - 1 : i;
    }
}
