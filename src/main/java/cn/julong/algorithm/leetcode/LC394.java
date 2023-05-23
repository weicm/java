package cn.julong.algorithm.leetcode;

import java.util.Stack;

/**
 * 字符串解码
 *
 * @author: weichangming@baidu.com
 * @date: 2023/5/23
 */
public class LC394 {
    public static void main(String[] args) {
        // String s = "3[a]2[bc]";
        // String s = "3[a2[c]]";
        // String s = "2[abc]3[cd]ef";
        String s = "abc3[cd]xyz";
        // String s = "3[z]2[2[y]pq4[2[jk]e1[f]]]ef";
        System.out.println(decodeString(s));
    }

    static public String decodeString(String s) {
        int n = s.length();
        int i = 0;
        String ans = "";
        while(i < n) {
            int sei = subEndIndex(s, i);
            ans += dfsParse(s.substring(i, sei));
            i = sei;
        }
        return ans;
    }

    static Integer subEndIndex(String s, int start) {
        String subStr = s.substring(start);
        String sp = abcPrefix(subStr);
        if(!sp.equals("")) {
            return start + sp.length();
        }

        String dp = digitPrefix(subStr);

        int i = start + dp.length() + 1;
        if(i > s.length()) {
            return i;
        }
        Stack<Character> stack = new Stack();
        stack.push(s.charAt(i));
        while(i < s.length() && !stack.isEmpty()) {
            char c = s.charAt(i++);
            if(c != '[' && c != ']') {
                continue;
            }
            if(c == '[') {
                stack.push(c);
            } else {
                stack.pop();
            }
        }
        return i;
    }
    static String dfsParse(String s) {
        String sp = abcPrefix(s);
        if(sp.equals(s)) {
            return s;
        }
        String dp = digitPrefix(s.substring(sp.length()));
        int count = Integer.valueOf(dp);

        String subInput = s.substring(sp.length() + dp.length() + 1, s.length() - 1);
        String str = decodeString(subInput);
        return sp + repeatStr(str, count);
    }

    static String repeatStr(String str, int count) {
        String rs = "";
        for(int i = 0; i < count; i++) {
            rs += str;
        }
        return rs;
    }
    static String subStr(String s, int start, int end) {
        return s.substring(start + 1, end - start);
    }
    static String digitPrefix(String s) {
        int i = 0;
        while (i < s.length() && isDigit(s, i)) i++;
        return s.substring(0, i);
    }
    static String abcPrefix(String s) {
        int i = 0;
        while (i < s.length() && isChar(s, i)) i++;
        return s.substring(0, i);
    }

    static Boolean isChar(String s, int i) {
        return s.charAt(i) >= 'a' && s.charAt(i) <= 'z';
    }

    static Boolean isDigit(String s, int i) {
        return s.charAt(i) >= '0' && s.charAt(i) <= '9';
    }
}
