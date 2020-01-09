package dp;

/**
 * 152. 乘积最大子序列
 *
 * @author suchao
 * @date 2020/1/9
 * @link https://leetcode-cn.com/problems/maximum-product-subarray/
 * @see MaximumSubarray
 */
public class MaximumProductSubarray {

    /*
        和 53 题相比，多了需要记住最小值，因为可能下一步负负得正
     */

    public int maxProduct(int[] nums) {
        int[] dpMax = nums.clone();
        int[] dpMin = nums.clone();
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dpMax[i] = Math.max(nums[i], Math.max(dpMax[i - 1] * nums[i], dpMin[i - 1] * nums[i]));
            dpMin[i] = Math.min(nums[i], Math.min(dpMax[i - 1] * nums[i], dpMin[i - 1] * nums[i]));
            max = Math.max(max, dpMax[i]);
        }
        return max;
    }

    public int maxProduct2(int[] nums) {
        int[] dpMax = new int[nums.length];
        int[] dpMin = new int[nums.length];
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dpMax[i] = Math.max(nums[i], Math.max(dpMax[i - 1] * nums[i], dpMin[i - 1] * nums[i]));
            dpMin[i] = Math.min(nums[i], Math.min(dpMax[i - 1] * nums[i], dpMin[i - 1] * nums[i]));
            max = Math.max(max, dpMax[i]);
        }
        return max;
    }

    public int maxProduct3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = nums[0];
        for (int i = 1, imax = max, imin = max; i < nums.length; i++) {
            int temp = imax;
            imax = Math.max(nums[i], Math.max(imax * nums[i], imin * nums[i]));
            imin = Math.min(nums[i], Math.min(temp * nums[i], imin * nums[i]));
            max = Math.max(max, imax);
        }
        return max;
    }

    public static void main(String[] args) {
        MaximumProductSubarray test = new MaximumProductSubarray();
        System.out.println(test.maxProduct3(new int[]{-4, -3, -2}));
    }
}
