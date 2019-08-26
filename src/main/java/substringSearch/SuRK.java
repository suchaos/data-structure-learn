package substringSearch;

import edu.princeton.cs.algs4.StdOut;

import java.math.BigInteger;
import java.util.Random;

/**
 * Rabin-Karp 指纹字符串查找算法
 *
 * @author suchao
 * @date 2019/1/3
 */
public class SuRK {
    private String pat;
    private long pathHash;
    private int patLength;
    /**
     * 一个很大的素数
     */
    private long q;
    private int r = 256;
    /**
     * r^(m-1) % q
     */
    private long rm;

    public SuRK(String pat) {
        this.pat = pat;
        this.patLength = pat.length();
        this.q = longRandomPrime();
        this.rm = 1;
        for (int i = 0; i < patLength - 1; i++) {
            rm = (r * rm) % q;
        }
        this.pathHash = hash(pat, patLength);
    }

    public boolean check(int i) {
        return true;
    }

    private int search(String txt) {
        int mainLength = txt.length();
        long txtHash = hash(txt, patLength);
        if (pathHash == txtHash && check(0)) {
            return 0;
        }
        for (int i = patLength; i < mainLength; i++) {
            // 减去第一个数字，加上最后一个数字，再次检查匹配
            txtHash = (txtHash + q - rm * txt.charAt(i - patLength) % q) % q;
            txtHash = (txtHash * r + txt.charAt(i)) % q;
            if (pathHash == txtHash) {
                if (check(i - patLength + 1)) {
                    return i - patLength + 1;
                }
            }
        }
        return -1;
    }

    /**
     * a random 31-bit prime
     *
     * @return a random 31-bit prime
     */
    private static long longRandomPrime() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }

    /**
     * Compute hash for key[0..m-1].
     *
     * @param key
     * @param m
     * @return
     */
    private long hash(String key, int m) {
        long h = 0;
        for (int j = 0; j < m; j++) {
            h = (r * h + key.charAt(j)) % q;
        }
        return h;
    }

    public static void main(String[] args) {
        String pat = args[0];
        String txt = args[1];
        SuRK rk =  new SuRK(pat);
        StdOut.println("tet: " + txt);
        int offset = rk.search(txt);
        StdOut.print("pat: ");
        for (int i = 0; i < offset; i++) {
            StdOut.print(" ");
        }
        StdOut.println(pat);
    }
}
