package queue;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * 顺序队列（基于数组实现的队列）
 *
 * @author suchao
 * @date 2019/6/27
 */
public class QueueBaseOnArray<T> {

    private Object[] items;
    private int n = 0;
    private int head = 0;
    private int tail = 0;

    public QueueBaseOnArray(int capacity) {
        items = new Object[capacity];
        n = capacity;
    }

    public boolean enqueue(T item) {
        // tail == n 表示队尾没有空间了
        if (tail >= n) {
            // tail == n && head == 0 表示队满
            if (head == 0) {
                return false;
            }
            // 数据搬移
            for (int i = head; i < tail; i++) {
                items[i - head] = items[i];
            }
            tail = tail - head;
            head = 0;
        }
        items[tail++] = Objects.requireNonNull(item);
        return true;
    }

    @SuppressWarnings("unchecked")
    public T dequeue() {
        // head == tail 表示队空
        if (head == tail) {
            return null;
        }
        return (T) items[head++];
    }

    public void printQuene() {
        StringJoiner joiner = new StringJoiner(",");
        for (int i = this.head; i < this.tail; i++) {
            joiner.add(this.items[i].toString());
        }
        System.out.println(joiner);
        System.out.println("-------");
    }

    public static void main(String[] args) {
        QueueBaseOnArray<String> queue = new QueueBaseOnArray<>(5);
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
