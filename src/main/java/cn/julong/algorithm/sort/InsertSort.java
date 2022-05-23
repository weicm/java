package cn.julong.algorithm.sort;

import java.util.Arrays;

/**
 * 插入排序
 * T(n) = O(n^2)
 */
public class InsertSort {
    public static void main(String[] args) {
        Integer[] arr = {8, 1, 9, 2, 5, 7, 3, 2};
        insertSort(arr);
        System.out.println(Arrays.asList(arr));
    }

    static void insertSort(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int current = arr[i + 1];
            int preIndex = i;
            while (preIndex >= 0 && arr[preIndex] > current) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = current;
        }
    }
}
