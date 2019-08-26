package sort;

import edu.princeton.cs.algs4.Heap;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * heap sort -- 堆排序
 * （算法4的实现 -- edu.princeton.cs.algs4.Heap）
 *
 * main() 使用 <Ctrl-d> or <Ctrl-z>, 来停止输入
 *
 * @author suchao
 * @date 2018/12/6
 */
public class SuHeap {

    private SuHeap() {
    }

    public static void sort(Comparable[] pq) {
        int n = pq.length;
        for (int k = n / 2; k >= 1; k--) {
            sink(pq, k, n);
        }
        while (n > 1) {
            exch(pq, 1, n--);
            sink(pq, 1, n);
        }
    }

    /***************************************************************************
     * Helper functions to restore the heap invariant.
     ***************************************************************************/
    private static void sink(Comparable[] pq, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(pq, j, j + 1)) {
                j++;
            }
            if (!less(pq, k, j)) {
                break;
            }
            exch(pq, k, j);
            k = j;
        }
    }

    /***************************************************************************
     * Helper functions for comparisons and swaps.
     * Indices are "off-by-one" to support 1-based indexing.
     ***************************************************************************/
    @SuppressWarnings("unchecked cast")
    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i - 1].compareTo(pq[j - 1]) < 0;
    }

    private static void exch(Object[] pq, int i, int j) {
        Object swap = pq[i - 1];
        pq[i - 1] = pq[j - 1];
        pq[j - 1] = swap;
    }

    // print array to standard output
    private static void show(Comparable[] a) {
        for (Comparable anA : a) {
            StdOut.println(anA);
        }
    }

    public static void main(String[] args) {
        int[] ints = StdIn.readAllInts();
        Integer[] a = Arrays.stream(ints).boxed().toArray(Integer[]::new);
        SuHeap.sort(a);
        show(a);
    }
}
