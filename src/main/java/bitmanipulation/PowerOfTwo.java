package bitmanipulation;

/**
 * 231. 2的幂
 * <p>
 * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
 *
 * @author suchao
 * @date 2020/1/13
 * @link https://leetcode-cn.com/problems/power-of-two/
 */
public class PowerOfTwo {

    /*
        位运算：2的幂数的数字的二进制有且只有一个1，其余均是 0 加上 n & (n-1) 清零最低位的1
     */

    public boolean isPowerOfTwo1(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
