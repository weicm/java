package cn.julong.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 找到字符串中所有字母异位词
 *
 * @author: weichangming@baidu.com
 * @date: 2023/6/16
 */
public class LC438 {
    public static void main(String[] args) {
        LC438 m = new LC438();
        String s = "abab";
        String p = "ab";
        System.out.println(m.findAnagrams(s, p));
    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList();

        int sn = s.length();
        int pn = p.length();

        if(sn < pn) {
            return ans;
        }

        char[] sa = s.toCharArray();
        char[] pa = p.toCharArray();

        // 词频
        int[] sc = new int[26];
        int[] pc = new int[26];

        // 计算p字符串的词频
        for(int i = 0; i < pn; i++) {
            int pi = pa[i] - 'a';
            pc[pi]++;
        }

        // 窗口和p的距离
        int dist = 0;

        // i：起始，j：结尾
        for(int i = 0, j = 0; j < sn; j++) {
            // 计算开头结尾坐标
            int si = sa[i] - 'a', sj = sa[j] - 'a';

            // 结尾向后滑动一个字符，计算距离：窗口词频 < 字符在p中的词频，则距离+1
            dist = sc[sj] < pc[sj] ? dist + 1 : dist;

            // 窗口小于p长度是，只移动结尾指针
            if(j - i + 1 < pn) {
                sc[sj]++;
                continue;
            }

            // 移动完结尾指针，结尾字符词频+1
            sc[sj]++;

            // 判断距离是否和p的长度一致，一致则即为目标
            if(dist == pn) {
                ans.add(i);
            }

            // 起始指针右移，指针对应字符词频-1
            i++;
            sc[si]--;
            // 起始指针右移后，如果起始字符词频 <= 字符在p中的词频，则距离-1
            dist = sc[si] <= pc[si] ? dist - 1 : dist;
        }
        return ans;
    }
}
