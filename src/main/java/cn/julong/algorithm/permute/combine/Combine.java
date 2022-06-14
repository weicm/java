package cn.julong.algorithm.permute.combine;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Combine {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        // System.out.println(combineByViolent(nums));
        // System.out.println(combineByBinaryViolent(nums));
        System.out.println(combineByRecursive(nums));
        // System.out.println(combineByNM(nums, 3));
    }


    /**
     * 暴力解法
     * @param nums
     * @return
     */
    public static List<List<Integer>> combineByViolent(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        for (int l = 1; l <= n; l++) {
            for (int i = 0; i + l <= n; i++) {
                if(l == 1 && i > 0) {
                    break;
                }
                Deque<Integer> item = new ArrayDeque<>(l);
                for (int j = i; j < i + l - 1; j++) {
                    item.add(nums[j]);
                }
                for (int k = i + l - 1; k < nums.length; k++) {
                    item.addLast(nums[k]);
                    result.add(new ArrayList<>(item));
                    item.removeLast();
                }
            }
        }
        return result;
    }

    /**
     * 二进制暴力法（对应递归法）
     * @param nums
     * @return
     */
    public static List<List<Integer>> combineByBinaryViolent(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int size = 1 << nums.length;
        for (int mark = 0; mark < size; mark++) {
            List<Integer> item = new ArrayList<>(nums.length);
            for (int j = 0; j < nums.length; j++) {
                if((mark & (1 << j)) == 0) {
                    continue;
                }
                item.add(j);
            }
            result.add(item);
        }
        return result;
    }

    /**
     * 递归解法（二进制递归法）
     * @param nums
     */
    private static List<List<Integer>> combineByRecursive(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // 从第0个索引开始递归，状态
        localRevursive(nums, 0, result, new LinkedList<>());
        return result;
    }

    /**
     * 本地递归
     * 原理：
     * 1. 二级制原理，每位要么0，要么1
     * 2. 类比二进制，每个索引位置，要么不选，要么选
     * @param nums 数组
     * @param i 第i个索引
     * @param result 结果集
     * @param item 单个结果（递归的状态，需要重用）
     */
    private static void localRevursive(int[] nums, int i, List<List<Integer>> result, LinkedList<Integer> item) {
        // 递归到最后一个索引+1，则遍历结束，并添加结果
        if (i == nums.length) {
            result.add(new LinkedList<>(item));
            return;
        }
        // 每个索引位置两种选择：要么选，要么不选；这里0表示不选，1表示选
        localRevursive(nums, i + 1, result, item);

        item.addLast(nums[i]);
        localRevursive(nums, i + 1, result, item);
        item.removeLast();
    }

    /**
     * N个数中取M个的所有组合
     * @param nums
     * @param m
     * @return
     */
    private static List<List<Integer>> combineByNM(int[] nums, int m) {
        List<List<Integer>> result = new ArrayList<>();
        // 从第0个索引开始递归，状态
        localRevursiveNM(nums, 0, result, new LinkedList<>(), 0, m);
        return result;
    }

    private static void localRevursiveNM(int[] nums, int i, List<List<Integer>> result, LinkedList<Integer> item,
                                         int c, int m) {
        // 剪枝，快速返回
        if (c > m) {
            return;
        }
        // 递归到最后一个索引+1，则遍历结束，并添加结果
        if (i == nums.length) {
            // 选择数量等于M时，才满足结果
            if (c == m) {
                result.add(new LinkedList<>(item));
            }
            return;
        }
        // 每个索引位置两种选择：要么选，要么不选；这里0表示不选，1表示选
        localRevursiveNM(nums, i + 1, result, item, c, m);

        item.addLast(nums[i]);
        localRevursiveNM(nums, i + 1, result, item, c + 1, m);
        item.removeLast();
    }

}
