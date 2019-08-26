package linkedList;

import java.util.Objects;

/**
 * 141. 环形链表
 * 给定一个链表，判断链表中是否有环。
 * <p>
 *
 * @author suchao
 * @link https://leetcode-cn.com/problems/linked-list-cycle/
 * @date 2019/6/24
 */
public class HasCycle {
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    // [-21,10,17,8,4,26,5,35,33,-7,-16,27,-12,6,29,-12,5,9,20,14,14,2,13,-24,21,23,-21,5]
    //-1
    public static void main(String[] args) {
        int[] arrays = {-21, 10, 17, 8, 4, 26, 5, 35, 33, -7, -16, 27, -12, 6, 29, -12, 5,
                9, 20, 14, 14, 2, 13, -24, 21, 23, -21, 5};
        ListNode node = ListNode.getList(arrays);
        ListNode.printAllNode(node);
        System.out.println("-----------");
        System.out.println(hasCycle(node));
    }
}
