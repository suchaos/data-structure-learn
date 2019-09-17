package string;

import edu.princeton.cs.algs4.Alphabet;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 高位优先的字符串排序
 * <p>
 * args: ./src/main/resources/shells.txt
 *
 * @author suchao
 * @date 2019/9/17
 * @see edu.princeton.cs.algs4.MSD
 */
public class MSD {

    /**
     * 基数
     */
    private static int R = Alphabet.LOWERCASE.radix();

    /**
     * 小数组的切换阈值
     */
    private static final int M = 10;

    /**
     * 数据分类的辅助数组
     */
    private static String[] aux;

    private static int charAt(String s, int d) {
        if (d < s.length()) {
            // return s.charAt(d);
            return Alphabet.LOWERCASE.toIndex(s.charAt(d));
        } else {
            return -1;
        }
    }

    public static void sort(String[] a) {
        int N = a.length;
        aux = new String[N];
        sort(a, 0, N - 1, 0);
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        // 以第 d 个字符为键将 a[lo] - a[hi] 排序
        if (hi < lo + M) {
            insertion(a, lo, hi, d);
            return;
        }

        // 1. 计算频率
        // 加 2 ，一个是为了计数，一个是为了将末尾完结的放到前面
        int[] count = new int[R + 2];
        for (int i = lo; i <= hi; i++) {
            count[charAt(a[i], d) + 2]++;
        }

        // 2. 将频率转换为索引
        for (int r = 0; r < R + 1; r++) {
            count[r + 1] += count[r];
        }

        // 3. 数据分类
        for (int i = lo; i <= hi; i++) {
            // 将已经结尾的放到  a 的前面，所以要加一
            aux[count[charAt(a[i], d) + 1]++] = a[i];
        }

        // 4. 回写
        for (int i = lo; i <= hi; i++) {
            a[i] = aux[i - lo];
        }

        // 递归的以每个字符为键进行排序
        // 第一次走到这里，相当于按照首字母进行了分组，然后在每个组里忽略掉首字母进行继续排序
        for (int r = 0; r < R; r++) {
            sort(a, lo + count[r], lo + count[r + 1] - 1, d + 1);
        }
    }

    /**************************辅助方法开始******************************************/

    // insertion sort a[lo..hi], starting at dth character
    private static void insertion(String[] a, int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j - 1], d); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    // exchange a[i] and a[j]
    private static void exch(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // is v less than w, starting at character d
    private static boolean less(String v, String w, int d) {
        // assert v.substring(0, d).equals(w.substring(0, d));
        for (int i = d; i < Math.min(v.length(), w.length()); i++) {
            if (v.charAt(i) < w.charAt(i)) {
                return true;
            }
            if (v.charAt(i) > w.charAt(i)) {
                return false;
            }
        }
        return v.length() < w.length();
    }

    /**************************辅助方法结束******************************************/

    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] a = in.readAllStrings();
        int n = a.length;
        sort(a);
        // edu.princeton.cs.algs4.MSD.sort(a);
        for (String s : a) {
            StdOut.println(s);
        }
    }
}
