package cn.julong.algorithm.sort;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

/**
 * Created by weicm on 2017/6/6.
 * 归并排序
 * T(n) = O(nlogn)
 */
public class MergeSort {
    public static void main(String[] args) {

        /*Integer[] a1 = new Integer[]{2, 5, 8, 10};
        Integer[] a2 = new Integer[]{3, 7, 15};
        Integer[] a3 = new Integer[]{1, 3, 8};
        Integer[] a4 = new Integer[]{6, 7, 9};

        System.out.println(Arrays.asList(multiblePathMerge(a3, a4)));*/

       /* List<Integer> nums = IntStream.range(0, 1000000).mapToObj(i -> new Integer(i)).collect(Collectors.toList());

        Collections.shuffle(nums);
        Integer[] array = nums.toArray(new Integer[nums.size()]);*/
        Integer[] nums = new Integer[]{4, 8, 2, 15, 3};
        mergeSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.asList(nums));

    }

    /**
     * 二路归并排序（递归实现）
     *
     * @param nums  待排序的数组
     * @param start 要数组排部分的起始位置索引
     * @param end   要数组排部分的截止位置索引
     */
    public static void mergeSort(Integer[] nums, int start, int end) {
        //只有一个元素，有序，结束
        if (start == end)
            return;

        int mid = (start + end) / 2;
        //排序做变部分
        mergeSort(nums, start, mid);
        //排序右边部分
        mergeSort(nums, mid + 1, end);

        //本次排序缓存
        Integer[] sorted = new Integer[end - start + 1];

        for (int index = 0, i = start, j = mid + 1; index < sorted.length; index++) {
            if (i > mid) {//左边剩余，将剩余元素追加到缓存
                sorted[index] = nums[j++];
            } else if (j > end) {//右边剩余，将剩余元素追加到缓存
                sorted[index] = nums[i++];
            } else if (nums[i] < nums[j]) {//左边i元素小于右边j元素，小元素追加到缓存
                sorted[index] = nums[i++];
            } else {//右边j元素小于左边i元素，小元素追加到缓存
                sorted[index] = nums[j++];
            }
        }
        //将缓存内容写回源数组
        for (int k : sorted) {
            nums[start++] = k;
        }
    }

    /**
     * 文件多路归并
     *
     * @param out
     * @param ins
     */
    public static void fileMultiblePathMerge(RandomAccessFile out, RandomAccessFile[] ins) throws IOException {
        //输出化指针
        out.seek(0);
        Arrays.stream(ins).forEach(in -> {
            try {
                in.seek(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }

    /**
     * 多路归并
     *
     * @param arrays 待归并的数组
     * @return
     */
    public static Integer[] multiblePathMerge(Integer[]... arrays) {
        //计算结果集数组大小
        int nums = 0;
        for (Integer[] array : arrays) {
            nums += array.length;
        }
        //归并结果集
        Integer[] mergedResult = new Integer[nums];
        //申请n个路径指针，记录当前路径遍历的位置
        int[] indexs = new int[arrays.length];
        //申请n个路径结束标志位，标记路径已经遍历结束
        int[] isOvers = new int[arrays.length];
        //结果数组指针
        int pointer = 0;
        //已经结束遍历的路径记住器
        int overCount = 0;
        //开头值最小的路径，默认取第一条
        int minStartValuePath = 0;
        while (true) {
            //归并结束则跳出循环
            if (overCount == isOvers.length)
                break;

            //求所有路的开头值最小的路径
            for (int i = 0; i < isOvers.length; i++) {
                if (isOvers[i] == 0) {
                    minStartValuePath = i;
                    break;
                }
            }
            //求开头值最小的路径
            for (int i = 0; i < arrays.length; i++) {
                if (isOvers[i] == 1 || minStartValuePath == i)
                    continue;
                if (arrays[i][indexs[i]] < arrays[minStartValuePath][indexs[minStartValuePath]]) {
                    minStartValuePath = i;
                }
            }

            //将开头值最小的路径的值保存下来，路径索引自增
            mergedResult[pointer++] = arrays[minStartValuePath][indexs[minStartValuePath]++];

            //如果最小值路径已经到达结尾，则标记结束并把结束路径计数器自增
            if (indexs[minStartValuePath] == arrays[minStartValuePath].length) {
                isOvers[minStartValuePath] = 1;
                overCount++;
            }
        }

        return mergedResult;
    }


}
;