package cn.julong.algorithm.sort;

import java.util.Arrays;
import java.util.BitSet;
import java.util.stream.Collectors;

/**
 * Created by weicm on 2017/6/6.
 * 位图排序
 * 注：不支持重复元素排序
 */
public class BitSetSort {
    public static void main(String[] args) {
        Integer[] arr = {15, 8, 1, 9, 2, 5, 7, 3};
        bitSetSort(arr);
        System.out.println(Arrays.asList(arr));
    }

    static void bitSetSort(Integer[] arr) {
        BitSet bits = new BitSet(16);

        Arrays.stream(arr).forEach(bits::set);

        int index = 0;
        for (Integer i : bits.stream().boxed().collect(Collectors.toList())) {
            arr[index++] = i;
        }
    }
}
