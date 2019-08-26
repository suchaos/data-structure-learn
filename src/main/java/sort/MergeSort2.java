package sort;

/**
 * 归并排序 （Merge Sort）
 *
 * @author suchao
 * @date 2018/10/25
 * <p>
 * 递推公式：
 * merge_sort(p...r) = merge(merge_sort(p...q), merge_sort(q+1...r))
 * <p>
 * 终止条件：
 * p >= r
 * <p>
 * 归并排序是一个稳定的排序算法
 * 时间复杂度：O(nlogn)
 * 空间复杂度：O(n)
 */
public class MergeSort2 {

    public static void mergeSort(int[] a, int n) {
        mergeSortInternally(a, 0, n - 1);
    }

    private static void mergeSortInternally(int[] a, int p, int r) {
        // 递归终止条件
        if (p >= r) {
            return;
        }

        // 取 p 到 r 之间的中间位置 q
        int q = (p + r) / 2;
        // 分治递归
        mergeSortInternally(a, p, q);
        mergeSortInternally(a, p + 1, r);

        // 将 A[p...q] 和 A[q+1...r] 合并为 A[p...r]
        merge(a, p, q, r);
    }

    private static void merge(int[] a, int p, int q, int r) {
        int i = p;
        int j = q + 1;
        int k = 0;
        int[] tmp = new int[r - p + 1];

        while (i <= q && j <= r) {
            if (a[i] <= a[j]) {
                tmp[k++] = a[i++];
            } else {
                tmp[k++] = a[j++];
            }
        }

        // 判断哪个子数组有剩余的数据
        int start = i;
        int end = q;
        if (j <= r) {
            start = j;
            end = r;
        }

        // 将剩余的数据拷贝到临时数组 tmp
        while (start <= end) {
            tmp[k++] = a[start++];
        }

        // 将 tmp 中的数组拷贝回 a[p...r]
        for (i = 0; i < r-p; i++) {
            a[p + i] = tmp[i];
        }
    }
}
