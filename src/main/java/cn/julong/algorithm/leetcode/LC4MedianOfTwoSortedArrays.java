package cn.julong.algorithm.leetcode;

/**
 * 4. 寻找两个正序数组的中位数
 */
public class LC4MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{3, 4};

        // int[] nums1 = new int[]{};
        // int[] nums2 = new int[]{1};

        // int[] nums1 = new int[]{2};
        // int[] nums2 = new int[]{};

        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length, mid = (len - 1) / 2;
        int i = 0, j = 0, p = 0, pre = 0, cur = 0;
        while (p++ <= mid + 1) {
            pre = cur;
            if (i < nums1.length && j < nums2.length && nums1[i] < nums2[j]) {
                cur= nums1[i++];
            } else if (i < nums1.length && j < nums2.length && nums1[i] >= nums2[j]) {
                cur = nums2[j++];
            } else if (i == nums1.length && j < nums2.length) {
                cur = nums2[j++];
            } else if(j == nums2.length && i < nums1.length) {
                cur = nums1[i++];
            } else {
                cur = 0;
            }
        }
        return len % 2 == 1 ? pre : (pre + cur) / 2D;
    }
}
