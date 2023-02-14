package cn.julong.algorithm.leetcode;

import cn.julong.algorithm.common.TreeNode;
import cn.julong.algorithm.common.TreeUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC655 {
    public static void main(String[] args) {
        TreeNode root = TreeUtil.buildCom(new Integer[]{1, 2, 3, null, 4});
        TreeUtil.print(root);

        List<List<String>> result = printTree(root);
        result.forEach(System.out::println);
    }

    public static List<List<String>> printTree(TreeNode root) {
        // 计算高度
        int h = hDfs(root, 0);
        // 计算宽度
        int w = Double.valueOf(Math.pow(2, h + 1)).intValue() - 1;
        // 行数
        int r = h + 1;

        // 结果矩阵
        List<List<String>> matrix = new ArrayList<>(r);

        // 临时矩阵（方便根据索引设置值）
        List<String[]> tmpMatrix = new ArrayList<>(r);

        // 初始化临时矩阵
        for (int i = 0; i < h + 1; i++) {
            String[] tmpLine = new String[w];

            for (int j = 0; j < w; j++) {
                tmpLine[j] = "";
            }

            tmpMatrix.add(tmpLine);
        }

        // 讲解点设置到矩阵对应坐标下，root节点坐标[0, (w - 1) / 2]
        setDfs(tmpMatrix, root, h, 0, (w - 1) / 2);

        // 复诊临时矩阵到正式矩阵中
        for (int i = 0; i < r; i++) {
            matrix.add(Arrays.asList(tmpMatrix.get(i)));
        }
        return matrix;
    }

    /**
     * 对于放置在矩阵中的每个节点，设对应位置为 res[r][c] ，将其左子节点放置在 res[r+1][c-2height-r-1] ，右子节点放置在 res[r+1][c+2height-r-1] 。
     *
     * @param matrix
     * @param n
     * @param r
     * @param c
     */
    static void setDfs(List<String[]> matrix, TreeNode n, int h, int r, int c) {
        if (n == null) {
            return;
        }
        String[] line = matrix.get(r);
        line[c] = String.valueOf(n.val);
        int lr = r + 1, lc = c - Double.valueOf(Math.pow(2, h - r - 1)).intValue();
        setDfs(matrix, n.left, h, lr, lc);
        int rr = r + 1, rc = c + Double.valueOf(Math.pow(2, h - r - 1)).intValue();
        setDfs(matrix, n.right, h, rr, rc);

    }

    static int hDfs(TreeNode n, int h) {
        if (n.left == null && n.right == null) {
            return h;
        }

        int hl = n.left == null ? h : hDfs(n.left, h + 1);
        int hr = n.right == null ? h : hDfs(n.right, h + 1);
        return Math.max(hl, hr);
    }
}
