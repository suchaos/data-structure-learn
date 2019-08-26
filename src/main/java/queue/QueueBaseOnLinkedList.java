package queue;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * 链式队列（基于链表实现的队列）
 *
 * @author suchao
 * @date 2019/6/27
 */
public class QueueBaseOnLinkedList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;
    private int count;

    public QueueBaseOnLinkedList(int capacity) {
        this.size = capacity;
        this.count = 0;
    }

    public boolean enqueue(T item) {
        if (this.count >= this.size) {
            return false;
        }
        createNode(item);
        return true;
    }

    private void createNode(T item) {
        Node<T> node = new Node<>(Objects.requireNonNull(item));
        if (count == 0) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        count++;
    }

    public T dequeue() {
        if (count == 0) {
            return null;
        }
        Node<T> result = head;
        head = head.next;
        count--;
        return result.data;
    }

    public void printQuene() {
        Node<T> node = this.head;
        StringJoiner joiner = new StringJoiner(",");
        for (int i = 0; i < this.count; i++) {
            joiner.add(node.data.toString());
            node = node.next;
        }
        System.out.println(joiner);
        System.out.println("-------");
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
        QueueBaseOnLinkedList<String> queue = new QueueBaseOnLinkedList<>(5);
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");
        queue.enqueue("e");
        queue.printQuene();
        for (int i = 0; i < 5; i++) {
            queue.dequeue();
            queue.printQuene();
        }
    }
}
