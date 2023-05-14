package cn.julong.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合
 *
 * @author: weichangming@baidu.com
 * @date: 2023/5/13
 */
public class LC77 {
    public static void main(String[] args) {
        System.out.println(combine(3, 2));
    }

    static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList();
        List<Integer> paths = new ArrayList(k);
        boolean[] used = new boolean[n];
        dfs(n, k, 0, used, paths, result);
        return result;
    }

    static void dfs(int n, int k, int l, boolean[] used, List<Integer> paths, List<List<Integer>> result) {
        if(l == k) {
            result.add(new ArrayList(paths));
            return;
        }

        for(int i = 0; i < n; i++) {
            if(used[i] || (l > 0 && i < paths.get(l - 1))) {
                continue;
            }

            paths.add(i + 1);
            used[i] = true;
            dfs(n, k, l + 1, used, paths, result);
            paths.remove(l);
            used[i] = false;
        }

    }
}
