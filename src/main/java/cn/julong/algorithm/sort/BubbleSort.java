package cn.julong.algorithm.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * T(n) = O(n^2)
 */
public class BubbleSort {
    public static void main(String[] args) {
        Integer[] arr = {8, 1, 9, 2, 5, 7, 3, 2};
        bubbleSort(arr);
        System.out.println(Arrays.asList(arr));
    }

    public static void bubbleSort(Integer arr[]) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]){
                    int tmp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }

    /*public static void bubbleSort(Integer arr[]) {
        int j = arr.length - 1;
        while (j > 0) {
            int i = 0;
            while (i < j) {
                if (arr[i] > arr[i+1]) {
                    int max = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = max;
                }
                i++;
            }
            j--;
        }
    }*/
}
