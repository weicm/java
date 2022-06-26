package cn.julong.algorithm.leetcode;

/**
 * 53. 最大子数组和
 */
public class LC53MaximumSubarray {
    public static void main(String[] args) {
        // int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}; // output: 6
        // int[] nums = new int[]{1}; // output: 1
        int[] nums = new int[]{5,4,-1,7,8}; // output: 23
        System.out.println(maxSubArray(nums));
    }

    public static int maxSubArray(int[] nums) {

        // return byViolent(nums);
        // return byDynamicPlan(nums);
        return byDivision(nums);
    }

    /**
     * 暴力破解
     * @param nums
     * @return
     */
    public static int byViolent(int[] nums) {
        int maxVal = Integer.MIN_VALUE, n = nums.length;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= n - i; j++) {
                int sum = 0;
                for (int k = j; k < j + i; k++) {
                    sum += nums[k];
                }
                maxVal = Math.max(sum, maxVal);
            }
        }
        return maxVal;
    }

    /**
     * 动态规划
     * @param nums
     * @return
     */
    public static int byDynamicPlan(int[] nums) {
        int n = nums.length, maxVal = Integer.MIN_VALUE;
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            if(i > 0 && dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
            maxVal = Math.max(maxVal, dp[i]);
        }

        return maxVal;
    }

    /**
     * 分治算法
     * 链接：https://leetcode.cn/problems/maximum-subarray/solution/zui-da-zi-xu-he-by-leetcode-solution/
     * @param nums
     * @return
     */
    public static int byDivision(int[] nums) {
        return getInfo(nums, 0, nums.length - 1).mSum;
    }

    /**
     * 获取区间[l, r]之间的状态
     * @param nums 输入数组
     * @param l 左索引
     * @param r 右索引
     * @return 状态
     */
    public static Status getInfo(int[] nums, int l, int r) {
        // 二分退出条件
        if(l == r) {
            return new Status(nums[l], nums[l], nums[l], nums[l]);
        }
        // 二分中间索引
        int mid = (l + r) >> 1;
        // 获取左子区间状态
        Status lStatus = getInfo(nums, l, mid);
        Status rStatus = getInfo(nums, mid + 1, r);
        return pushUp(lStatus, rStatus);
    }

    /**
     * 递归回升
     * @param lStatus 左侧状态
     * @param rStatus 右侧状态
     * @return [l, r] 范围的状态
     */
    public static Status pushUp(Status lStatus, Status rStatus) {
        // 区间 [l,r] 的 iSum 就等于「左子区间」的 iSum 加上「右子区间」的 iSum。
        int is = lStatus.iSum + rStatus.iSum;
        // 区间 [l,r] 的 lSum，存在两种可能，它要么等于「左子区间」的 lSum，要么等于「左子区间」的 iSum 加上「右子区间」的 lSum，二者取大。
        int ls = Math.max(lStatus.lSum, lStatus.iSum + rStatus.lSum);
        // 区间 [l,r] 的 rSum，同理，它要么等于「右子区间」的 rSum，要么等于「右子区间」的 iSum 加上「左子区间」的 rSum，二者取大。
        int rs = Math.max(rStatus.rSum, rStatus.iSum + lStatus.rSum);
        // 区间[l,r] 的 mSum 了。存在三种可能，可能是「左子区间」的 mSum 或 「右子区间」的 mSum 中的一个，
        // 它也可能跨越 m，即「左子区间」的 rSum 和「右子区间」的 lSum 求和。三者取大。
        int ms = Math.max(Math.max(lStatus.mSum, rStatus.mSum), lStatus.rSum + rStatus.lSum);

        return new Status(ls, rs, ms, is);
    }

    static class Status {
        // lSum 表示 [l,r] 内以 l 为左端点的最大子段和
        // rSum 表示 [l,r] 内以 r 为右端点的最大子段和
        // mSum 表示 [l,r] 内的最大子段和
        // iSum 表示 [l,r] 的区间和
        int lSum, rSum, mSum, iSum;

        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }

}
