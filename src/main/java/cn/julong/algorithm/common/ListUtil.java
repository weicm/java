package cn.julong.algorithm.common;

import java.util.ArrayList;

public class ListUtil {
    public static ListNode build(Integer[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        ListNode head = new ListNode(arr[0]), p = head;
        for (int i = 1; i < arr.length; i++) {
            p.next = new ListNode(arr[i]);
            p = p.next;
        }
        return head;
    }

    public static ListNode link(ListNode l, ListNode r) {
        if (l == null) {
            return r;
        }
        if (r == null) {
            return l;
        }
        ListNode h = l;
        while (h.next != null) {
            h = h.next;
        }
        h.next = r;
        return l;

    }

    public static void print(ListNode head) {
        ArrayList<Integer> r = new ArrayList<>();
        while (head != null) {
            r.add(head.val);
            head = head.next;
        }
        System.out.println(r);
    }
}
