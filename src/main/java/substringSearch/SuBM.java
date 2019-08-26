package substringSearch;

import edu.princeton.cs.algs4.StdOut;

/**
 * Boyer-Moore 字符串匹配算法（启发式地处理不匹配的字符）
 * program arguments: AACAA AABRAACADABRAACAADABRA
 *
 * @author suchao
 * @date 2019/1/3
 */
public class SuBM {
    private final int r;
    private int[] right;
    private String pat;

    SuBM(String pat) {
        this.pat = pat;
        this.r = 256;
        int patLength = pat.length();

        right = new int[r];

        for (int c = 0; c < r; c++) {
            right[c] = -1;
        }
        for (int j = 0; j < patLength; j++) {
            right[pat.charAt(j)] = j;
        }
    }

    public int search(String txt) {
        int mainLength = txt.length();
        int patLength = pat.length();
        int skip;
        for (int i = 0; i < mainLength - patLength; i += skip) {
            skip = 0;
            for (int j = patLength - 1; j >= 0; j--) {
                if (pat.charAt(j) != txt.charAt(i + j)) {
                    skip = j - right[txt.charAt(i + j)];
                    if (skip < 1) {
                        skip = 1;
                    }
                    break;
                }
            }
            if (skip == 0) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String pat = args[0];
        String txt = args[1];
        SuBM bm = new SuBM(pat);
        StdOut.println("tet: " + txt);
        int offset = bm.search(txt);
        StdOut.print("pat: ");
        for (int i = 0; i < offset; i++) {
            StdOut.print(" ");
        }
        StdOut.println(pat);
    }
}
