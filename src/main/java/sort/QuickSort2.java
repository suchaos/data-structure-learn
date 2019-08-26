package sort;

import java.util.Arrays;

/**
 * 快速排序 -- quick sort
 *
 * @author suchao
 * @date 2018/10/25
 * <p>
 * 递推公式：
 * quick_sort(p...r) = quick_sort(p...q-1) + quick_sort(q+1...r)
 * <p>
 * 终止条件：
 * p >= r
 * <p>
 * 快排是一种原地，不稳定的排序算法
 * 最好时间复杂度：O(nlogn)
 * 最差时间复杂度：O(n*n)
 * 平均时间复杂度：O(nlogn)
 */
public class QuickSort2 {

    public static void quickSort(int[] a, int n) {
        quickSortInternally(a, 0, n - 1);
    }

    private static void quickSortInternally(int[] a, int p, int r) {
        if (p >= r) {
            return;
        }
        // 获取分区点
        int q = partition(a, p, r);

        quickSortInternally(a, p, q - 1);
        quickSortInternally(a, q + 1, r);
    }

    private static int partition(int[] a, int p, int r) {
        int pivot = a[r];
        int i = p;
        for (int j = p; j < r; j++) {
            if (a[j] < pivot) {
                swap(a, i, j);
                i++;
            }
        }
        swap(a, i, r);

        return i;
    }

    // 寻找第 K 个最小的值
    private static int findKth(int[] a, int k) {
        int index = quickSelect(a, 0, a.length - 1, k);
        return a[index];
    }

    private static int quickSelect(int[] a, int p, int r, int k) {
        int q = partition(a, p, r);

        int mumber = q + 1;
        if (mumber == k) {
            return q;
        } else if (mumber > k) {
            return quickSelect(a, p, q - 1, k);
        } else {
            return quickSelect(a, q + 1, r, k);
        }
    }

    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
        int[] a = {4, 2, 5, 12, 3};
        System.out.println(findKth(a, 5));
//        quickSort(a, a.length);
//        System.out.println(Arrays.toString(a));
    }

}
