package search;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 二分查找（基于有序数组）
 * 尽管能够保证查找所需的时间时对数级别的，但是仍然无法支持大型问题，
 * 因为put()方法还是太慢了
 *
 * @author suchao
 * @date 2018/11/19
 */
public class SuBinarySearchST<Key extends Comparable<Key>, Value> {
    private static final int INIT_CAPACITY = 12;
    private Key[] keys;
    private Value[] vals;
    private int n = 0;

    public SuBinarySearchST() {
        this(INIT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public SuBinarySearchST(int capacity) { // 调整数组大小的标准代码请见算法1.1
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size() {
        return n;
    }

    private boolean isEmpty() {
        return size() == 0;
    }

    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) {
            return vals[i];
        } else {
            return null;
        }
    }

    /**
     * 数组二分查找的核心方法
     *
     * @param key 键
     * @return 小于给定键的键的数量
     * 如果表中存在该键， rank() 应该返回该键的位置，也就是表中小于它的键的数量；
     * 如果表中不存在该键， rank() 还是应该返回表中小于它的键的数量。
     */
    public int rank(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to rank() is null");
        }

        int lo = 0, hi = n - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return lo;
    }

    public void put(Key key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException("first argument to put() is null");
        }

        // 查找键，找到则更新值，否则创建新的元素
        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }
        for (int j = n; j > i; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = key;
        vals[i] = val;
        n++;
    }
}
