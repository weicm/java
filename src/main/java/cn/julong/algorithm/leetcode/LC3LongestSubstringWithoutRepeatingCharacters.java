package cn.julong.algorithm.leetcode;

import java.util.HashMap;

/**
 * 3. 无重复字符的最长子串
 */
public class LC3LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        String s = "abcabcbb";
        // String s = "aab";
        // String s = "";

        System.out.println(llsWithArray(s));
    }

    public static int llsWithArray(String s) {
        int maxLen = 0;
        Integer[] c2i = new Integer[26];
        // i: 遍历指针
        // p: 不重复字符串开始指针
        for(int i = 0, p = 0; i < s.length(); i++) {
            // 获取当前位置字符
            char c = s.charAt(i);
            // 获取字符的已存在索引
            Integer ci = c2i[c - 97];
            // 记录当前字符索引
            c2i[c - 97] = i;
            // 如果不存在索引，或者索引位置小于不重复字符串，则表示当前字符没有重复
            if (ci == null || ci < p) {
                // 不重复字符个数大于最长不重复长度，则更新最长不重复字符长度
                if (i - p + 1 > maxLen) {
                    maxLen = i - p + 1;
                }
                continue;
            }
            // 如果当前字符已存在，则将不重复开始指针移动到当前字符已存在索引的下一位
            p = ci + 1;
        }
        return maxLen;
    }
    public static int llsWithHashMap(String s) {
        int maxLen = 0;
        HashMap<Character, Integer> c2i = new HashMap<>();
        // i: 遍历指针
        // p: 不重复字符串开始指针
        for(int i = 0, p = 0; i < s.length(); i++) {
            // 获取当前位置字符
            char c = s.charAt(i);
            // 获取字符的已存在索引
            Integer ci = c2i.get(c);
            // 记录当前字符索引
            c2i.put(c, i);
            // 如果不存在索引，或者索引位置小于不重复字符串，则表示当前字符没有重复
            if (ci == null || ci < p) {
                // 不重复字符个数大于最长不重复长度，则更新最长不重复字符长度
                if (i - p + 1 > maxLen) {
                    maxLen = i - p + 1;
                }
                continue;
            }
            // 如果当前字符已存在，则将不重复开始指针移动到当前字符已存在索引的下一位
            p = ci + 1;
        }
        return maxLen;
    }
    /**
     * 暴力破解法
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        int maxLen = s.equals("") ? 0 : 1;
        for (int i = 0; i < s.length() - 1;) {
            int j = i + 1;
            for(; j < s.length(); j++) {
                int p = j - 1;
                while(p >= i && s.charAt(j) != s.charAt(p)) {
                    p--;
                }
                if(p < i && j - p > maxLen) {
                    maxLen = j - p;
                } else if(p < i && j - p <= maxLen) {
                    continue;
                } else {
                    i = p + 1;
                    break;
                }
            }

            if (j == s.length()) {
                break;
            }

        }
        return maxLen;
    }
}
