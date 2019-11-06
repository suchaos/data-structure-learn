package array;

import java.util.Arrays;

/**
 * 66. 加一
 *
 * @author suchao
 * @date 2018/10/22
 * @see <a href="https://leetcode-cn.com/problems/plus-one/description/">
 * https://leetcode-cn.com/problems/plus-one/description/</a>
 */
public class PlusOne {
    /**
     * 输入：
     * [9,8,7,6,5,4,3,2,1,0]
     * 输出：
     * [0,-2,-1,-4,-7,-4,-8,-3,-6,-4,-8]
     * 预期：
     * [9,8,7,6,5,4,3,2,1,1]
     * 这种思路有问题！ Integer.MAX_VALUE 比 9876543210 小
     */
    public static int[] plusOne(int[] digits) {
        int num = 0;
        for (int i = 0; i < digits.length; i++) {
            num += Math.pow(10, digits.length - i - 1) * digits[i];
        }
        num = num + 1;
        int length = String.valueOf(num).length();
        int[] result = new int[length];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = num % 10;
            num = num / 10;
        }
        return result;
    }

    public static int[] plusOne2(int[] digits) {
        // 判断最后一位是否进位
        if (digits[digits.length - 1] + 1 == 10) {
            for (int i = digits.length - 1; i >= 0; i--) {
                if (digits[i] + 1 == 10) {
                    digits[i] = 0;
                    if (i == 0) {
                        // 出现最高为进位的情况
                        digits = new int[digits.length + 1];
                        digits[0] = 1;
                        for (int j = 1; j < digits.length; j++) {
                            digits[j] = 0;
                        }
                    }
                } else {
                    digits[i] = digits[i] + 1;
                    break;
                }
            }
        } else {
            digits[digits.length - 1] = digits[digits.length - 1] + 1;
        }
        return digits;
    }

    public static void main(String[] args) {
        //int[] nums = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        int[] nums = {9};
        int[] ints = plusOne2(nums);
        System.out.println(Arrays.toString(ints));
    }
}
