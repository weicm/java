package cn.julong.algorithm.leetcode;

import cn.julong.algorithm.common.TreeNode;
import cn.julong.algorithm.common.TreeUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LC102 {
    public static void main(String[] args) {
        TreeNode root = TreeUtil.build(new Integer[]{3, 9, 20, null, null, 15, 7});
        TreeUtil.print(root);
        List<List<Integer>> r = levelOrder(root);
        System.out.println(r);
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        ArrayList<List<Integer>> r = new ArrayList<>();
        if (null == root) {
            return r;
        }
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);

        // 定义行尾指针
        TreeNode p = root;
        // 定义行号
        int level = 1;

        while (!list.isEmpty()) {
            // 1访问头结点，并将其子节点放到队列尾部
            TreeNode node = list.getFirst();
            if (node.left != null) {
                list.addLast(node.left);
            }
            if (node.right != null) {
                list.addLast(node.right);
            }

            // 2结果集大小小于行号，则需要初始化层列表
            if (r.size() < level) {
                r.add(new ArrayList<>());
            }
            // 3获取当前行子结果集
            List<Integer> line = r.get(level - 1);

            // 4如果当前节点是行结尾，则需要把行尾指针移动到队列最后（也就是下一样行尾），并且自增行号
            if (p == node) {
                p = list.getLast();
                level++;
            }

            // 5删除并访问行首值，并加入子结果集（注意改步放在最后，避免对第4步判断造成错误影响）
            line.add(list.removeFirst().val);
        }

        return r;
    }
}
