package cn.julong.algorithm.leetcode;

public class LC8 {
    public static void main(String[] args) {
        // System.out.println(myAtoi("42"));
        // System.out.println(myAtoi("-91283472332"));
        // System.out.println(myAtoi("   -42"));
        // System.out.println(myAtoi("3.14159"));
        // System.out.println(myAtoi("21474836460"));
        // System.out.println(myAtoi("  0000000000012345678"));
        // System.out.println(myAtoi("00000-42a1234"));
        System.out.println(myAtoi("-11919730356"));
    }

    public static int myAtoi(String s) {
        if(s == null || s.matches("[ ]*(-|\\+)?[^\\d]+.*]")) {
            return 0;
        }

        String p = "[ ]*((-|\\+)?\\d+).*";
        if (s.matches(p)) {
            String num = s.replaceAll(p, "$1");

            if (cmp(num, String.valueOf(Integer.MIN_VALUE)) < 0) {
                return Integer.MIN_VALUE;
            } else if (cmp(num, String.valueOf(Integer.MAX_VALUE)) > 0) {
                return Integer.MAX_VALUE;
            } else {
                return Integer.valueOf(num);
            }
        }
        return 0;
    }

    static Integer cmp(String s1, String s2) {
        boolean s1Neg = s1.startsWith("-");
        boolean s2Neg = s2.startsWith("-");

        String trimS1 = s1.replaceFirst("^(-|\\+)?0*", "");
        String trimS2 = s2.replaceFirst("^(-|\\+)?0*", "");

        int lenS2 = trimS2.length();
        int lenS1 = trimS1.length();

        if (s1Neg && !s2Neg) {
            return -1;
        } else if (!s1Neg && s2Neg) {
            return 1;
        } else if (s1Neg && s2Neg) {
            if (lenS1 < lenS2) {
                return 1;
            }
            if (lenS1 > lenS2) {
                return -1;
            }
            for (int i = 0; i < lenS1; i++) {
                if (trimS1.charAt(i) == trimS2.charAt(i)) {
                    continue;
                }

                if (trimS1.charAt(i) < trimS2.charAt(i)) {
                    return 1;
                } else {
                    return -1;
                }
            }
            return 0;
        } else {
            if (lenS1 < lenS2) {
                return -1;
            }
            if (lenS1 > lenS2) {
                return 1;
            }
            for (int i = 0; i < lenS1; i++) {
                if (trimS1.charAt(i) == trimS2.charAt(i)) {
                    continue;
                }

                if (trimS1.charAt(i) < trimS2.charAt(i)) {
                    return -1;
                } else {
                    return 1;
                }
            }
            return 0;
        }
    }
}
