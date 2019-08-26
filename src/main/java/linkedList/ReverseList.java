package linkedList;

import static linkedList.ListNode.printAllNode;

/**
 * 206. 反转链表
 *
 * @author suchao
 * @link https://leetcode-cn.com/problems/reverse-linked-list/description/
 * @date 2018/10/12
 */
public class ReverseList {

    public static ListNode reverseList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode cur = head;
        ListNode pre = null;
        ListNode next;

        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

    /**
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     * @param args 测试
     */
    public static void main(String[] args) {
        test(15);
    }

    private static void test(int number) {
        for (int i = 0; i < number; i++) {
            ListNode head = ListNode.getList(i);
            printAllNode(head);
            ListNode list = reverseList(head);
            printAllNode(list);
            System.out.println("-------------");
        }
    }
}
