package array;

/**
 * 747. 至少是其他数字两倍的最大数
 *
 * @author suchao
 * @link https://leetcode-cn.com/problems/largest-number-at-least-twice-of-others/description/
 * @date 2018/10/22
 */
public class LargestTwice {

    // 时间复杂度： O(n)
    public static int dominantIndex(int[] nums) {
        int largestNum = 0, largestIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > largestNum) {
                largestNum = nums[i];
                largestIndex = i;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (largestIndex != i && nums[i] * 2 > largestNum) {
                return -1;
            }
        }
        return largestIndex;
    }

    // 思路：遍历一遍，找到最大值和第二大值
    // TODO ：现在代码是错误的
    public static int dominantIndex2(int[] nums) {
        int largestIndex = 0;
        int secondIndex = 0, secondNum = -1;
        for (int i = 0; i < nums.length; i++) {
            /*if (nums[i] > nums[largestIndex]) {
                largestIndex = i;
            }
            if (i != largestIndex && nums[i] >= secondNum && nums[i] <= nums[largestIndex]) {
                secondNum = nums[i];
                secondIndex = i;
            }*/
        }
        if (nums[largestIndex] >= 2 * secondNum) {
            return largestIndex;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,0,2,3};
        System.out.println(dominantIndex2(nums));
    }
}
