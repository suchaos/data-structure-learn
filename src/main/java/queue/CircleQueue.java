package queue;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * 循环队列（基于数组实现）
 * <p>
 * 当用数组来实现队列的时候，会有数据搬移操作，影响性能。
 * <p>
 * 可以通过实现循环队列来避免搬移数据
 * <p>
 * 队空的判定条件： head == tail
 * <p>
 * 队满的判定条件： (tail + 1)%n == head
 * <p>
 * 需要留一个空位不存储元素，否则无法判断队满队空
 *
 * @author suchao
 * @date 2019/6/27
 */
public class CircleQueue<T> {

    private Object[] items;
    private int n = 0;
    private int head = 0;
    private int tail = 0;

    public CircleQueue(int capacity) {
        capacity = capacity + 1;
        items = new Object[capacity];
        n = capacity;
    }

    public boolean enqueue(T item) {
        // 队满
        if ((tail + 1) % n == head) {
            return false;
        }
        items[tail] = Objects.requireNonNull(item);
        tail = (tail + 1) % n;
        return true;
    }

    @SuppressWarnings("unchecked")
    public T dequeue() {
        // head == tail 表示队空
        if (head == tail) {
            return null;
        }
        T result = (T) items[head];
        head = (head + 1) % n;
        return result;
    }

    public void printQuene() {
        StringJoiner joiner = new StringJoiner(",");
        for (int i = head; i % n != tail; i = (i + 1) % n) {
            joiner.add(this.items[i].toString());
        }
        System.out.println(joiner);
        System.out.println("-------");
    }

    public static void main(String[] args) {
        CircleQueue<String> queue = new CircleQueue<>(5);
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
