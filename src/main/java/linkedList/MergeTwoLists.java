package linkedList;

/**
 * 21. 合并两个有序链表
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 * <p>
 * 示例：
 * <p>
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 * @author suchao
 * @link https://leetcode-cn.com/problems/merge-two-sorted-lists
 * @date 2019/6/24
 */
public class MergeTwoLists {
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }

        ListNode result;
        ListNode head;

        if (l1.val > l2.val) {
            head = l2;
            l2 = l2.next;
        } else {
            head = l1;
            l1 = l1.next;
        }
        result = head;

        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                head.next = l2;
                l2 = l2.next;
            } else {
                head.next = l1;
                l1 = l1.next;
            }
            head = head.next;
        }

        if (l1 == null) {
            head.next = l2;
        } else {
            head.next = l1;
        }

        return result;
    }

    public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {

        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }

        // 设定一个哨兵结点，两个好处：
        // 1. 返回值比较好弄
        // 2. 不必对头结点做特殊处理（结合上面那个方法看）
        ListNode preHead = new ListNode(-1);
        ListNode head = preHead;

        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                head.next = l2;
                l2 = l2.next;
            } else {
                head.next = l1;
                l1 = l1.next;
            }
            head = head.next;
        }

        if (l1 == null) {
            head.next = l2;
        } else {
            head.next = l1;
        }

        return preHead.next;
    }

    // 递归版本 TODO
    public static ListNode mergeTwoLists3(ListNode l1, ListNode l2) {
        return null;
    }

    public static void main(String[] args) {
        ListNode node1 = ListNode.getList(new int[]{1, 2, 4});
        ListNode node2 = ListNode.getList(new int[]{1, 3, 4});
        // ListNode node2 = null;
        ListNode node = mergeTwoLists2(node1, node2);
        ListNode.printAllNode(node);
    }
}
