package sort;

import edu.princeton.cs.algs4.StdOut;

/**
 * 算法4 -- 2.4.2.1 -- 优先队列初级实现（无序数组）
 *
 * @author suchao
 * @date 2018/12/5
 */
public class UnorderedArrayMaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int size;

    @SuppressWarnings("unchecked cast")
    public UnorderedArrayMaxPQ(int capacity) {
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
        pq[size++] = key;
    }

    public Key delMax() {
        int max = 0;
        for (int i = 1; i < size; i++) {
            if (less(max, i)) {
                max = i;
            }
        }
        exch(max, size - 1);
        return pq[--size];
    }

    /***************************************************************************
     * Helper functions.
     ***************************************************************************/
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    /***************************************************************************
     * Test routine.
     ***************************************************************************/
    public static void main(String[] args) {
        UnorderedArrayMaxPQ<String> pq = new UnorderedArrayMaxPQ<>(10);
        pq.insert("this");
        pq.insert("is");
        pq.insert("a");
        pq.insert("test");
        while (!pq.isEmpty()) {
            StdOut.println(pq.delMax());
        }
    }
}
