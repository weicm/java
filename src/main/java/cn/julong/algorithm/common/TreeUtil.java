package cn.julong.algorithm.common;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    public static void print(TreeNode root) {
        String[] matrix = dfs(root, " ", "/", "\\", 3);
        Arrays.stream(matrix).forEach(System.out::println);
    }
    private static String[] dfs(TreeNode node, String space, String leftSlash, String rightSlash, Integer width) {
        if (null == node.left && null == node.right) {
            return new String[]{node.val.toString()};
        }
        String[] leftMatrix = dfs(node.left, space, leftSlash, rightSlash, width);
        String[] rightMatrix = dfs(node.right, space, leftSlash, rightSlash, width);

        Integer len = Optional.ofNullable(leftMatrix)
                .map(m -> m[0].length())
                .orElse(rightMatrix[0].length());

        Integer high = Optional.ofNullable(leftMatrix)
                .map(m -> m.length)
                .orElse(rightMatrix.length);
        String topLineSpace = nc(space, len);
        String topLine = String.format("%s%s%s", topLineSpace, cm(space, width, node.val), topLineSpace);
        String secondLineLeft = String.format("%s%s%s", nc(space, (len * 3) / 4), leftSlash, nc(space, len / 4));
        String secondLineRight = String.format("%s%s%s", nc(space, len / 4), rightSlash, nc(space, (len * 3) / 4));
        String secondLine = String.format("%s%s%s", secondLineLeft, nc(space, width), secondLineRight);

        String[] matrix = new String[high + 2];
        matrix[0] = topLine;
        matrix[1] = secondLine;
        for (int i = 0; i < high; i++) {
            String leftLine = leftMatrix == null ? nc(space, len) : leftMatrix[i];
            String rightLine = rightMatrix == null ? nc(space, len) : rightMatrix[i];
            String line = String.format("%s%s%s", leftLine, nc(space, width), rightLine);
            matrix[i + 2] = line;
        }
        return matrix;
    }

    private static String nc(String c, Integer n) {
        return IntStream.range(0, n).boxed().map(i -> c).collect(Collectors.joining(""));
    }

    private static String cm(String space, Integer width, Integer num) {
        int spaceNum = width - num.toString().length();
        String leftSpace = nc(space,  spaceNum % 2 == 0 ? spaceNum / 2 : (spaceNum + 1) / 2);
        String rightSpace = nc(space, spaceNum % 2 == 0 ? spaceNum / 2 : (spaceNum - 1) / 2);
        return String.format("%s%d%s", leftSpace, num, rightSpace);
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

        TreeNode root = buildTree(new Integer[]{0, 1, 2, 3, 4, 5, 6, 110, 120, 130, 140, 150, 160, 170, 180,});
        print(root);
        // System.out.println(cm("-", 1, 1));
        // System.out.println(cm("-", 2, 2));
        // System.out.println(cm("-", 2, 22));
        // System.out.println(cm("-", 3, 3));
        // System.out.println(cm("-", 3, 33));
        // System.out.println(cm("-", 3, 333));
    }


}
