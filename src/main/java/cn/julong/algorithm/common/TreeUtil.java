package cn.julong.algorithm.common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TreeUtil {
    /**
     * 构建Avl树
     * @param arr avl层级遍历顺序节点数组
     * @return
     */
    public static TreeNode buildAvl(Integer[] arr) {
        return build(arr, 0);
    }

    /**
     * 构建Com树（压缩树）
     * @param arr com层级遍历顺序节点数组
     * @return
     */
    public static TreeNode buildCom(Integer[] arr) {
        return build(arr, 1);
    }

    /**
     * 构建Avl树
     * @param arr avl层级遍历顺序节点数组
     * @return
     */
    public static TreeNode build(Integer[] arr) {
        return buildAvl(arr);
    }

    /**
     * 构建二叉树
     * @param arr 层级遍历顺序节点数组
     * @param mode 0：avl，1：com（压缩树）
     * @return
     */
    public static TreeNode build(Integer[] arr, Integer mode) {
        if (mode == 0) {
            return buildByAvlArr(arr);
        }
        if (mode == 1) {
            return buildByCompressArr(arr);
        }
        return null;
    }

    /**
     * 构建Avl树
     * @param arr avl层级遍历顺序节点数组
     * @return
     */
    public static TreeNode buildByAvlArr(Integer[] arr) {
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

    /**
     * 构建Com二叉树
     * @param arr 层级遍历顺序节点压缩数组
     * @return
     */
    public static TreeNode buildByCompressArr(Integer[] arr) {
        HashMap<Integer, TreeNode> val2Tn = new HashMap<>();
        // pp: 父节点指针, cp: 子节点指针
        for (Integer pp = 0, cp = 0; pp <= cp && cp < arr.length - 1;) {
            TreeNode node = val2Tn.get(pp);
            if (node == null) {
                node = new TreeNode(arr[pp]);
                val2Tn.put(pp, node);
            }
            Integer lp = cp + 1, rp = cp + 2;
            TreeNode left = lp >= arr.length || null == arr[lp] ? null : new TreeNode(arr[lp]);
            if (null != left) {
                val2Tn.put(lp, left);
            }
            TreeNode right = rp >= arr.length || null == arr[rp] ? null : new TreeNode(arr[rp]);
            if (null != right) {
                val2Tn.put(rp, right);
            }
            node.left = left;
            node.right = right;
            cp += 2;
            while (arr[++pp] == null && pp <= cp);
        }
        return val2Tn.get(0);
    }

    /**
     * 打印二叉树：背景是空格，数字宽度为3
     * @param root
     */
    public static void print(TreeNode root) {
        print(root, " ", 3);
    }

    /**
     * 打印二叉树：背景是空格，数字宽度为widh
     * @param root
     */
    public static void print(TreeNode root, Integer width) {
        print(root, " ", width);
    }

    /**
     * 打印二叉树：背景是space，数字宽度为3
     * @param root
     */
    public static void print(TreeNode root, String space) {
        print(root, space, 3);
    }

    /**
     * 打印二叉树：背景是space，数字宽度为width
     * @param root
     */
    public static void print(TreeNode root, String space, Integer width) {
        TreeNode tmpNode = fillEmptyNode(root);
        String[] matrix = printDfs(tmpNode, space, "/", "\\", width);
        Arrays.stream(matrix).forEach(System.out::println);
    }

    /**
     * 递归打印二叉树
     * @param node
     * @param space
     * @param leftSlash
     * @param rightSlash
     * @param width
     * @return
     */
    private static String[] printDfs(TreeNode node, String space, String leftSlash, String rightSlash, Integer width) {
        if (null == node.left && null == node.right) {
            return new String[]{cm(space, width,  node.val)};
        }
        String[] leftMatrix = null == node.left ? null : printDfs(node.left, space, leftSlash, rightSlash, width);
        String[] rightMatrix = null == node.right ? null : printDfs(node.right, space, leftSlash, rightSlash, width);

        Integer leftLen = Optional.ofNullable(leftMatrix)
                .map(m -> m[0].length())
                .orElse(0);
        Integer rightLen = Optional.ofNullable(rightMatrix)
                .map(m -> m[0].length())
                .orElse(0);

        Integer leftHigh = Optional.ofNullable(leftMatrix)
                .map(m -> m.length)
                .orElse(0);
        Integer rightHigh = Optional.ofNullable(rightMatrix)
                .map(m -> m.length)
                .orElse(0);
        Integer len = Math.max(leftLen, rightLen);
        String topLineSpace = nc(space, len);
        String topLine = String.format("%s%s%s", topLineSpace, cm(space, width, node.val), topLineSpace);
        leftSlash = null == node.val || null == node.left || null == node.left.val ? space : leftSlash;
        rightSlash = null == node.val || null == node.right || null == node.right.val ? space : rightSlash;
        String secondLineLeft = String.format("%s%s%s", nc(space, (len * 3) / 4), leftSlash, nc(space, len / 4));
        String secondLineRight = String.format("%s%s%s", nc(space, len / 4), rightSlash, nc(space, (len * 3) / 4));
        String secondLine = String.format("%s%s%s", secondLineLeft, nc(space, width), secondLineRight);

        Integer high = Math.max(leftHigh, rightHigh);
        String[] matrix = new String[high + 2];
        matrix[0] = topLine;
        matrix[1] = secondLine;
        for (int i = 0; i < high; i++) {
            String leftLine = leftMatrix == null || i >= leftHigh ? nc(space, len) : leftMatrix[i];
            String rightLine = rightMatrix == null || i >= rightHigh ? nc(space, len) : rightMatrix[i];
            String line = String.format("%s%s%s", leftLine, nc(space, width), rightLine);
            matrix[i + 2] = line;
        }
        return matrix;
    }

    /**
     * 构建重复字符的字符串
     * @param c 字符
     * @param n 重复次数
     * @return
     */
    private static String nc(String c, Integer n) {
        return IntStream.range(0, n).boxed().map(i -> c).collect(Collectors.joining(""));
    }

    /**
     * 构建剧中数字
     * @param space 背景
     * @param width 数字宽度
     * @param num 数字
     * @return
     */
    private static String cm(String space, Integer width, Integer num) {
        int spaceNum = width - (null == num ? 0 : num.toString().length());
        String leftSpace = nc(space,  spaceNum % 2 == 0 ? spaceNum / 2 : (spaceNum + 1) / 2);
        String rightSpace = nc(space, spaceNum % 2 == 0 ? spaceNum / 2 : (spaceNum - 1) / 2);
        return String.format("%s%s%s", leftSpace, num == null ? "" : num, rightSpace);
    }

    /**
     * 将二叉树填充为平衡二叉树，填充节点值为null
     * @param root
     */
    private static TreeNode fillEmptyNode(TreeNode root) {
        return fillDfs(root, depth(root), 1);
    }

    /**
     * 递归填充二叉树
     * @param node
     * @param depth
     * @param level
     */
    private static TreeNode fillDfs(TreeNode node, Integer depth, Integer level) {
        TreeNode tmpNode = node.val == null ? node : new TreeNode(node.val, node.left, node.right);
        if (level >= depth) {
            return tmpNode;
        }
        if (node.left == null) {
            tmpNode.left = new TreeNode();
        }
        if (node.right == null) {
            tmpNode.right = new TreeNode();
        }
        tmpNode.left = fillDfs(tmpNode.left, depth, level + 1);
        tmpNode.right = fillDfs(tmpNode.right, depth, level + 1);
        return tmpNode;
    }

    /**
     * 二叉树深度
     * @param root
     * @return
     */
    public static Integer depth(TreeNode root) {
        return depthDfs(root, 1);
    }

    /**
     * 递归查询二叉树深度
     * @param node
     * @param level
     * @return
     */
    private static Integer depthDfs(TreeNode node, Integer level) {
        if (null == node || node.left == null && node.right == null) {
            return level;
        }

        return Math.max(depthDfs(node.left, level + 1), depthDfs(node.right, level + 1));
    }

    public static void main(String[] args) {
        // String space = "-", leftSlash = "/", rightSlash = "\\";
        // Integer len = 3;
        // String topLineSpace = nc(space, len);
        // String topLine = String.format("%s%s%s", topLineSpace, "*", topLineSpace);
        // String secondLineLeft = String.format("%s%s%s", nc(space, (len * 3) / 4), leftSlash, nc(space, len / 4));
        // String secondLineRight = String.format("%s%s%s", nc(space, len / 4), rightSlash, nc(space, (len * 3) / 4));
        // String secondLine = String.format("%s%s%s", secondLineLeft, space, secondLineRight);
        // System.out.println(topLine);
        // System.out.println(secondLine);

        // TreeNode root = build(new Integer[]{0, 1, 2, 3, 4, 5, 6, 110, 120, 130, 140, 150, 160, 170, 180,});
        // TreeNode root = build(new Integer[]{1, 2, 3, null, 4, 5, null, null, null, 6, 7}, 1);
        // TreeNode root = buildCom(new Integer[]{0, null, 1, null, 2, null, 3, null, 4, null, 5, null, 6});
        TreeNode root = buildCom(new Integer[]{0, 1, null, 2, null, 3, null, 4, null, 5, null, 6});

        print(root, " ", 1);
    }


}
