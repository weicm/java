package cn.julong.algorithm;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SortTest {

    public static void main(String[] args) {

        Integer[] arr = new Integer[]{3, 1, 10, 8, 8, 5};
        insertSort(arr);
        printArray(arr);
    }

    public static void printArray(Integer[] arr) {
        List<Integer> list = Arrays.stream(arr).collect(Collectors.toList());
        System.out.println(list);
    }

    public static void bubbleSort(Integer[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    arr[j] = arr[j] ^ arr[j + 1];
                    arr[j + 1] = arr[j] ^ arr[j + 1];
                    arr[j] = arr[j] ^ arr[j + 1];
                }
            }
        }
    }

    public static void selectSort(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int k = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[k]) {
                    k = j;
                }
            }
            if (k > i) {
                arr[i] = arr[i] ^ arr[k];
                arr[k] = arr[i] ^ arr[k];
                arr[i] = arr[i] ^ arr[k];
            }
        }
    }

    public static void insertSort(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int tmp = arr[i + 1];
            int j = i;
            for (; j >= 0 && arr[j] > tmp; j--) {
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = tmp;
        }
    }
}
