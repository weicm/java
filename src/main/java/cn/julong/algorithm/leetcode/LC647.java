package cn.julong.algorithm.leetcode;

public class LC647 {
    public static void main(String[] args) {
        System.out.println(countSubstrings("aaa"));
    }
    public static int countSubstrings(String s) {
        // return violence(s);
        return dynamicPlan(s);
    }

    private static int violence(String s) {
        if (s.length() == 1) {
            return 1;
        }
        int len = s.length();
        int ans = 0;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j <= len - i; j++) {
                if (check(s.substring(j, j + i))) {
                    ans++;
                }
            }
        }
        return ans;
    }

    private static int dynamicPlan(String s) {
        int len = s.length(), ans = 0;
        int[][] dp = new int[len][len];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i <= 1 || dp[i + 1][j - 1] == 1) {
                        dp[i][j] = 1;
                        ans++;
                    }
                }
            }
        }
        return ans;
    }
    static boolean check(String s) {
        for (int i = 0, j = s.length() - 1; i <= j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}
