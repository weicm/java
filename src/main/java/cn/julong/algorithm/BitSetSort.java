package cn.julong.algorithm;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by weicm on 2017/6/6.
 */
public class BitSetSort {
    public static void main(String[] args) {
        int[][] arrays = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        show2Array(arrays);
    }

    static void show2Array(int[][] arrays) {
        Integer[] nums = new Integer[arrays[0].length * arrays.length];
        for (int index = 0, startX = arrays[0].length - 1; startX >= -arrays[0].length + 1; startX--) {
            for (int x = startX, y = 0; x < arrays[0].length && y < arrays.length; x++, y++) {
                if (x >= 0 && y >= 0)
                    nums[index++] = arrays[y][x];
            }
        }
        System.out.println(Arrays.asList(nums));
    }

    static void bitSet() {
        BitSet bits = new BitSet(1000000);
//        int[] nums = new int[10000000];
        List<Integer> nums = IntStream.range(0, 1000000).mapToObj(i -> new Integer(i)).collect(Collectors.toList());

        Collections.shuffle(nums);

        nums.stream().forEach(bits::set);

        System.out.println(bits);
    }
}
