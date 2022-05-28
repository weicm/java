package cn.julong.algorithm.leetcode;

/**
 * 6. Z字形变换
 */
public class LC6ZigzagConversion {
    public static void main(String[] args) {
        String s = "PAYPALISHIRING"; // 结果：PAHNAPLSIIGYIR
        System.out.println(convertWithDirect(s, 3));
    }

    /**
     * 根据规律直接填充法
     * 思路：
     * 字符个数：n
     * 行数: r
     * 周期：t = 2 * r - 2
     *
     * 源字符：PAYPAL ISHIRI NG
     * 源索引：012345 678901 23
     * 行索引：012321 012321 01
     * 周索引：012345 012345 01
     *
     * 规律1：当 i % t < r 时，行索引 j = i % t
     * 规律2：当 i % t >= r 时，航索引 j = t - i % t
     *
     * @param s
     * @param numRows
     * @return
     */
    public static String convertWithDirect(String s, int numRows) {
        int n = s.length(), r = numRows, t = 2 * r - 2;
        if (r == 1 || n <= r) {
            return s;
        }

        String[] rows = new String[r];
        for (int i = 0; i < n; i++) {
            int mod = i % t;
            int j = mod < r ? mod : t - mod;
            String curStr = rows[j] == null ? "" : rows[j];
            rows[j] = curStr + s.charAt(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < r; i++) {
            sb.append(rows[i]);
        }
        return sb.toString();
    }
    /**
     * 暴力破解法
     *
     * @param s
     * @param numRows
     * @return
     */
    public static String convert(String s, int numRows) {
        if(s == null || s.length() <= numRows || numRows == 1) {
            return s;
        }
        int r = numRows;
        int n = s.length();
        int t = 2 * r - 2;
        int c = (n + t - 1) / t * (r - 1);
        char[][] mat = new char[r][c];
        for(int i = 0, x = 0, y = 0; i < n; i++) {
            mat[x][y] = s.charAt(i);
            if(i % t < r - 1) {
                x++;
            } else {
                x--;
                y++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                char cc = mat[i][j];
                if(cc != 0) {
                    sb.append(cc);
                }
            }
        }

        return sb.toString();
    }
}
