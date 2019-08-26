package stack;

import java.util.Objects;

/**
 * 基于链表实现的栈，链式栈
 * <p>
 * push 直接将新增结点放到头结点
 * <p>
 * pop 直接将头结点删除即可
 *
 * @author suchao
 * @date 2019/6/26
 */
public class StackBaseOnLinkedList<T> {

    private int size;
    private Node<T> top;

    public void push(T item) {
        Node<T> node = createNode(Objects.requireNonNull(item), top);
        this.size++;
        this.top = node;
    }

    public Node<T> pop() {
        Node<T> result = top;
        top = top.next;
        this.size--;
        return result;
    }

    public void clear() {
        this.top = null;
        this.size = 0;
    }

    public  void printStack() {
        Node<T> head = this.top;
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }


    private static <T> Node<T> createNode(T data, Node<T> next) {
        return new Node<>(data, next);
    }


    public static class Node<T> {

        private T data;
        private Node<T> next;

        public Node(T data) {
            this(data, null);
        }

        Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        public void setData(T data) {
            this.data = data;
        }

        public T getData() {
            return this.data;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public Node<T> getNext() {
            return this.next;
        }
    }

    public static void main(String[] args) {
        StackBaseOnLinkedList<String> stack = new StackBaseOnLinkedList<>();
        stack.push("A");
        stack.push("B");
        stack.push("C");
        stack.pop();
        stack.push("D");
        stack.push("E");
        stack.pop();
        stack.push("F");
        stack.printStack();
    }
}
