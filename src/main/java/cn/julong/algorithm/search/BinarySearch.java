package cn.julong.algorithm.search;

public class BinarySearch {
    public static void main(String[] args) {
        BinarySearch d = new BinarySearch();

        // n = 7
        int[] nums = new int[]{2, 4, 7, 7, 7, 9, 10};

        // 2, 4: nums[i] == target（验证能找到的情况）
        System.out.println(d.bsEqFirst(nums, 0, nums.length - 1, 7));
        System.out.println(d.bsEqLast(nums,  0, nums.length - 1, 7));
        System.out.println();

        // -1, -1, -1, -1: nums[i] == target（验证不能找到的情况）
        System.out.println(d.bsEqFirst(nums, 0, nums.length - 1, 1));
        System.out.println(d.bsEqFirst(nums, 0, nums.length - 1, 11));
        System.out.println(d.bsEqLast(nums,  0, nums.length - 1, 1));
        System.out.println(d.bsEqLast(nums,  0, nums.length - 1, 11));
        System.out.println();

        // 2: nums[i] >= target（验证等值情况）
        System.out.println(d.bsBeFirst(nums,  0, nums.length - 1, 7));
        // 4: nums[i] <= target（验证等值情况）
        System.out.println(d.bsLeFisrt(nums,  0, nums.length - 1, 7));
        System.out.println();

        // 1: nums[i] >= target（验证不等值情况）
        System.out.println(d.bsBeFirst(nums,  0, nums.length - 1, 3));
        // 0: nums[i] <= target（验证不等值情况）
        System.out.println(d.bsLeFisrt(nums,  0, nums.length - 1, 3));
        System.out.println();

        // 0, 7: nums[i] >= target（验证边界情况）
        System.out.println(d.bsBeFirst(nums,  0, nums.length - 1, 1));
        System.out.println(d.bsBeFirst(nums,  0, nums.length - 1, 11));

        // -1, 6: nums[i] <= target（验证边界情况）
        System.out.println(d.bsLeFisrt(nums,  0, nums.length - 1, 1));
        System.out.println(d.bsLeFisrt(nums,  0, nums.length - 1, 11));
    }

    /**
     * 等值首位二分查找
     * @param nums
     * @param start
     * @param end
     * @param v
     * @return 目标值索引（找不到时：-1）
     */
    int bsEqFirst(int[] nums, int start, int end, int v) {
        // 空数组或非法输入，直接返回-1
        if(start > end) {
            return -1;
        }

        // 切割到只有一个元素时，判断是否是目标值，是则返回下标，不是则返回-1
        if(start == end) {
            return nums[start] == v ? start : -1;
        }

        // 注意中间位置计算
        // 如果找首位，则用：(start + end) >> 1
        // 如果找末位，则用：(start + end + 1) >> 1
        int mid = (start + end) >> 1;

        // 找首位则用 >=，找末位则用 >
        if(nums[mid] >= v) {
            // mid位置可能是目标值，所以要包含在内
            return bsEqFirst(nums, start, mid, v);
        } else {
            // mid位置不可能是目标值，所以不用包含在内
            return mid < end ? bsEqFirst(nums, mid + 1, end, v) : -1;
        }
    }

    /**
     * 等值末位二分查找
     * @param nums
     * @param start
     * @param end
     * @param v
     * @return 目标值索引（找不到时：-1）
     */
    int bsEqLast(int[] nums, int start, int end, int v) {
        // 空数组或非法输入，直接返回-1
        if(start > end) {
            return -1;
        }

        // 切割到只有一个元素时，判断是否是目标值，是则返回下标，不是则返回-1
        if(start == end) {
            return nums[start] == v ? start : -1;
        }

        // 注意中间位置计算
        // 如果找首位，则用：(start + end) >> 1
        // 如果找末位，则用：(start + end + 1) >> 1
        int mid = (start + end + 1) >> 1;

        // 找首位则用 >=，找末位则用 >
        if(nums[mid] > v) {
            // mid位置不可能是目标值，所以不用包含在内
            return start < mid ? bsEqLast(nums, start, mid - 1, v) : -1;
        } else {
            // mid位置可能是目标值，所以要包含在内
            return bsEqLast(nums, mid, end, v);
        }
    }

    /**
     * 大于等于首位二分查找
     * @param nums
     * @param start
     * @param end
     * @param v
     * @return 目标值索引（找不到时：-1）
     */
    int bsBeFirst(int[] nums, int start, int end, int v) {
        // 空数组或非法输入，直接返回-1
        if(start > end) {
            return -1;
        }

        // 切割到只有一个元素时，就是first
        if(start == end) {
            return nums[start] >= v ? start : end + 1;
        }

        // 注意中间位置计算
        // 如果找首位，则用：(start + end) >> 1
        // 如果找末位，则用：(start + end + 1) >> 1
        int mid = (start + end) >> 1;

        // 找首位则用 >=，找末位则用 >
        if(nums[mid] >= v) {
            return bsBeFirst(nums, start, mid, v);
        } else {
            return mid < end ? bsBeFirst(nums, mid + 1, end, v) : mid + 1;
        }
    }

    /**
     * 小于等于末位二分查找
     * @param nums
     * @param start
     * @param end
     * @param v
     * @return 目标值索引（找不到时：nums.length + 1）
     */
    int bsLeFisrt(int[] nums, int start, int end, int v) {
        // 空数组或非法输入，直接返回-1
        if(start > end) {
            return -1;
        }

        // 切割到只有一个元素时，就是last
        if(start == end) {
            return nums[start] <= v ? start : start - 1;
        }

        // 注意中间位置计算
        // 如果找首位，则用：(start + end) >> 1
        // 如果找末位，则用：(start + end + 1) >> 1
        int mid = (start + end + 1) >> 1;

        // 找首位则用 >=，找末位则用 >
        if(nums[mid] > v) {
            return start < mid ? bsLeFisrt(nums, start, mid - 1, v) : mid - 1;
        } else {
            return bsLeFisrt(nums, mid, end, v);
        }
    }
}
