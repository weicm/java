package cn.julong.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC336 {
    public static void main(String[] args) {
        String[] words = {"abcd", "dcba", "lls", "s", "sssll"};
        System.out.println(palindromePairs(words));
    }

    public static List<List<Integer>> palindromePairs(String[] words) {
        return dictTree(words);
        // return violence(words);
    }

    private static List<List<Integer>> dictTree(String[] words) {
        Node root = new Node();
        int n = words.length;
        // 字典树的插入，注意维护每个节点上的两个列表
        for (int i = 0; i < n; i++) {
            String rev = new StringBuilder(words[i]).reverse().toString();
            Node cur = root;

            if (isPalindrome(rev.substring(0)))
                cur.suffixs.add(i);
            for (int j = 0; j < rev.length(); j++) {
                char ch = rev.charAt(j);

                if (cur.children[ch - 'a'] == null)
                    cur.children[ch - 'a'] = new Node();

                cur = cur.children[ch - 'a'];

                if (isPalindrome(rev.substring(j + 1)))
                    cur.suffixs.add(i);
            }
            cur.words.add(i);
        }
        // 用以存放答案的列表
        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String word = words[i];
            Node cur = root;
            int j = 0;
            for (; j < word.length(); j++) {
                // 到j位置，后续字符串若是回文对，则在该节点位置上所有单词都可以与words[i]构成回文对
                // 因为我们插入的时候是用每个单词的逆序插入的:)
                if (isPalindrome(word.substring(j)))
                    for (int k : cur.words)
                        if (k != i) ans.add(Arrays.asList(i, k));

                char ch = word.charAt(j);
                if (cur.children[ch - 'a'] == null)
                    break;
                cur = cur.children[ch - 'a'];

            }
            // words[i]遍历完了，现在找所有大于words[i]长度且符合要求的单词，suffixs列表就派上用场了:)
            if (j == word.length())
                for (int k : cur.suffixs)
                    if (k != i)
                        ans.add(Arrays.asList(i, k));

        }
        return ans;
    }

    private static List<List<Integer>> violence(String[] words) {
        List<List<Integer>> r = new ArrayList<List<Integer>>();
        if (words.length == 1) {
            return r;
        }
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (i == j) {
                    continue;
                }
                if (isPalindrome(words[i] + words[j])) {
                    r.add(Arrays.asList(i, j));
                }
            }
        }
        return r;
    }

    //  判断一个字符串是否是回文字符串
    private static boolean isPalindrome(String w) {
        int i = 0, j = w.length() - 1;
        while (i < j) {
            if (w.charAt(i) != w.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }

    static class Node {
        public Node[] children;
        /**
         * 存储以当前节点为终止节点的所有单词（逆序之后的)，来获取所有第1种情况下的匹配成功的字符串 y
         */
        public List<Integer> words;

        /**
         * 保存所有到当前节点为止，其之后剩余字符可以构成回文对的单词。
         * 用来获取所有长度大于 x.length() 且可以和其构成回文对的单词 y（即针对第二种情况）
         */
        public List<Integer> suffixs;

        public Node() {
            this.children = new Node[26];
            this.words = new ArrayList<>();
            this.suffixs = new ArrayList<>();
        }
    }

}
