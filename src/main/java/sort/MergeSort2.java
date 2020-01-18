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
        // int q = (p + r) / 2;
        int q = p + (r - p) >> 2;
        // int q = (p + r) >> 2;
        // 分治递归
        mergeSortInternally(a, p, q);
        mergeSortInternally(a, p + 1, r);

        // 将 A[p...q] 和 A[q+1...r] 合并为 A[p...r]
        merge(a, p, q, r);
    }

    private static void merge(int[] a, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = 0;
        int[] tmp = new int[right - left + 1];

        while (i <= mid && j <= right) {
            if (a[i] <= a[j]) {
                tmp[k++] = a[i++];
            } else {
                tmp[k++] = a[j++];
            }
        }

        // 判断哪个子数组有剩余的数据
        int start = i;
        int end = mid;
        if (j <= right) {
            start = j;
            end = right;
        }

        // 将剩余的数据拷贝到临时数组 tmp
        while (start <= end) {
            tmp[k++] = a[start++];
        }

        // 将 tmp 中的数组拷贝回 a[p...r]
        for (i = 0; i < right - left; i++) {
            a[left + i] = tmp[i];
        }
    }

    private static void merge2(int[] arr, int left, int mid, int right) {
        // 1. 申请临时空间
        int[] tmp = new int[right - left + 1];

        // 2. 定义变量开始 merge
        int i = left, j = mid + 1, k = 0;

        /*
           3. merge:
              1. 两个数组都存在数据，哪个小就放到临时数组中
              2. 如果左边数组没有全部移过去，则说明右边已经没有数据了，将数据移动到临时数组中去
              3. 右边同理
         */
        while (i <= mid && j <= right) {
            tmp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= mid) {
            tmp[k++] = arr[i++];
        }
        while (j <= right) {
            tmp[k++] = arr[j++];
        }
        // 4. 将临时数组中的数据复制到指定数组中去
        for (int m = 0; m < tmp.length; m++) {
            arr[m + left] = tmp[m];
        }
        // if (tmp.length >= 0) {
        //     System.arraycopy(tmp, 0, arr, 0, tmp.length);
        // }
    }
}
