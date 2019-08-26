package substringSearch;

import edu.princeton.cs.algs4.StdOut;

/**
 * Knuth-Morris-Pratt 字符串查找算法
 *
 * @author suchao
 * @date 2019/1/3
 */
public class SuKMP {
    private final int R;
    private String pat;
    private int[][] dfa;

    public SuKMP(String pat) {
        this.pat = pat;
        this.R = 256;

        int patLength = pat.length();
        dfa = new int[this.R][patLength];
        dfa[pat.charAt(0)][0] = 1;
        for (int x = 0, j = 1; j < patLength; j++) {
            for (int c = 0; c < this.R; c++) {
                dfa[c][j] = dfa[c][x];
            }
            dfa[pat.charAt(j)][j] = j + 1;
            x = dfa[pat.charAt(j)][x];
        }
    }

    public int search(String txt) {
        int i, j, mainLength = txt.length(), patLength = pat.length();
        for (i = 0, j = 0; i < mainLength && j < patLength; i++) {
            j = dfa[txt.charAt(i)][j];
        }
        if (j == patLength) {
            return i - patLength;
        } else {
            return -1;
        }
    }


    public static void main(String[] args) {
        //AACAA
        String pat = args[0];
        //AABRAACADABRAACAADABRA
        String txt = args[1];
        SuKMP kmp = new SuKMP(pat);
        StdOut.println("txt: " + txt);
        int offset = kmp.search(txt);
        StdOut.print("pat: ");
        for (int i = 0; i < offset; i++) {
            StdOut.print(" ");
        }
        StdOut.println(pat);
    }
}
