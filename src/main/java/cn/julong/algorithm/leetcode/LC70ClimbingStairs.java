package cn.julong.algorithm.leetcode;

public class LC70ClimbingStairs {
    public static void main(String[] args) {
        // System.out.println(climbStairsV1(44));
        // System.out.println(climbStairsV2(44));
        // System.out.println(climbStairsV3(44));
        System.out.println(climbStairsV4(44));
    }

    /**
     * 暴力递归
     * @param n
     * @return
     */
    public static int climbStairsV1(int n) {
        return recursiveV1(0, n);
    }

    private static int recursiveV1(int i, int n) {
        if(i == n) {
            return 1;
        }
        if(i > n) {
            return 0;
        }
        return recursiveV1(i + 1, n) + recursiveV1(i + 2, n);
    }

    /**
     * 计数递归
     * @param n
     * @return
     */
    public static int climbStairsV2(int n) {
        // status用来记录已经计算过的，避免每个分支重复计算，让递归快速返回
        int[] status = new int[n + 1];
        return recursiveV2(status, n);
    }

    private static int recursiveV2(int[] status, int n) {
        // status[n] > 0，说明第n层已经计算过有多少方法，让递归快速返回
        if(status[n] > 0) {
            return status[n];
        }

        if(n == 1) {// 第一层时，只有1种方法
            status[n] = 1;
        } else if(n == 2) {// 第二层时，只有2种方法
            status[n] = 2;
            return 2;
        } else {// 第n层时，可以从第n-1层爬一层，也可以从第n-2层爬两层，即方法数：recursiveV2(status, n - 1) + recursiveV2(status, n - 2)
            status[n] = recursiveV2(status, n - 1) + recursiveV2(status, n - 2);
        }
        return status[n];
    }

    /**
     * 动态规划
     * @param n
     * @return
     */
    public static int climbStairsV3(int n) {
        int[] status = new int[n];
        status[0] = 1;
        status[1] = 2;
        for (int i = 2; i < n; i++) {
            // 状态转移方程
            status[i] = status[i - 1] + status[i - 2];
        }
        return status[n - 1];
    }

    /**
     * 动态规划优化版：降低空间复杂度
     * 原理：状态转义方程只会用到前面两个状态，用三个状态即可满足状态转义方程
     * @param n
     * @return
     */
    public static int climbStairsV4(int n) {
        int first = 1, second = 2;
        if(n == 1) {
            return first;
        }
        for (int i = 2; i < n; i++) {
            // 状态转移方程
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
    }
}
