package cn.julong.algorithm.search;

import java.util.Arrays;
import java.util.stream.Collectors;

public class KmsSearch {
    public static void main(String[] args) {

        String str1 = "XyzABACABABhhhxixixi";
        String str2 = "XyzhhhxixixiABACABAB";
        String str3 = "ABACABABXyzhhhxixixi";
        String str4 = "Xyzhhhxixixi";

        String subStr = "ABACABAB";

        Integer index1 = kmpSearch(str1, subStr);
        Integer index2 = kmpSearch(str2, subStr);
        Integer index3 = kmpSearch(str3, subStr);
        Integer index4 = kmpSearch(str4, subStr);

        System.out.println(index1);
        System.out.println(index2);
        System.out.println(index3);
        System.out.println(index4);
    }


    private static Integer kmpSearch(String str, String subStr) {
        int[] next = next(subStr);
        System.out.println(Arrays.stream(next).boxed().collect(Collectors.toList()));

        int i = 0;
        int j = 0;

        while (i < str.length()) {
            if(str.charAt(i) == subStr.charAt(j)) {
                // 如果当前字符匹配，则判断是否到达子串结尾，到达结尾则说明找到了子串，直接返匹配字符串开始索引
                if (j == next.length - 1) {
                    return i - j;
                }
                // 没有到达子串结尾，则继续匹配下一个字符
                i++;
                j++;
            } else if(j > 0) {
                // 如果当前字符不匹配，且最长匹配前缀索引不为0，则查询前一个字符的最长匹配前缀的前一个较短匹配前缀索引
                j = next[j - 1];
            } else {
                // 如果当前字符不匹配，且最长匹配前缀索引为0，则跳过当前字符
                i++;
            }
        }

        return -1;
    }


    /**
     * 构建最长匹配前缀数组：每一个元素值代表以第i个字符结尾的子串和字符串前缀最长匹配长度，也代表最长前缀结尾索引
     * A B C C A B C B
     * 0 0 0 0 1 2 3 0
     * A B A C A B A B
     * 0 0 1 0 1 2 3 2
     * @param subStr
     * @return
     */
    private static int[] next(String subStr) {
        char[] charArr = subStr.toCharArray();
        int[] next = new int[charArr.length];

        // 第0个元素为0，前缀出事长度为0，从第二个元素开始算
        int prefixLen = 0;
        int i = 1;
        while(i < charArr.length) {

            if (charArr[i] == charArr[prefixLen]) {
                // 当前元素和最长前缀下一个元素相等，长度加一，继续匹配下一个元素
                next[i++] = ++prefixLen;
            } if (prefixLen == 0) {
                // 如果不存在最长匹配前缀，则跳过当前元素
                i++;
            } else {
                // 如果当前元素和最长前缀下一个元素不等，则从当前元素的前一个最长前缀中找前一个最长匹配前缀，并从下一个元素继续匹配，
                // 目的是为了找是否存在更短的最长匹配前缀，如果找不到则重复找较短最长匹配前缀
                prefixLen = next[prefixLen - 1];
            }
        }
        return next;
    }
}
