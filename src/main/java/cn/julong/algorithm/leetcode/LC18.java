package cn.julong.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 四数之和
 *
 * @author: weichangming@baidu.com
 * @date: 2023/5/12
 */
public class LC18 {

    public static void main(String[] args) {
        System.out.println(fourSum(new int[] {1000000000,1000000000,1000000000,1000000000}, -294967296));
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList();
        Arrays.sort(nums);
        int len = nums.length;
        for(int i = 0; i< len; i++) {
            if(i > 0 && nums[i] == nums[i-1]) {
                continue;
            }

            for(int j = i + 1; j < len - 2; j++) {
                if(j > i + 1 && nums[j] == nums[j-1]) {
                    continue;
                }

                for(int l = j + 1; l < len - 1; l++) {
                    if(l > j + 1 && nums[l] == nums[l - 1]) {
                        continue;
                    }

                    int r = len - 1;

                    long longTarget = target;
                    while(l < r && 0L + nums[i] + nums[j] + nums[l] + nums[r] > longTarget) {
                        r--;
                    }

                    if(l == r) {
                        break;
                    }

                    if(0L + nums[i] + nums[j] + nums[l] + nums[r] == longTarget) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                    }
                }
            }
        }
        return ans;
    }
}
