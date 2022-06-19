package cn.julong.algorithm.leetcode;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 备注：二分查找需要注意计算中间索引方向
 * 靠左计算：mid = (start + end) / 2
 * 靠有计算：mid = (start + end + 1) / 2
 */
public class LC34FindFirstAndLastPositionOfElementInSortedArray {
    public static void main(String[] args) {
        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        int target = 6;

        int[] indexes = searchRange(nums, target);
        System.out.println(
                Arrays.stream(indexes).boxed().collect(Collectors.toList())
        );
    }

    public static int[] searchRange(int[] nums, int target) {
        int fi = findFirstIndex(nums, target, 0, nums.length - 1);
        if(fi < 0) {
            return new int[] {-1, -1};
        }

        int li = findLastIndex(nums, target, fi, nums.length - 1);

        return new int[] {fi, li};
    }

    private static int findFirstIndex(int[] nums, int target, int start, int end) {
        while(start < end) {
            // 从右向左计算中间位置，中间位置偏左
            int mid = (start + end) >> 1;
            if(nums[mid] < target) {
                start = mid + 1;
            } else if(nums[mid] > target) {
                end = mid - 1;
            } else {// 从右向左计算中间位置，右边逐渐压缩
                end = mid;
            }
        }

        if(nums[start] == target) {
            return start;
        }
        return -1;
    }


    private static int findLastIndex(int[] nums, int target, int start, int end) {
        while(start < end) {
            // 从左向右计算中间位置，中间位置偏右
            int mid = (start + end + 1) >> 1;
            if(nums[mid] < target) {
                start = mid + 1;
            } else if(nums[mid] > target) {
                end = mid - 1;
            } else {// 从左向右计算中间位置，左边逐渐压缩
                start = mid;
            }
        }

        return start;
    }
}
