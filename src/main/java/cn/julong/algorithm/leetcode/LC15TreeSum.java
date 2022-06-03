package cn.julong.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和
 */
public class LC15TreeSum {
    public static void main(String[] args) {
        int[] in = new int[]{0,0,0, 0};
        // int[] in = new int[]{-1,0,1,2,-1,-4}; // 输出：[[-1,-1,2],[-1,0,1]]
        // int[] in = new int[]{0, 3, 0, 1, 1, -1, -5, -5, 3, -3, -3, 0};

        System.out.println(threeSumInSortAnd2Poiter(in));

        // quickSort(in, 0, in.length - 1);
        // System.out.println(Arrays.stream(in).boxed().collect(Collectors.toList()));

    }

    /**
     * 排序+双指针法
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSumInSortAnd2Poiter(int[] nums) {
        // quickSort(nums, 0, nums.length - 1);
        List<List<Integer>> result = new ArrayList<>();
        if(nums.length < 3) {
            return result;
        }
        // 排序：升序
        Arrays.sort(nums);

        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            // 如果第一个元素大于0，那后面元素一定大于0，三数之和必定大于0，所以直接退出
            if (nums[i] > 0) {
                break;
            }
            // 为了避免重复，当前索引值等于前一个索引值，直接跳过
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 以第一个元素为准，获取另外两个数的和
            int target = - nums[i];
            for (int l = i + 1, r = n - 1; l < r;) {
                // 找到两个数，记录结果
                if (nums[l] + nums[r] == target) {
                    result.add(Arrays.asList(nums[i], nums[l++], nums[r--]));
                    // 为了避免重复，当前索引值等于前一个索引值，直接跳过
                    while (l < r && nums[l] == nums[l - 1]) {
                        l++;
                    }
                    // 为了避免重复，当前索引值等于后一个索引值，直接跳过
                    while (l < r && nums[r] == nums[r + 1]) {
                        r--;
                    }
                } else if (nums[l] + nums[r] <  target) { // 两数之和小于目标值，左侧指针右移
                    l++;
                } else { // 两数之和大于目标值，右侧指针左移
                    r--;
                }
            }
        }
        return result;
    }

    private static void quickSort(int[] nums, int s, int e) {
        if (s >= e) {
            return;
        }

        int i = s, j = e, t = nums[s];
        while (i < j) {
            while (j > i && nums[j] > t) {
                j--;
            }
            if (j > i) {
                nums[i++] = nums[j];
            }
            while (j > i && nums[i] <= t) {
                i++;
            }
            if (j > i) {
                nums[j--] = nums[i];
            }
        }
        nums[i] = t;
        quickSort(nums, s, i - 1);
        quickSort(nums, i + 1, e);
    }
    /**
     * 暴力解法：超时，不满足时间复杂度
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> r = new ArrayList<>();
        for(int i = 0; i < nums.length; i++) {
            for(int j = 0; j < nums.length; j++) {
                if(i == j) continue;
                for(int k = 0; k < nums.length; k++) {
                    if(k == j || k == i) continue;
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> e = new ArrayList(3);
                        e.add(nums[i]);
                        e.add(nums[j]);
                        e.add(nums[k]);

                        int x = 0;
                        for(; x < r.size(); x++) {
                            if(e.containsAll(r.get(x)) && r.get(x).containsAll(e)) {
                                break;
                            }
                        }
                        if(x == r.size()) {
                            r.add(e);
                        }
                    }
                }
            }
        }
        return r;
    }
}
