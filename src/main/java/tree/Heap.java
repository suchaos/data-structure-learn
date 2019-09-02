package tree;

import java.util.Arrays;

/**
 * 二叉堆
 *
 * @author suchao
 * @date 2019/8/26
 */
public class Heap {

    /**
     * 上浮，是在最后增加了一个数据，然后进行上浮
     *
     * @param array 待调整的堆
     */
    public static void upAdjust(int[] array) {
        int childIndex = array.length - 1;
        int parentIndex = (childIndex - 1) / 2;

        int temp = array[childIndex];
        while (childIndex > 0 && temp < array[parentIndex]) {
            // 无需真正交换，单项赋值即可
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = (childIndex - 1) / 2;
        }
        array[childIndex] = temp;
    }

    /**
     * 下沉
     *
     * @param array       待调整的堆
     * @param parentIndex 要下沉的父节点
     * @param length      堆的有效大小
     */
    public static void downAdjust(int[] array, int parentIndex, int length) {
        int temp = array[parentIndex];
        int childIndex = 2 * parentIndex + 1;

        while (childIndex < length) {
            // 如果有右子节点并且，右子节点小于左子节点，则定位到右子节点
            if (childIndex + 1 < length && array[childIndex + 1] < array[childIndex]) {
                childIndex++;
            }
            // 如果父节点小于任何一个子节点，则直接跳出
            if (temp <= array[childIndex]) {
                break;
            }
            // 无需真正交换，单项赋值即可
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * parentIndex + 1;
        }
        array[parentIndex] = temp;
    }

    /**
     * 构建最小堆
     *
     * @param array 待调整的堆
     */
    public static void buildHeap(int[] array) {
        // 从最后一个非叶子节点开始，依次下沉
        // (array.length - 2) / 2 是最后一个叶子节点的父节点，也就是最后一个非叶子节点
        for (int i = (array.length - 2) / 2; i >= 0; i--) {
            downAdjust(array, i, array.length);
        }
    }

    public static void main(String[] args) {
        // 在一个最小堆上，末尾添加了一个 0，进行上浮操作
        int[] array = new int[]{1, 3, 2, 6, 5, 7, 8, 9, 10, 0};
        upAdjust(array);
        System.out.println(Arrays.toString(array));

        // 一个乱序的堆，进行重新构建
        array = new int[]{7, 1, 3, 10, 5, 2, 8, 9, 6};
        buildHeap(array);
        System.out.println(Arrays.toString(array));
    }
}
