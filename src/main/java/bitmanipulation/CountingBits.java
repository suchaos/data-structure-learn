package bitmanipulation;

/**
 * 338. 比特位计数
 * <p>
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，
 * 计算其二进制数中的 1 的数目并将它们作为数组返回
 *
 * @author suchao
 * @date 2020/1/13
 * @link https://leetcode-cn.com/problems/counting-bits/description/
 * @see NumberOf1Bits
 */
public class CountingBits {

    /*
        1. 普通版：每个数都是进行一次计算
        2. 进阶版：DP + 位运算
                   DP 方程： dp(i) = dp(i & (i-1)) + 1
                             n & (n-1) 清零最低位的 1，然后加 1
     */

    public int[] countBits1(int num) {
        int[] result = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            countingOneBits(i, result);
        }
        return result;
    }

    private void countingOneBits(int n, int[] result) {
        int count = 0;
        int number = n;
        while (number != 0) {
            count++;
            number &= (number - 1);
        }
        result[n] = count;
    }

    public int[] countBits2(int num) {
        int[] result = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            result[i] = result[i & (i - 1)] + 1;
        }
        return result;
    }
}
