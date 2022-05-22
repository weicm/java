package cn.julong.algorithm;

import java.util.Arrays;
import java.util.stream.Collectors;

public class HeapSort {


    /**
     * 数组标识的堆的特性，i为数组索引
     * 性质一: 索引为i的左孩子的索引是 (2*i+1);
     * 性质二: 索引为i的右孩子的索引是 (2*i+2);
     * 性质三: 索引为i的父结点的索引是 floor((i-1)/2);
     * @param args
     */
    public static void main(String[] args) {
        Integer[] arr = new Integer[]{3, 1, 10, 8, 8, 5};

        heapSort(arr);

        System.out.println(Arrays.stream(arr).collect(Collectors.toList()));
    }

    /**
     * 从根节点向下调整大根堆
     *
     * @param arr 待调整数组(子堆：除了根结点，所有节点都满足大根堆)
     * @param start 子堆根节点
     * @param end 子堆结束结点
     */
    public static void maxHeapDown(Integer[] arr, int start, int end) {
        // i: 根节点，j: 左/右子节点
        // j <= end: j = end - 1，或 j = end，表示最后一轮调整
        for (int i = start, j = 2 * i + 1; j <= end; i = j, j = 2 * i + 1) {
            // 比较左右节点较大一个（j < end: 如果最后一个子堆只有左节点，除了最后一个子堆，其他所有都包含左右节点）
            if (j < end && arr[j] < arr[j + 1]) {
                j++;
            }
            // 如果根节点大于子节点，则已经满足大根堆，直接结束调整；否则，继续调整，交换较大子节点和根节点
            if (arr[i] > arr[j]) {
                break;
            } else {
                arr[i] = arr[i] + arr[j];
                arr[j] = arr[i] - arr[j];
                arr[i] = arr[i] - arr[j];
            }

        }
    }

    /**
     * 堆排序
     * @param arr 待排序数组
     */
    public static void heapSort(Integer[] arr) {
        // 初始化堆，从最后一个子堆开始调整(i = arr.length / 2 - 1)，一直调整到第一个子堆（i = 0）
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            maxHeapDown(arr, i, arr.length - 1);
        }

        // 反复取根节点，放在最右侧，并重新从根节点开始调整堆
        for (int i = arr.length - 1; i > 0; i--) {
            // 交换根节点和最后一个节点
            arr[0] = arr[0] + arr[i];
            arr[i] = arr[0] - arr[i];
            arr[0] = arr[0] - arr[i];
            // 抛去堆尾元素（i - 1），放在最右侧，重新从根节点开始调整堆
            maxHeapDown(arr, 0, i - 1);
        }
    }
}

