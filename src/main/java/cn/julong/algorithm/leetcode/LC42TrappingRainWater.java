package cn.julong.algorithm.leetcode;

/**
 * 接雨水
 */
public class LC42TrappingRainWater {
    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        // int[] height = {4, 2, 3};
        // int[] height = {4, 2, 0, 3, 2, 5};

        System.out.println(trapWithTwoPoter(height));
    }

    /**
     * 双指针法
     *
     * @param height
     * @return
     */
    public static int trapWithTwoPoter(int[] height) {
        int acc = 0, l = 0, r = height.length - 1, lmh = 0, rmh = 0;
        while (l < r) {
            lmh = Math.max(lmh, height[l]);
            rmh = Math.max(rmh, height[r]);
            if (height[l] < height[r]) {
                acc += lmh - height[l++];
            } else {
                acc += rmh - height[r--];
            }
        }
        return acc;
    }

    /**
     * 暴力破解法
     *
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        int acc = 0;
        for (int l = 0; l < height.length - 2; ) {
            // 上坡，找左侧边
            while (l < height.length - 2 && height[l] <= height[l + 1]) {
                l++;
            }
            int r = l;

            // 下坡，找第一个波谷
            while (r < height.length - 1 && height[r] >= height[r + 1]) {
                r++;
            }
            int i = r + 1;

            // 找右侧边：大于最小右侧边，小于左侧边值则结束
            do {
                if (i < height.length && height[i] > height[r]) {
                    r = i;
                }
            } while (i < height.length && height[i++] < height[l]);

            // 从新定位左侧边：最后一个大于等于右侧边的位置
            while (l < r - 1 && height[l + 1] >= height[r]) {
                l++;
            }

            // 计算数值占用空间
            int tmp = 0;
            for (int k = l + 1; k < r; k++) {
                tmp += height[k];
            }

            if (r < height.length && height[l] < height[r]) {
                // 左侧边大，以左侧边为准计算累计和并减去数值占用空间
                acc += (r - l - 1) * height[l] - tmp;
            } else if (r < height.length && height[l] >= height[r]) {
                // 右侧边大，以右侧边为准计算累计和并减去数值占用空间
                acc += (r - l - 1) * height[r] - tmp;
            } else {
                // 未找到右侧边，则表示结束
                break;
            }
            // 每轮结束，从右侧边开始重新开始找盆地
            l = r;
        }
        return acc;
    }
}
