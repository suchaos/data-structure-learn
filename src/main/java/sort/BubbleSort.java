package sort;

import java.util.Arrays;

/**
 * bubble sort 冒泡排序
 *
 *
 *
 * @author suchao
 * @date 2020/1/18
 */
public class BubbleSort {

    /**
     * 冒泡排序
     *
     * @param a 待排序数组
     * @param n 数组长度
     */
    public static <T> void bubbleSort(Comparable<T>[] a, int n) {
        if (n <= 1) {
            return;
        }
        for (int i = 0; i < n; i++) {
            boolean flag = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j].compareTo((T) a[j + 1]) > 0) {
                    Comparable<T> tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }

    public static void bubbleSort2(int[] a, int n) {
        if (n <= 1) {
            return;
        }
        for (int i = 0; i < n; i++) {
            boolean flag = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        Integer[] test = {5, 4, 1, 7, 9, 0};
        bubbleSort(test, test.length);
        System.out.println(Arrays.toString(test));
    }
}
