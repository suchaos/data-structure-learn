package string;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * Least-Significant-Digit First (LSD)
 * <p>
 * 低位优先的字符串排序
 * <p>
 * -enableassertions / -ea
 * <p>
 * args: ./src/main/resources/words3.txt
 *
 * @author suchao
 * @date 2019/9/17
 */
public class LSD {

    /**
     * @param a 待排序字符串数组
     * @param W 字符宽度
     */
    public static void sort(String[] a, int W) {
        int N = a.length;
        // 扩展 ASCII 字符集的字符数量
        int R = 256;
        String[] aux = new String[N];

        for (int d = W - 1; d >= 0; d--) {
            // 根据第 d 个字符用键索引计数法排序
            // 1. 计算出现频率
            int[] count = new int[R + 1];
            for (int i = 0; i < N; i++) {
                count[a[i].charAt(d) + 1]++;
            }
            // 2. 将频率转换为索引
            for (int i = 0; i < R; i++) {
                count[i + 1] += count[i];
            }
            // 3. 将元素分类
            for (int i = 0; i < N; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }
            // 4. 回写
            for (int i = 0; i < N; i++) {
                a[i] = aux[i];
            }
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] a = in.readAllStrings();
        int n = a.length;

        // check that strings have fixed length
        int w = a[0].length();
        for (String s : a) {
            assert s.length() == w : "Strings must have fixed length";
        }

        // sort the strings
        sort(a, w);

        // print results
        for (String s : a) {
            StdOut.println(s);
        }
    }
}
