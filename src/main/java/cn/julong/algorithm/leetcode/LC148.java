package cn.julong.algorithm.leetcode;

import cn.julong.algorithm.common.ListNode;
import cn.julong.algorithm.common.ListUtil;

/**
 * 排序链表
 *
 * @author: weichangming@baidu.com
 * @date: 2023/5/16
 */
public class LC148 {
    public static void main(String[] args) {
        ListNode head = ListUtil.build(new Integer[]{4, 2, 1, 3});

        ListNode h = sortList(head);

        ListUtil.print(h);
    }

    public static ListNode sortList(ListNode head) {
        if(head == null) {
            return null;
        }

        return dfs(head);
    }

    static ListNode dfs(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        // 寻找中点：即左边链表的尾结点 tail1
        ListNode slow = head, fast = head.next;

        // 由于fast指针会跳跃两步，所以fast可能为null，fast.next也可能为null
        // 有一个为null，就到达当前链表的尾部了，slow指针此时就是中点（tail1）
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next == null ? null : fast.next.next;
        }

        // slow 就是左边链表的尾结点
        ListNode tail1 = slow;

        // tail1的下一个节点就是右边链表的头结点
        ListNode head2 = tail1.next;

        // 断开两个链表
        tail1.next = null;

        // 递归排序左右链表
        ListNode h1 = dfs(head);
        ListNode h2 = dfs(head2);

        // 将h1、h2两个有序链表合并为大的有序链表
        return merge(h1, h2);
    }

    static ListNode merge(ListNode ln, ListNode rn) {
        ListNode h = new ListNode();
        ListNode p = h;
        while(ln != null && rn != null) {
            if(ln.val < rn.val) {
                p.next = ln;
                ln = ln.next;
            } else {
                p.next = rn;
                rn = rn.next;
            }

            p = p.next;
        }

        if(ln != null) {
            p.next = ln;
        }

        if(rn != null) {
            p.next = rn;
        }

        return h.next;
    }
}
