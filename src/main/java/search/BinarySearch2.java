package search;

/**
 * 二分查找 -- 时间复杂度： O(logn)
 * <p>
 * 局限性：
 * 二分查找依赖的时顺序表结构，（数组）
 * 二分查找针对的时有序数据
 * 二分查找不适合数据量太小的情况，直接顺序遍历就可以了
 * 二分查找也不适合数据量太大的情况，底层时数组，要求内存空间连续
 *
 * @author suchao
 * @date 2018/10/29
 */
public class BinarySearch2 {

    /**
     * 有序数组中不存在重复元素的情况 --- 非递归实现
     *
     * @param a     数组
     * @param n     长度
     * @param value 查找的值
     * @return 索引或者 -1
     */
    public static int bsearch(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;

        // 注意循环退出条件 <=
        while (low <= high) {
            //int mid = (low + high) / 2; -- 可能会溢出
            int mid = low + ((high - low) >> 1);
            if (a[mid] == value) {
                return mid;
            } else if (a[mid] < value) {
                // 注意 low, high 的更新
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    // 有序数组中不存在重复元素的情况 --- 递归实现
    public static int bsearchRes(int[] a, int n, int val) {
        return baearchInternally(a, 0, n - 1, val);
    }

    private static int baearchInternally(int[] a, int low, int high, int val) {
        if (low > high) {
            return -1;
        }
        int mid = low + ((high - low) >> 1);
        if (a[mid] == val) {
            return mid;
        } else if (a[mid] < val) {
            return baearchInternally(a, mid + 1, high, val);
        } else {
            return baearchInternally(a, low, mid - 1, val);
        }
    }

    /*------------数据是从小到大排序的， 查找第一个值等于给定值的元素---------------------------------*/
    public static int bsearchFirstEqual(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] < value) {
                low = mid + 1;
            } else if (a[mid] > value) {
                high = mid - 1;
            } else {
                if ((mid == 0) || (a[mid - 1] != value)) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    /*------------数据是从小到大排序的， 查找最后一个值等于给定值的元素---------------------------------*/
    public static int bsearchLastEqual(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] < value) {
                low = mid + 1;
            } else if (a[mid] > value) {
                high = mid - 1;
            } else {
                if ((mid == n - 1) || (a[mid + 1] != value)) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    /*------------数据是从小到大排序的， 查找第一个大于等于给定值的元素---------------------------------*/
    public static int bsearchFirstGreatEqual(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] >= value) {
                if ((mid == 0) || (a[mid - 1] < value)) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /*------------数据是从小到大排序的， 查找最后一个小于等于给定值的元素---------------------------------*/
    public static int bsearchFirstLessEqual(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] <= value) {
                if ((mid == n - 1) || (a[mid + 1] > value)) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
