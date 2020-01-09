package dp;

import java.util.HashSet;
import java.util.Set;

/**
 * 1143. 最长公共子序列
 *
 * @author suchao
 * @date 2020/1/8
 * @link https://leetcode-cn.com/problems/longest-common-subsequence/
 */
public class LongestCommonSubsequence {

    public static void main(String[] args) {
        //text1 = "abcde", text2 = "ace"
        // "papmretkborsrurgtina"
        //"nsnupotstmnkfcfavaxgl"
        LongestCommonSubsequence test = new LongestCommonSubsequence();
        System.out.println(test.longestCommonSubsequence1("papmretkborsrurgtina", "nsnupotstmnkfcfavaxgl"));
    }

    /*
        方法2：动态规划，使用二维数组
        https://leetcode-cn.com/problems/longest-common-subsequence/solution/dong-tai-gui-hua-zhi-zui-chang-gong-gong-zi-xu-lie/
     */

    public int longestCommonSubsequence2(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    /*
        方法1：想到的一个最直接的方法，将两个字符串的公共子序列都存到 set 中，然后取交集，遍历交集，得到最长的那个，
        时间复杂度太高，leetcode 超时
     */

    public int longestCommonSubsequence1(String text1, String text2) {
        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();
        subSequence(text1, set1);
        subSequence(text2, set2);
        set1.retainAll(set2);
        int max = 0;
        for (String s : set1) {
            max = Math.max(max, s.length());
        }
        return max;
    }

    public void subSequence(String s, Set<String> set) {
        int length = s.length();
        helper("", s, 0, length, set);
    }

    private void helper(String cur, String word, int level, int max, Set<String> set) {
        if (level == max) {
            return;
        }
        String s1 = cur + word.charAt(level);
        set.add(s1);
        String s2 = cur;
        set.add(s2);
        helper(s1, word, level + 1, max, set);
        helper(s2, word, level + 1, max, set);
    }
}
