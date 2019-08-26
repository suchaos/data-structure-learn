package sort;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 插入排序  ----  将每个元素插入到其他已经有序的数组中的适当位置中去
 * <p>
 * 当前索引左边的所有元素都是有序的，但是它们的最终位置还不确定，
 * 为了给更小的元素腾出空间，它们可能会被移动。
 * 但是当索引到达数组的右端是，数组排序就完成了。
 * <p>
 * 注：
 * 对于随机排列的长度为 N 且主键不重复的数组，平均情况下插入排序需要 N*N/4 次比较和 N*N/2 次交换
 * 最好情况下需要 N-1 次比较和 0 次交换；
 * 插入排序需要的交换操作和数组中倒置的数量相同，需要的比较次数大于等于倒置的数量，
 * 小于等于倒置的数量加上数组的大小再减一
 * <p>
 * 特点：
 * 插入排序对于部分有序的数组很有效，也很适合小规模数组。
 * <p>
 * 分析：
 * 插入排序空间复杂度为 O(1)，是一种原地排序算法
 * 插入排序的最好情况时间复杂度是 O(n) -- 从头到尾遍历已经有序的数组、
 * 最坏情况和平均情况时间复杂度都为 O(n2)
 * 插入排序是一种稳定的排序算法
 *
 * @author suchao
 * @date 2018/10/20
 */
public class Insertion {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exec(a, j, j - 1);
            }
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exec(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] a = new In(args[0]).readAllStrings();
        show(a);
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
