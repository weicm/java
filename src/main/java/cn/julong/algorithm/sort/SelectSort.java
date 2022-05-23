package cn.julong.algorithm.sort;

import java.util.Arrays;

/**
 * 选择排序
 * T(n) = O(n^2)
 */
public class SelectSort {
    public static void main(String[] args) {
        Integer[] arr = {8, 1, 9, 2, 5, 7, 3, 2};
        selectSort(arr);
        System.out.println(Arrays.asList(arr));
    }

    static void selectSort(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int k = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[k]) {
                    k = j;
                }
            }
            int tmp = arr[k];
            arr[k] = arr[i];
            arr[i] = tmp;
        }
    }
}
