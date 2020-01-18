package bitmanipulation;

/**
 * 190. 颠倒二进制位
 * <p>
 * 颠倒给定的 32 位无符号整数的二进制位
 *
 * @author suchao
 * @date 2020/1/13
 * @link https://leetcode-cn.com/problems/reverse-bits/
 */
public class ReverseBits {

    /*
        使用位运算：一位一位的移动即可
     */

    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result = (result << 1) + (n & 1);
            n >>>= 1;
        }
        return result;
    }

    public int reverseBits2(int n) {
        return Integer.reverseBytes(n);
    }
}
