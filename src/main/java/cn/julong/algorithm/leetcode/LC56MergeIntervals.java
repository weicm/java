package cn.julong.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 56. 合并区间
 */
public class LC56MergeIntervals {

    public static void main(String[] args) {
        // [2,3],[4,5],[6,7],[8,9],[1,10]]
        // int[][] intervals = new int[][]{
        //         new int[]{2,3},
        //         new int[]{4,5},
        //         new int[]{6,7},
        //         new int[]{8,9},
        //         new int[]{1,10}
        // };
        //[[1,3],[2,6],[8,10],[15,18]]
        // int[][] intervals = new int[][]{
        //         new int[]{1,3},
        //         new int[]{2,6},
        //         new int[]{8,10},
        //         new int[]{15,18}
        // };

        //[[1,4],[4,5]]
        // int[][] intervals = new int[][]{
        //         new int[]{1,4},
        //         new int[]{4,5}
        // };

        //[[1,4],[0,4]]
        int[][] intervals = new int[][]{
                new int[]{1,4},
                new int[]{0,4}
        };

        int[][] result = merge(intervals);

        printResult(result);
    }

    static void printResult(int[][] result) {
        List<List<Integer>> listResult = new ArrayList<>(result.length);

        for (int[] arr : result) {
            listResult.add(Arrays.asList(arr[0], arr[1]));
        }

        System.out.println(listResult);
    }
    public static int[][] merge(int[][] intervals) {

        Arrays.sort(intervals, (l, r) -> l[0] - r[0] == 0 ? l[1] - r[1] : l[0] - r[0]);

        List<int[]> result = new ArrayList<>(intervals.length);
        int p = 0, len = intervals.length;
        for (int i = 0; i < len; ) {
            int[] cur = intervals[p];
            int vr = cur[1];
            while (i < len && vr >= intervals[i][0]) {
                vr = Math.max(intervals[i][1], vr);
            }
            result.add(new int[] {cur[0], vr});
            p = ++i;
        }

        return result.toArray(new int[result.size()][2]);
    }

}
