package cn.julong.algorithm.other;

import java.util.HashMap;
import java.util.Map;

/**
 * 中文转阿拉伯数字
 */
public class Chiness2Number {

    public static void main(String[] args) {
        Chiness2Number d = new Chiness2Number();
        System.out.println(d.chinese2number("二千亿零一百零一万零二百"));
        System.out.println(d.chinese2number("十万"));
    }

    private static final Map<Character, Long> c2n = new HashMap<>();

    static {
        c2n.put('零', 0L);
        c2n.put('一', 1L);
        c2n.put('二', 2L);
        c2n.put('三', 3L);
        c2n.put('四', 4L);
        c2n.put('五', 5L);
        c2n.put('六', 6L);
        c2n.put('七', 7L);
        c2n.put('八', 8L);
        c2n.put('九', 9L);
        c2n.put('十', 10L);
        c2n.put('百', 100L);
        c2n.put('千', 1000L);
        c2n.put('万', 10000L);
        c2n.put('亿', 100000000L);
    }
    /**
     * 二千亿零一百零一万零二百: 200001010200
     * @return
     */
    Long chinese2number(String chineseNumber) {
        if (chineseNumber.trim().equals("")) {
            return 0L;
        }

        char[] cs = chineseNumber.toCharArray();
        int n = cs.length;

        long ans = 0;
        long tmp = 1;

        for(int i = n - 1; i >=0; i--) {
            // 中文字符
            char c = cs[i];
            // 阿拉伯数字
            Long num = c2n.get(c);

            // 如果是单位
            if(num >= 10) {
                // 单位大于临时结果，则临时结果 = 单位值
                // 单位小于临时结果，则临时结果 = 单位值 * 临时结果
                tmp = num > tmp ? num : num * tmp;
            } else {
                // 如果是数字，则结果 = 结果 + 则将临时结果 * 数字
                ans += tmp * num;
            }
        }
        // 注意这里，如果是纯单位，则结果存储在临时结果中
        return ans == 0 ? tmp : ans;
    }
}
