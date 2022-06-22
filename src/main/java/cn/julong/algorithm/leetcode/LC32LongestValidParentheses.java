package cn.julong.algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 32. 最长有效括号
 */
public class LC32LongestValidParentheses {
    public static void main(String[] args) {

        // String s = "(()"; // output: 2
        String s = "()()"; // output: 4
        System.out.println(longestValidParentheses(s));
    }

    public static int longestValidParentheses(String s) {
        // return byDoubleLoop(s);
        // return byDynamicPlan(s);
        return byStack(s);
    }

    /**
     * 双向遍历法
     *
     * @param s
     * @return
     */
    public static int byDoubleLoop(String s) {
        int maxLen = 0, left = 0, right = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                int len = left + right;
                maxLen = len > maxLen ? len : maxLen;
            }
            if (left < right) {
                left = right = 0;
            }
        }

        left = right = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                int len = left + right;
                maxLen = len > maxLen ? len : maxLen;
            }
            if (left > right) {
                left = right = 0;
            }
        }

        return maxLen;
    }

    /**
     * 动态规划法
     *
     * @param s
     * @return
     */
    public static int byDynamicPlan(String s) {
        int n = s.length(), maxLen = 0;
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            if (i < 1) {
                continue;
            }
            char c = s.charAt(i);
            int leftIndex = i - dp[i - 1] - 1;
            if (c == ')' && leftIndex >= 0 && s.charAt(leftIndex) == '(') {
                int outLastIndex = i - dp[i - 1] - 2;
                int outDp = outLastIndex >= 0 ? dp[outLastIndex] : 0;
                dp[i] = 2 + dp[i - 1] + outDp;
                maxLen = Math.max(dp[i], maxLen);
            }
        }
        return maxLen;
    }

    public static int byStack(String s) {
        int n = s.length(), maxLen = 0;
        Deque<Integer> stack = new ArrayDeque<>(n);
        stack.push(-1);

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if(c == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }
        return maxLen;
    }
}
