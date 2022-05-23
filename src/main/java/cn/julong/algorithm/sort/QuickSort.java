package cn.julong.algorithm.sort;

import java.util.Arrays;

/**
 * 快速排序
 * T(n) = O(nlogn)
 */
public class QuickSort {
    public static void main(String[] args) {
        Integer[] arr = {8, 1, 9, 2, 5, 7, 3, 2};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.asList(arr));
    }

    static void quickSort(Integer[] arr, Integer start, Integer end) {
        if(start >= end)
            return;
        // 保存左侧第一个元素，作为当前调整元素
        int tmp = arr[start];
        int i = start, j = end;
        while (i < j) {
            // 从右侧找比当前元素小的
            while (i < j && arr[j] > tmp) {
                j--;
            }
            // i < j：找到之后，复制到当前元素位置
            if (i < j) {
                arr[i++] = arr[j];
            }
            // 从左侧找比当前元素大的
            while (i < j && arr[i] <= tmp) {
                i++;
            }
            // i < j：找到之后，复制到右侧元素空出的位置
            if (i < j) {
                arr[j--] = arr[i];
            }
        }
        // 循环退出后，i == j，将当前元素放入中间空出的位置
        arr[i] = tmp;
        // 递归排序左侧部分
        quickSort(arr, start, i - 1);
        // 递归排序右侧部分
        quickSort(arr,i + 1, end);
    }
}
