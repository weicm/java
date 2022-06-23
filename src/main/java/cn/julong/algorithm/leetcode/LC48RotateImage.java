package cn.julong.algorithm.leetcode;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 48. 旋转图像
 */
public class LC48RotateImage {
    public static void main(String[] args) {
        // int[][] matrix = new int[][]{
        //         new int[]{1, 2, 3},
        //         new int[]{4, 5, 6},
        //         new int[]{7, 8, 9}
        // };

        int[][] matrix = new int[][]{
                new int[]{1, 2, 3, 4},
                new int[]{5, 6, 7, 8},
                new int[]{9, 10, 11, 12},
                new int[]{13, 14, 15, 16},
        };

        rotate(matrix);

        printMetrix(matrix);

    }

    private static void printMetrix(int[][] matrix) {
        for (int[] line : matrix) {
            System.out.println(Arrays.stream(line).boxed().map(Object::toString).collect(Collectors.joining(", ")));
        }
    }

    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        // 行 i：[0, (n - 1) / 2]
        for(int i = 0; i <= (n - 1) / 2; i++) {
            // 列 j：[i, n - i - 1)
            for(int j = i; j < n - i - 1; j++) {
                // x：左坐标，y：右坐标，curVal：当前坐标值
                int x = i, y = j, curVal = matrix[x][y];
                // 旋转90度，从左上角依次移动到旋转的位置
                for(int k = 0; k < 4; k++) {
                    // 记录当前坐标
                    int px= x, py = y;
                    // 计算旋转目标位置的坐标
                    x = py;
                    y = n - px - 1;
                    // 记录目标坐标处的值修，即下一个需要计算元素的坐标
                    int nVal = matrix[x][y];
                    // 将当前坐标值复制到目标坐标处
                    matrix[x][y] = curVal;
                    // 更新下一个计算元素的值
                    curVal = nVal;
                }
            }
        }
    }
}
