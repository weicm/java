package cn.julong.algorithm.leetcode;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 每日温度
 */
public class LC739 {
    public static void main(String[] args) {
        LC739 driver = new LC739();
        int[] nums = new int[] {73,74,75,71,69,72,76,73}; // 1,1,4,2,1,1,0,0
        int[] ans = driver.dailyTemperatures(nums);
        System.out.println(Arrays.stream(ans).boxed().map(Object::toString).collect(Collectors.joining(",")));;
    }

    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];
        ans[0] = findFirstBig(temperatures, 1, temperatures[0]);
        for(int i = 1; i < n; i++) {
            int v = temperatures[i];
            int pv = temperatures[i - 1];
            if(v == pv && ans[i - 1] + i - 1 == 0) {
                ans[i] = 0;
                continue;
            }
            int start = v > pv ? ans[i - 1] + i - 1 : i + 1;
            int index = findFirstBig(temperatures, start, v);
            ans[i] = index == n ? 0 : index - i;
        }
        return ans;
    }

    int findFirstBig(int[] nums, int start, int v) {
        int i = start;
        while(i < nums.length && nums[i] <= v) i++;
        return i;
    }
}
