package linkedList;

import java.util.Objects;

/**
 * 链表节点
 *
 * @author suchao
 * @date 2018/10/12
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    public static ListNode getList(int index) {

        if (index <= 0) {
            return null;
        }

        ListNode head = new ListNode(0);
        ListNode result = head;

        for (int i = 1; i < index; i++) {
            head.next = new ListNode(i);
            head = head.next;
        }
        return result;
    }

    public static ListNode getList(int[] arrays) {

        Objects.requireNonNull(arrays);

        ListNode head = new ListNode(arrays[0]);
        ListNode result = head;

        for (int i = 1; i < arrays.length; i++) {
            head.next = new ListNode(arrays[i]);
            head = head.next;
        }
        return result;
    }

    public static void printAllNode(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
}
