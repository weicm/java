package cn.julong.algorithm.sort;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SortTest2 {
    public static void main(String[] args) {
        Integer[] arr = new Integer[]{3, 1, 10, 8, 8, 5};
        // bubbleSort(arr);
        // selectSort(arr);
        // insertSort(arr);
        // quickSort(arr, 0, arr.length - 1);
        // heapSort(arr);
        mergeSort(arr, 0, arr.length - 1);
        print(arr);
    }

    static void print(Integer[] arr) {
        System.out.println(Arrays.stream(arr).map(i -> i.toString()).collect(Collectors.toList()));
        ;
    }

    static boolean swap(Integer[] arr, int i, int j) {
        if (i == j || arr[i].equals(arr[j])) {
            return false;
        }
        arr[i] = arr[i] + arr[j];
        arr[j] = arr[i] - arr[j];
        arr[i] = arr[i] - arr[j];
        return true;
    }

    static void bubbleSort(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    static void selectSort(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int k = 0;
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[k]) {
                    k = j;
                }
            }
            swap(arr, k, arr.length - i - 1);
        }
    }

    static void insertSort(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int j = 0;
            for (; j < i; j++) {
                if (arr[j] >= arr[i]) {
                    break;
                }
            }
            int tmp = arr[i];
            for (int k = j; k < i; k++) {
                arr[k + 1] = arr[k];
            }
            arr[j] = tmp;
        }
    }

    static void quickSort(Integer[] arr, int s, int e) {
        if (s >= e) {
            return;
        }
        int i = s, j = e;
        while (i < j) {
            while (i < j && arr[i] <= arr[j]) {
                i++;
            }
            if (i < j) {
                swap(arr, i, j--);
            }
            while (i < j && arr[i] <= arr[j]) {
                j--;
            }
            if (i < j) {
                swap(arr, i++, j);
            }
        }
        quickSort(arr, s, i - 1);
        quickSort(arr, i + 1, e);
    }

    static void heapSort(Integer[] arr) {
        for (int i = (arr.length - 1) / 2; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, 0, i);
            adjustHeap(arr, 0, i);
        }
    }

    static void adjustHeap(Integer[] arr, int i, int e) {
        if (i >= e) {
            return;
        }
        int li = 2 * i + 1, ri = 2 * i + 2;
        if (li < e && arr[li] > arr[i]) {
            swap(arr, i, li);
            adjustHeap(arr, li, e);
        }
        if (ri < e && arr[ri] > arr[i]) {
            swap(arr, i, ri);
            adjustHeap(arr, ri, e);
        }
    }

    static void mergeSort(Integer[] arr, int s, int e) {
        if (s >= e) {
            return;
        }
        int mid = (s + e) / 2;
        mergeSort(arr, s, mid);
        mergeSort(arr, mid + 1, e);

        int i = s, j = mid + 1, k = 0;
        Integer[] tmp = new Integer[e - s + 1];
        while (i <= mid && j <= e) {
            if (arr[i] > arr[j]) {
                tmp[k++] = arr[j++];
            } else {
                tmp[k++] = arr[i++];
            }
        }
        while (i <= mid) {
            tmp[k++] = arr[i++];
        }

        while (j <= e) {
            tmp[k++] = arr[j++];
        }

        for (int x = s; x < tmp.length; x++) {
            arr[x] = tmp[x];
        }
    }

}
