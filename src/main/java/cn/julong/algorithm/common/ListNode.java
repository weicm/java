package cn.julong.algorithm.common;

import java.util.ArrayList;
import java.util.List;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static void print(ListNode ln) {
        List<Integer> res = new ArrayList<>();
        while (ln != null) {
            res.add(ln.val);
            ln = ln.next;
        }
        System.out.println(res);
    }

    public static ListNode build(int[] nums) {
        return build(nums, 0);
    }

    public static ListNode build(int[] nums, int i) {
        if (i == nums.length) {
            return null;
        }
        return new ListNode(nums[i], build(nums, i + 1));
    }
}