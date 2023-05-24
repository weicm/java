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
        LC394 driver = new LC394();
        // String s = "3[a]2[bc]";
        // String s = "3[a2[c]]";
        // String s = "2[abc]3[cd]ef";
        // String s = "abc3[cd]xyz";
        // String s = "3[z]2[2[y]pq4[2[jk]e1[f]]]ef"; // zzzyypqjkjkefjkjkefjkjkefjkjkefyypqjkjkefjkjkefjkjkefjkjkefef
        String s = "2[2[y]pq4[2[jk]e1[f]]]"; // zzzyypqjkjkefjkjkefjkjkefjkjkefyypqjkjkefjkjkefjkjkefjkjkefef
        System.out.println(driver.decodeString(s));
    }

    public String decodeString(String s) {
        int n = s.length();
        String ans = "";
        // 字符栈：a-z + [
        // 数字栈：0-9 + [
        Stack<Character> abcStack = new Stack();
        Stack<Character> numStack = new Stack();
        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if(c == ']') { // 右括号
                // 出栈连续数字
                int num = popNum(numStack);
                // 出栈连续字符
                String str = popStr(abcStack);
                // 重复字符串
                String rs = repeat(str, num);
                // 如果数字栈为空个，则说明没有嵌套数字，直接将重复字符串追加到结果上
                if(numStack.isEmpty()) {
                    ans += rs;
                } else {
                    // 数字栈不为空，说明还有嵌套数字，将重复字符串压入栈顶
                    for(int j = 0; j < rs.length(); j++) {
                        abcStack.push(rs.charAt(j));
                    }
                }
            } else if (c == '[') { // 左括号，需要在字符占和数字栈定均存入一份，作为嵌套的截至符
                numStack.push(c);
                abcStack.push(c);
            } else if(c >= '0' && c <= '9') { // 数字，直接入数字栈
                numStack.push(c);
            } if(c >= 'a' && c <= 'z') { // 字符，是否入栈需要判断是否需要重复
                if (!numStack.isEmpty()) { // 数字栈不为空，需要重复字符，所以字符入栈
                    abcStack.push(c);
                } else { // 数字栈为空，不需要重复字符，直接作为结果
                    ans += c;
                }
            }
        }

        return ans;
    }

    Integer popNum(Stack<Character> stack) {
        String s = "";
        // 弹出多余的左括号（左括号在数字的右侧，所有先于数字弹出）
        if(!stack.isEmpty()) {
            stack.pop();
        }
        while(!stack.isEmpty() && stack.peek() != '[') {
            s = stack.pop() + s;
        }

        return s.equals("") ? 0 : Integer.valueOf(s);
    }

    String popStr(Stack<Character> stack) {
        String s = "";
        while(!stack.isEmpty() && stack.peek() != '[') {
            s = stack.pop() + s;
        }
        // 弹出多余的左括号（左括号在字符有左侧，所有最后弹出）
        if(!stack.isEmpty()) {
            stack.pop();
        }
        return s;
    }
    String repeat(String str, int n) {
        String s = "";
        for(int i = 0; i < n; i++) {
            s += str;
        }
        return s;
    }
}
