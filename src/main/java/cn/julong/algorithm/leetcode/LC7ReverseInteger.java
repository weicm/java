package cn.julong.algorithm.leetcode;

/**
 * 7. 整数反转
 */
public class LC7ReverseInteger {
    public static void main(String[] args) {
        int x = -123;
        System.out.println(reverse(x));
    }

    public static int reverse(int x) {
        if(x == 0) {
            return x;
        }
        int in = x, out = 0;
        int max = Integer.MAX_VALUE / 10 , maxDigit = Integer.MAX_VALUE % 10;
        int min = Integer.MIN_VALUE / 10, minDigit = Integer.MIN_VALUE % 10;
        while(in != 0) {
            int mod = in % 10;
            if(out > max || (out == max && mod > maxDigit ) ||
                    out < min || (out == min && mod < minDigit)) {
                return 0;
            }
            out = out * 10 + mod;
            in /= 10;
        }

        return out;
    }
}
