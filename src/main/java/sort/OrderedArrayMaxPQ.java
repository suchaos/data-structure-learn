package sort;

import edu.princeton.cs.algs4.StdOut;

/**
 * 算法4 -- 2.4.2.2 -- 优先队列初级实现（有序数组）
 *
 * @author suchao
 * @date 2018/12/5
 */
public class OrderedArrayMaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int size;

    @SuppressWarnings("unchecked cast")
    public OrderedArrayMaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity];
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size <= 0;
    }

    public void insert(Key key) {
        int index = size - 1;
        while (index >= 0 && less(key, pq[index])) {
            pq[index + 1] = pq[index];
            index--;
        }
        pq[index + 1] = key;
        size++;
    }

    public Key delMax() {
        return pq[--size];
    }

    /***************************************************************************
     * Helper functions.
     ***************************************************************************/
    private boolean less(Key v, Key w) {
        return v.compareTo(w) < 0;
    }

    /***************************************************************************
     * Test routine.
     ***************************************************************************/
    public static void main(String[] args) {
        OrderedArrayMaxPQ<String> pq = new OrderedArrayMaxPQ<>(10);
        pq.insert("this");
        pq.insert("is");
        pq.insert("a");
        pq.insert("test");
        while (!pq.isEmpty()) {
            StdOut.println(pq.delMax());
        }
    }
}
