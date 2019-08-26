package sort;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 选择排序  ----  在不断的选择剩余元素的最小者
 * <p>
 * 首先，找到数组中最小的那个元素，其次，将它和数组的第一个元素交换位置。
 * 再次，在剩下的元素中找到最小的元素，将它和数组中的第二个元素交换位置。
 * 如此往复，直到将整个数组排序。
 * <p>
 * 注：对于长度为 N 的数组，选择排序需要大约 N*N / 2 次比较和 N 次交换
 * <p>
 * 特点：
 * 运行时间和输入无关（无论输入的初始状态是否有序）
 * 移动数据是最少的
 * <p>
 * 分析：
 * 选择排序空间复杂度为 O(1)，是一种原地排序算法
 * 选择排序的最好情况时间复杂度、最坏情况和平均情况时间复杂度都为 O(n2)
 * 选择排序是一种不稳定的排序算法
 *
 * @author suchao
 * @date 2018/10/20
 */
public class Selection {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exec(a, i, min);
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
