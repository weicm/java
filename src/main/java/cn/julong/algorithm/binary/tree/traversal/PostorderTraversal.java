package cn.julong.algorithm.binary.tree.traversal;

import cn.julong.algorithm.common.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 后遍历：左右中
 */
public class PostorderTraversal implements Traversal {
    @Override
    public List<Integer> traversalByRecursive(TreeNode root) {
        List<Integer> items = new ArrayList<>();
        localRecursive(items, root);
        return items;
    }

    public void localRecursive(List<Integer> items, TreeNode node) {
        if (node == null) {
            return;
        }
        localRecursive(items, node.left);
        localRecursive(items, node.right);
        items.add(node.val);
    }


    @Override
    public List<Integer> traversalByUnRecursive(TreeNode root) {
        return unRecursiveBy2PointerV0(root);
    }

    /**
     * 前后指针法（根节点先入栈版本）
     * 1. 探测栈顶
     * 2. 判断能出栈，则访问并出栈
     * 3. 判断不能出栈，则将右左节点依次入闸
     * @param root
     * @return
     */
    public static List<Integer> unRecursiveBy2PointerV0(TreeNode root) {
        List<Integer> items = new ArrayList<>();

        LinkedList<TreeNode> stack = new LinkedList<>();
        // 根节点入栈
        stack.push(root);

        TreeNode cur, pre = null;

        while (!stack.isEmpty()) {
            // 探测栈顶节点
            cur = stack.peek();
            // 栈顶节点出栈条件：
            // 1. 栈顶节点的左右节点都为空，说明是叶子节点，可以访问
            // 2. 前一个访问节点不为空，并且当前节点的左节点等于前一个访问过的节点，则说明当前节点为右节点且前一个已访问节点为左节点，可以访问当前节点
            // 3. 前一个访问节点不为空，并且当前节点的右节点等于前一个访问过的节点，则说明当前节点为根节点且前一个已访问节点为右节点，可以访问当前节点
            if ((cur.left == null && cur.right == null) || (pre != null && (pre == cur.left || pre == cur.right))) {
                items.add(cur.val);
                stack.pop();
                pre = cur;
            } else {// 栈顶节点不出站，就需要把右节点和左节点依次入栈
                if (cur.right != null)
                    stack.push(cur.right);
                if (cur.left != null)
                    stack.push(cur.left);
            }
        }

        return items;
    }
    /**
     * 前后指针法（原版）
     * 链接：https://leetcode.cn/problems/binary-tree-postorder-traversal/solution/dong-hua-mo-ni-er-cha-shu-de-hou-xu-bian-wyg2/
     * @param root
     * @return
     */
    public List<Integer> unRecursiveBy2PointerV1(TreeNode root) {
        List<Integer> items = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root, pre = null;
        while (cur != null || !stack.isEmpty()) {
            // 左节点入栈
            while (cur != null) {
                stack.addFirst(cur);
                cur = cur.left;
            }
            // 左节点为空，弹出栈顶节点
            cur = stack.removeFirst();
            // 当前出站节点的右节点为空或右节点等于上一个访问过的节点，则说明右节点已经访问过，可以访问当前节点了
            if (cur.right == null || cur.right == pre) {
                // 访问当前节点
                items.add(cur.val);
                // 记录上一个访问节点
                pre = cur;
                // 使当前指针失效以继续访问栈顶节点
                cur = null;
            } else {
                // 右节点未访问过，则把当前节点重新入栈，当前节点指向右节点以让右节点入栈
                stack.addFirst(cur);
                cur = cur.right;
            }
        }
        return items;
    }

    /**
     * 前后指针法（改进版：避免多余的出栈操作）
     * 链接：https://leetcode.cn/problems/binary-tree-postorder-traversal/solution/dong-hua-mo-ni-er-cha-shu-de-hou-xu-bian-wyg2/
     * @param root
     * @return
     */
    public List<Integer> unRecursiveBy2PointerV2(TreeNode root) {
        List<Integer> items = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root, pre = null;
        while (cur != null || !stack.isEmpty()) {
            // 左节点入栈
            while (cur != null) {
                stack.addFirst(cur);
                cur = cur.left;
            }
            // 左节点为空，弹出栈顶节点
            cur = stack.peekFirst();
            // 当前出站节点的右节点为空或右节点等于上一个访问过的节点，则说明右节点已经访问过，可以访问当前节点了
            if (cur.right == null || cur.right == pre) {
                cur = stack.removeFirst();
                // 访问当前节点
                items.add(cur.val);
                // 记录上一个访问节点
                pre = cur;
                // 使当前指针失效以继续访问栈顶节点
                cur = null;
            } else {
                // 右节点未访问过，则把当前节点重新入栈，当前节点指向右节点以让右节点入栈
                cur = cur.right;
            }
        }
        return items;
    }
    /**
     * 借助变形前序遍历（指针版本）
     * 原理：
     * 前序遍历：    根 -> 左 -> 右
     * 变形前序遍历： 根 -> 右 -> 左
     * 后序遍历：    左 -> 右 -> 根  === 变形前序遍历的结果翻转
     * @param root
     * @return
     */
    public List<Integer> unRecursiveByPreReversalV1(TreeNode root) {
        List<Integer> items = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode p = root;
        while (p != null || !stack.isEmpty()) {
            while (p != null) {
                items.add(p.val);
                stack.addFirst(p);
                // 变形：先访问右节点
                p = p.right;
            }
            if (!stack.isEmpty()) {
                TreeNode node = stack.removeFirst();
                // 变形：后访问你左节点
                p = node.left;
            }
        }
        Collections.reverse(items);
        return items;
    }

    /**
     * 借助变形前序遍历（栈顶版本）
     * 原理：
     * 前序遍历：    根 -> 左 -> 右
     * 变形前序遍历： 根 -> 右 -> 左
     * 后序遍历：    左 -> 右 -> 根  === 变形前序遍历的结果翻转
     * @param root
     * @return
     */
    public List<Integer> unRecursiveByPreReversalV2(TreeNode root) {
        List<Integer> items = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.addFirst(root);;
        while (!stack.isEmpty()) {
            TreeNode node = stack.removeFirst();
            items.add(node.val);

            if (node.left != null) {
                stack.addFirst(node.left);
            }
            if (node.right != null) {
                stack.addFirst(node.right);
            }
        }
        Collections.reverse(items);
        return items;
    }

    /**
     * TODO
     * 双栈法（增加状态栈）
     * 链接：https://leetcode.cn/problems/binary-tree-postorder-traversal/solution/chao-jian-dan-de-fei-di-gui-er-cha-shu-de-hou-xu-b/
     * @param root
     * @return
     */
    public List<Integer> unRecursiveBy2Stack(TreeNode root) {
        return null;
    }
}
