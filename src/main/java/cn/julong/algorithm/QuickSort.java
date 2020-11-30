package cn.julong.algorithm;

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
        int tmp = arr[start];
        int i = start, j = end;
        while (i < j) {
            while (i < j && arr[j] >= tmp) {
                j--;
            }
            arr[i] = arr[j];
            while (i < j && arr[i] <= tmp) {
                i++;
            }
            arr[j] = arr[i];
        }
        arr[i] = tmp;
        quickSort(arr, start, i - 1);
        quickSort(arr,i + 1, end);
    }
}
