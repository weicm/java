package cn.julong.algorithm.leetcode;

public class LC42 {
    public static void main(String[] args) {
        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};

        System.out.println(trap(height));
    }

    public static int trap(int[] height) {
        int acc = 0;
        int n = height.length;

        if(n < 2) {
            return 0;
        }

        int[] lm = leftMatrix(height);
        int[] rm = rightMatrix(height);
        for(int i = 1; i < n - 1; i++) {
            int mm = Math.min(lm[i], rm[i]);
            acc += mm > height[i] ? mm - height[i] : 0;
        }
        return acc;
    }

    static int[] leftMatrix(int[] bs) {
        int n = bs.length;
        int[] lm = new int[n];

        for(int i = 1; i < n; i++) {
            lm[i] = i == 1 ? bs[0] : Math.max(bs[i-1], lm[i-1]);
        }
        return lm;
    }

    static int[] rightMatrix(int[] bs) {
        int n = bs.length;
        int[] rm = new int[n];
        for(int i = n - 2; i >= 0; i--) {
            rm[i] = i == n -2 ? bs[n - 1] : Math.max(bs[i+1], rm[i+1]);
        }
        return rm;
    }
}
