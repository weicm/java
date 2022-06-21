package cn.julong.algorithm.leetcode;

/**
 * 33. 搜索旋转排序数组
 */
public class LC33SearchInRotatedSortedArray {
    public static void main(String[] args) {
        // output: 4
        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        // output: 1
        // int[] nums = new int[]{3, 1};
        // int target = 1;

        System.out.println(search(nums, target));
    }

    public static int search(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while(start <= end) {
            int mid = (start + end) >> 1;
            int sv = nums[start];
            int mv = nums[mid];
            int ev = nums[end];

            if(mv == target) {
                return mid;
            } else if(sv <= mv) {// 左边升序(注意：start = end + 1时，则start == mid，此时需要判断mid+1是否是目标值，所以需要sv <= mv)
                if(sv <= target && target < mv) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {// 右边升序
                if(mv < target && target <= ev) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        return -1;
    }
}
