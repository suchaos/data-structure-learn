package linkedList;

/**
 * leetcode : 24. 两两交换链表中的节点
 *
 * @author suchao
 * @link https://leetcode-cn.com/problems/swap-nodes-in-pairs/description/
 * @date 2018/10/12
 *
 *
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 示例:
 *      给定 1->2->3->4, 你应该返回 2->1->4->3.
 *
 * 说明:
 *      你的算法只能使用常数的额外空间。
 *      你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 */
public class SwapNodesInPairs {
    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode res = head.next;

        ListNode cur = head;
        ListNode pre = null;
        ListNode next;

        while (cur != null && cur.next != null) {
            next = cur.next;
            cur.next = next.next;
            next.next = cur;
            if (pre != null) {
                pre.next = next;
            }
            pre = cur;
            cur = cur.next;
        }

        return res;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.getList(5);
        ListNode.printAllNode(head);
        ListNode node = swapPairs(head);
        ListNode.printAllNode(node);
    }
}
