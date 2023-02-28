package cn.julong.algorithm.leetcode;

import java.util.LinkedList;

public class LC9 {
    public static void main(String[] args) {
        System.out.println(isPalindrome(8));
    }

    public static boolean isPalindrome(int x) {
        // return violence(x);
        return magic(x);
    }

    private static boolean violence(int x) {
        if (x < 0) {
            return false;
        }

        LinkedList<Integer> list = new LinkedList<>();
        int tmp = x;
        while (tmp != 0) {
            list.add(tmp % 10);
            tmp = tmp / 10;
        }

        while (!list.isEmpty()) {
            Integer f = list.getFirst();
            Integer l = list.removeLast();
            if (!f.equals(l)) {
                return false;
            }
            if (!list.isEmpty()) {
                list.removeFirst();
            }
        }
        return true;
    }

    private static boolean magic(int x) {
        if (x < 0 || (x % 10 == 0 && x / 10 > 0)) {
            return false;
        }
        int reverse = 0;
        while (x > reverse) {
            reverse = reverse * 10 + x % 10;
            x /= 10;
        }

        return x == reverse || x == reverse / 10;
    }
}
