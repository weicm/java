package cn.julong.algorithm.leetcode;

import cn.julong.algorithm.common.ListNode;
import cn.julong.algorithm.common.ListUtil;

public class LC234 {
    public static void main(String[] args) {
        // ListNode head = ListUtil.build(new Integer[]{1, 2, 2, 1});
        // ListNode head = ListUtil.build(new Integer[]{1, 2, 3, 2, 1});
        ListNode head = ListUtil.build(new Integer[]{1});
        System.out.println(isPalindrome(head));
    }

    public static boolean isPalindrome(ListNode head) {
        return recursive(head, head) != null;
    }

    /**
     * 递归求回文尾结点
     * @param head 头结点
     * @param n 当前节点
     * @return 是回文：尾结点，否则：null
     */
    static ListNode recursive(ListNode head, ListNode n) {
        // 递归出口（尾结点）
        if (n.next == null) {
            if (head == n) {
                return head;
            }
            return head.val == n.val ? head.next : null;
        }

        ListNode first = recursive(head, n.next);

        // 非回文，直接返回null
        if (first == null) {
            return null;
        }
        // 回文，且回溯到了尾结点
        if (first.next == null) {
            return first;
        }
        // 中间结果，继续回溯
        return first.val == n.val ? first.next : null;
    }
}
