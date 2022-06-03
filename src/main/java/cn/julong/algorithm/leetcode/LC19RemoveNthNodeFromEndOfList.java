package cn.julong.algorithm.leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class LC19RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {

        ListNode head = buildList(new int[]{1, 2, 3, 4, 5}, 0);
        // ListNode startNode = removeNthFromEnd(head, 2);
        ListNode startNode = removeNthFromEndByStack(head, 2);
        printListNode(startNode);
    }

    private static void printListNode(ListNode ln) {
        while (ln != null) {
            System.out.print(ln.val + ", ");
            ln = ln.next;
        }
    }
    private static ListNode buildList(int[] nums, int i) {
        if (i == nums.length) {
            return null;
        }
        return new ListNode(nums[i], buildList(nums, i + 1));
    }
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }


    /**
     * 递归+哑结点
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode startNode = new ListNode(0, head);
        localRecursive(head, startNode, startNode, n);
        return startNode.next;
    }

    public static void localRecursive(ListNode cur, ListNode pre, ListNode startNode, int n) {
        if (cur == null) {
            startNode.val = -1;
            return;
        }
        localRecursive(cur.next, cur, startNode, n);

        if(startNode.val == -n) {
            pre.next = cur.next;
        }
        startNode.val -= 1;
    }

    /**
     * 栈+哑结点
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEndByStack(ListNode head, int n) {
        ListNode startNode = new ListNode(0, head), p = startNode;
        Deque<ListNode> stack = new LinkedList<>();
        while (p != null) {
            stack.push(p);
            p = p.next;
        }
        ListNode removeNode = null;
        for (int i = 0; i < n; i++) {
            removeNode = stack.pop();
        }

        stack.peek().next = removeNode.next;

        return startNode.next;
    }
}
