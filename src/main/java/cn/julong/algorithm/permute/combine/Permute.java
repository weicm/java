package cn.julong.algorithm.permute.combine;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class Permute {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(permuteByDfs(nums));
        System.out.println(permuteByViolent(nums));
    }

    /**
     * dfs法
     * @param nums
     * @return
     */
    public static List<List<Integer>> permuteByDfs(int[] nums) {
        List<List<Integer>> result = new ArrayList();
        boolean[] status = new boolean[nums.length];
        dfs(nums, status, new ArrayDeque(), result);
        return result;
    }

    private static void dfs(int[] nums, boolean[] status, Deque<Integer> path, List<List<Integer>> result) {
        if(path.size() == nums.length) {
            result.add(new ArrayList(path));
            return;
        }

        for(int i = 0; i < nums.length; i++) {
            if(status[i]) {
                continue;
            }
            path.addLast(nums[i]);
            status[i] = true;
            dfs(nums, status, path, result);
            status[i] = false;
            path.removeLast();
        }
    }

    /**
     * 暴力法（找下一个排列）
     * 1. 从右侧找第一个升序元素j索引
     * 2. 将j索引和n-1索引位置元素调换
     * 3. 将j+1到n-1索引元素翻转
     * @param nums
     * @return
     */
    public static List<List<Integer>> permuteByViolent(int[] nums) {
        List<List<Integer>> result = new ArrayList();
        List<Integer> item = Arrays.stream(nums).boxed().collect(Collectors.toList());
        do {
            result.add(item);
            item = nextPermute(nums);
        } while (null != item);
        return result;
    }

    /**
     * 下一个排列
     * @param nums
     * @return
     */
    private static List<Integer> nextPermute(int[] nums) {
        int n = nums.length, i = n - 2;
        // 从右侧找第一个升序位置i
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if(i < 0) {
            return null;
        }

        // 从右边找第一个大于i位置值的位置j（即大于i位置的做小元素）
        int j = n - 1;
        while(i < j && nums[i] >= nums[j]) {
            j--;
        }

        // 对调i和j位置，[i + 1, n - 1] 仍然是降序
        swap(nums, i, j);

        // 翻转[i + 1, n - 1]之间的元素，即降序改为升序，保证值最小
        j = i + 1;
        i = n - 1;
        for (; j < i; j++, i--) {
            swap(nums, i, j);
        }

        return Arrays.stream(nums).boxed().collect(Collectors.toList());
    }

    private static void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] + nums[j];
        nums[j] = nums[i] - nums[j];
        nums[i] = nums[i] - nums[j];
    }
}
