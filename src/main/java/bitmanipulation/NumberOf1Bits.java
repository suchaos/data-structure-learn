package bitmanipulation;

/**
 * 191. 位1的个数
 * <p>
 * 编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）。
 *
 * @author suchao
 * @date 2020/1/13
 * @link https://leetcode-cn.com/problems/number-of-1-bits/
 */
public class NumberOf1Bits {

    /*
         1. 第一想法是转换位字符串，一个个比对
            问题： String.valeOf 会自动将输入的二进制转化为十进制
         2. 位运算 -- 推荐
            1. java.lang.Integer.bitCount 调用 Java 库的函数
            2. %2 /2
            3. 和 1 与，得到最后一位是不是1，然后右移，继续上面的过程
            4. 不断把数字最后一个 1 反转，加一，直到全为 0 为止
                方法：将 n 和 n−1 与运算总是能把 n 中最低位的 1 变成 0 ，并保持其他位不变
     */

    // you need to treat n as an unsigned value
    public int hammingWeight1(int n) {
        // 现在是错误的，TODO 修改
        int number = 0;
        String s = String.valueOf(n);
        for (char c : s.toCharArray()) {
            if (c == '1') {
                number++;
            }
        }
        return number;
    }

    public int hammingWeight2(int n) {
        int count = 0;
        while (n != 0) {
            count += n & 1;
            n >>>= 1;
        }
        return count;
    }

    public int hammingWeight3(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n &= (n - 1);
        }
        return count;
    }

    public static void main(String[] args) {
        int n = 00000000000000000000000000001011;
        System.out.println(String.valueOf(n));
        //Integer.bitCount()
    }
}
