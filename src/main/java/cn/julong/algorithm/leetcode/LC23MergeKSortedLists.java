package cn.julong.algorithm.leetcode;

import cn.julong.algorithm.leetcode.repo.ListNode;

/**
 * 23. 合并K个升序链表
 */
public class LC23MergeKSortedLists {
    public static void main(String[] args) {
        // in: [1, 4, 5],[1, 3, 4],[2, 6]
        // out: [1, 1, 2, 3, 4, 4, 5, 6]
        ListNode[] lists = new ListNode[]{
                ListNode.build(new int[]{1, 4, 5}),
                ListNode.build(new int[]{1, 3, 4}),
                ListNode.build(new int[]{2, 6}),
        };

        // ListNode.print(mergeKLists(lists));
        ListNode.print(mergeKListsByRecursive(lists));
    }

    /**
     * 暴力解法
     * @param lists
     * @return
     */
    private static ListNode mergeKLists(ListNode[] lists) {
        ListNode fn = new ListNode(Integer.MIN_VALUE), pre = fn;
        int n = lists.length;
        while(true) {
            int i = 0;
            ListNode cur = null;
            for(; i < n && lists[i] == null; i++);
            if (i == n) {
                break;
            }
            cur = lists[i];

            int k = i, j = i + 1;
            for(; j < n; j++) {
                ListNode node = lists[j];
                if(node == null) {
                    continue;
                }
                if(node.val < cur.val) {
                    k = j;
                    cur = node;
                }
            }
            lists[k] = cur.next;
            pre.next = cur;
            pre = cur;
        }
        return fn.next;
    }

    /**
     * 递归解法（分治解法）
     * 1. 每次将左右有序链表做归并，最终结果存到左节点上
     * 2. 结果只会出现在偶数索引出，最终结果就是list[0]
     * @param lists
     * @return
     */
    private static ListNode mergeKListsByRecursive(ListNode[] lists) {
        if (null == lists || lists.length == 0) {
            return null;
        }

        localRecursive(lists, 0, lists.length - 1);

        return lists[0];
    }

    private static void localRecursive(ListNode[] lists, int start, int end) {
        if(start >= end) {
            return;
        }

        int mid = (start + end) / 2;

        localRecursive(lists, start, mid);
        localRecursive(lists, mid + 1, end);

        // 归并合并左右有序链表
        ListNode l = lists[start], r = lists[mid + 1], head = new ListNode(), pre = head;
        while (l != null || r != null) {
            if (l != null && r != null && l.val < r.val) {
                pre.next = l;
                pre = l;
                l = l.next;
            } else if (l != null && r != null && l.val >= r.val) {
                pre.next = r;
                pre = r;
                r = r.next;
            } else if (l == null && r != null) {
                pre.next = r;
                pre = r;
                r = r.next;
            } else {
                pre.next = l;
                pre = l;
                l = l.next;
            }
        }
        // 结果合并到左节点位置
        lists[start] = head.next;
    }
}
