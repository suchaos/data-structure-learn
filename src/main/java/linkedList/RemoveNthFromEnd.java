package linkedList;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * <p>
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * <p>
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 * 给定的 n 保证是有效的。
 * <p>
 * 进阶：
 * 你能尝试使用一趟扫描实现吗？
 *
 * @author suchao
 * @link https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
 * @date 2019/6/24
 */
public class RemoveNthFromEnd {

    /**
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点
     *
     * @param head 头结点
     * @param n    倒数第 n 个节点
     * @return 返回链表的头结点
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        // 设置一个哨兵结点，
        // 这样对插入第一个结点和删除最后一个结点的情况（这个是指链表只剩一个结点的情况）
        // 也不用做进行特殊处理
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int length = 0;
        ListNode first = head;
        while (first != null) {
            length++;
            first = first.next;
        }

        length = length - n;
        first = dummy;

        for (int index = 0; index < length; index++) {
            first = first.next;
        }
        first.next = first.next.next;

        return dummy.next;
    }


    /**
     * 一次遍历方法
     * <p>
     * 我们可以使用两个指针而不是一个指针。
     * 第一个指针从列表的开头向前移动 n+1 步，而第二个指针将从列表的开头出发。
     * 现在，这两个指针被 n 个结点分开。我们通过同时移动两个指针向前来保持这个恒定的间隔，直到第一个指针到达null。
     * 此时第二个指针将指向从最后一个结点数起的第 n 个结点。
     * 我们重新链接第二个指针所引用的结点的 next 指针指向该结点的下下个结点。
     *
     * @param head 头结点
     * @param n    倒数第 n 个节点
     * @return 返回链表的头结点
     */
    public static ListNode removeNthFromEnd2(ListNode head, int n) {
        // 设置一个哨兵结点，
        // 这样对插入第一个结点和删除最后一个结点的情况（这个是指链表只剩一个结点的情况）
        // 也不用做进行特殊处理
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode first = dummy;
        ListNode second = dummy;
        // 第一个指针从列表的开头向前移动 n+1 步
        for (int i = 0; i <= n; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode node = ListNode.getList(5);
        ListNode removeNthFromEnd = removeNthFromEnd2(node, 2);
        ListNode.printAllNode(removeNthFromEnd);
    }
}
