package cn.julong.algorithm;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SortTest {

    public static void main(String[] args) {


        Integer[] arr = new Integer[]{3, 1, 10, 8, 8, 5};

        heapSort(arr);
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

    public static void mergeSort(Integer[] arr, int start, int end) {
        if(start >= end) {
            return;
        }

        int mid = (start + end) / 2;
        mergeSort(arr, start, mid);
        mergeSort(arr, mid + 1, end);

        Integer[] tmp = new Integer[end - start + 1];
        for (int i = start, j = mid + 1, k = 0; k < tmp.length;) {
            if (i <= mid && j <= end && arr[i] < arr[j]) {
                tmp[k++] = arr[i++];
            } else if (i <= mid && j <= end && arr[i] >= arr[j]) {
                tmp[k++] = arr[j++];
            } else if (i <= mid) {
                tmp[k++] = arr[i++];
            } else {
                tmp[k++] = arr[j++];
            }
        }
        for (int i = 0, j = start; i < tmp.length; ) {
            arr[j++] = tmp[i++];
        }
    }

    public static void quickSort(Integer[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int i = start, j = end;
        int tmp = arr[j];
        while (i < j) {
            while (i < j && arr[i] < tmp) {
                i++;
            }
            if (i < j) {
                arr[j--] = arr[i];
            }
            while (i < j && arr[j] >= tmp) {
                j--;
            }
            if (i < j ) {
                arr[i++] = arr[j];
            }
        }
        arr[i] = tmp;

        quickSort(arr, start, i - 1);
        quickSort(arr, i + 1, end);
    }

    public static void heapSort(Integer[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            maxHeapDown(arr, i, arr.length - 1);
        }

        for (int i = arr.length - 1; i > 0; i--) {
            arr[0] = arr[0] + arr[i];
            arr[i] = arr[0] - arr[i];
            arr[0] = arr[0] - arr[i];
            maxHeapDown(arr, 0, i - 1);
        }
    }

    public static void maxHeapDown(Integer[] arr, int start, int end) {
        for (int i = start, li = 2 * i + 1; li <= end; i = li, li = 2 * i + 1) {
            if (li < end && arr[li] < arr[li + 1]) {
                li++;
            }
            if (arr[i] >= arr[li]) {
                break;
            } else {
                arr[i] = arr[i] + arr[li];
                arr[li] = arr[i] - arr[li];
                arr[i] = arr[i] - arr[li];
            }
        }
    }
}
