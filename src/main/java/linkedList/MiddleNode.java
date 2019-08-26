package linkedList;

/**
 * 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
 * <p>
 * 如果有两个中间结点，则返回第二个中间结点。
 * <p>
 * 示例 1：
 * <p>
 * 输入：[1,2,3,4,5]
 * <p>
 * 输出：此列表中的结点 3 (序列化形式：[3,4,5])
 * <p>
 * 返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
 * <p>
 * 注意，我们返回了一个 ListNode 类型的对象 ans，这样：
 * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
 * <p>
 * 示例 2：
 * <p>
 * 输入：[1,2,3,4,5,6]
 * <p>
 * 输出：此列表中的结点 4 (序列化形式：[4,5,6])
 * <p>
 * 由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
 *  
 * <p>
 * 提示：
 * <p>
 * 给定链表的结点数介于 1 和 100 之间。
 *
 * @author suchao
 * @link https://leetcode-cn.com/problems/middle-of-the-linked-list
 * @date 2019/6/24
 */
public class MiddleNode {

    /**
     * 先求长度，然后除2，得到要返回的位置
     * @param head 头结点
     * @return 中间节点
     */
    public static ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        int length = 0;
        ListNode first = head;
        while (first != null) {
            length++;
            first = first.next;
        }
        for (int i = 0; i < length / 2; i++) {
            head = head.next;
        }

        return head;
    }

    /**
     * 快慢指针法
     * @param head 头结点
     * @return 中间节点
     */
    public static ListNode middleNode2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode node = ListNode.getList(new int[]{1, 2, 3, 4, 5});
        ListNode middleNode = middleNode2(node);
        ListNode.printAllNode(middleNode);

        node = ListNode.getList(new int[]{1,2,3,4,5,6});
        middleNode = middleNode2(node);
        ListNode.printAllNode(middleNode);
    }
}
