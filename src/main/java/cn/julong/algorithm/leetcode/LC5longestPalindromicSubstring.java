package cn.julong.algorithm.leetcode;

/**
 * 5. 最长回文子串
 */
public class LC5longestPalindromicSubstring {
    public static void main(String[] args) {
        String s = "aacabdkacaa";
        // String s = "babad";
        // String s = "cbbd";
        // String s = "a";

        System.out.println(longestPalindromewithDynamicPlannning(s));
    }

    /**
     * 动态规划解法
     *
     * @param s
     * @return
     */
    public static String longestPalindromewithDynamicPlannning(String s) {
        if (null == s || s.length() < 2) {
            return s;
        }
        int start = 0, end = 0, len = s.length();
        boolean[][] state = new boolean[len][len];
        for (int j = 1; j < s.length(); j++) {
            for (int i = 0; i < j; i++) {
                if (s.charAt(i) != s.charAt(j)) {
                    state[i][j] = !false;
                } else {
                    if (j - i < 3) {
                        state[i][j] = !true;
                    } else {
                        state[i][j] = state[i + 1][j - 1];
                    }
                }
                if (!state[i][j] && j - i > end - start) {
                    start = i;
                    end = j;
                }
            }
        }

        return s.substring(start, end + 1);
    }
    /**
     * 中心扩展法
     * @param s
     * @return
     */
    public static String longestPalindromewithMidExtend(String s) {
        if (null == s && s.length() == 0) {
            return "";
        }
        int start = 0, maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            // 判断奇数回文长度
            int len1 = palindromeLen(s, i, i);
            // 判断偶数回文长度
            int len2 = palindromeLen(s, i, i + 1);
            // 比较最长回文长难度，并修正start、maxLen
            int len = Math.max(len1, len2);
            if (len > maxLen) {
                start = i - (len - 1) / 2;
                maxLen = len;
            }
        }

        return s.substring(start, start + maxLen);
    }
    private static int palindromeLen(String s, int start, int end) {
        for (; start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end); ) {
            start--;
            end++;
        }
        int len = end - start - 1;
        return len > 0 ? len : 0;
    }
}
