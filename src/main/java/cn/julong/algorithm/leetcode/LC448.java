package cn.julong.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 找到所有数组中消失的数字
 *
 * @author: weichangming@baidu.com
 * @date: 2023/5/23
 */
public class LC448 {
    public static void main(String[] args) {
        LC448 driver = new LC448();

        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        List<Integer> ans = driver.findDisappearedNumbers(nums);
        System.out.println(ans);
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        List<Integer> ans = new ArrayList(n);

        for (int i = 0; i < n; i++) {
            int index = (nums[i] - 1) % n;
            nums[index] = nums[index] + n;
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                ans.add(i + 1);
            }
        }
        return ans;
    }
}
