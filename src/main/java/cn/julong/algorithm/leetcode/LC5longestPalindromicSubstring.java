package cn.julong.algorithm.leetcode;

/**
 * 5. 最长回文子串
 */
public class LC5longestPalindromicSubstring {
    public static void main(String[] args) {
        String s = "babad";
        // String s = "babad";
        // String s = "cbbd";
        // String s = "a";

        System.out.println(longestPalindrome(s));
    }

    static String longestPalindrome(String s) {
        int maxLen = 0, start = 0, end = 0;
        char[] ss = fenceStr(s, '#');
        for(int i = 0; i < ss.length; i++) {

            int len = (expandCenter(ss, i, i) - 1) / 2;

            if(len > maxLen) {
                maxLen = len;
                int realIndex = (i - 1) / 2;
                start = realIndex - (len - 1) / 2;
                end = realIndex + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    static char[] fenceStr(String s, char sep) {
        int n = s.length();
        char[] ss = new char[2 * n + 1];
        for(int i = 0; i < ss.length; i++) {
            ss[i] = i % 2 == 0 ? sep : s.charAt((i - 1) / 2);
        }
        return ss;
    }

    static int expandCenter(char[] ss, int l,  int r) {
        int n = ss.length;
        while(l >= 0 && r < n && ss[l] == ss[r]) {
            l--;
            r++;
        }

        return r - l - 1;
    }
}
