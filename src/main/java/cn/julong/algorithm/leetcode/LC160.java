package cn.julong.algorithm.leetcode;

import cn.julong.algorithm.common.ListNode;
import cn.julong.algorithm.common.ListUtil;

import java.util.HashSet;

public class LC160 {
    public static void main(String[] args) {
        ListNode left = ListUtil.build(new Integer[]{4, 1, 8});
        ListNode right = ListUtil.build(new Integer[]{5, 6, 1, 8});
        ListNode pub = ListUtil.build(new Integer[]{4, 5});
        // ListNode left = ListUtil.build(new Integer[]{2, 6, 4});
        // ListNode right = ListUtil.build(new Integer[]{1, 5});
        // ListNode pub = ListUtil.build(new Integer[]{});
        ListNode l = ListUtil.link(left, pub);
        ListNode r = ListUtil.link(right, pub);
        ListUtil.print(l);
        ListUtil.print(r);

        ListNode result = getIntersectionNode(l, r);

        ListUtil.print(result);
    }
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // return violence(headA, headB);
        return rotate(headA, headB);
    }

    private static ListNode violence(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        HashSet<ListNode> ns = new HashSet<ListNode>();
        ListNode pa = headA, pb = headB;
        while (pa != null) {
            ns.add(pa);
            pa = pa.next;
        }

        while (pb != null) {
            if (ns.contains(pb)) {
                return pb;
            }
            pb = pb.next;
        }
        return null;
    }

    private static ListNode rotate(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pa = headA, pb = headB;
        while (pa != pb) {
            pa = pa == null ? headB : pa.next;
            pb = pb == null ? headA : pb.next;
        }
        return pa;
    }
}
