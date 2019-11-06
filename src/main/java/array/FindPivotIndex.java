package array;

/**
 * 724. 寻找数组的中心索引
 *
 * @author suchao
 * @date 2018/10/22
 * @see <a href="https://leetcode-cn.com/problems/find-pivot-index/description/">
 * https://leetcode-cn.com/problems/find-pivot-index/description/</a>
 */
public class FindPivotIndex {
    // 暴力破解 ---- 时间复杂度： O(n*n)
    public static int pivotIndex(int[] nums) {
        int index = -1;
        for (int i = 0; i < nums.length; i++) {
            int leftSum = 0, rightSum = 0;
            for (int j = 0; j < i; j++) {
                leftSum += nums[j];
            }
            for (int j = i + 1; j < nums.length; j++) {
                rightSum += nums[j];
            }
            if (leftSum == rightSum) {
                index = i;
                break;
            }
        }
        return index;
    }

    // 从后往前减 ---- 时间复杂度： O(n)
    public static int pivotIndex2(int[] nums) {
        int index = -1, leftSum = 0, rightSum = 0;
        for (int x : nums) {
            rightSum += x;
        }
        for (int i = 0; i < nums.length; i++) {
            if ((rightSum - leftSum - nums[i]) == leftSum) {
                index = i;
                break;
            }
            leftSum += nums[i];
        }
        return index;
    }

    public static void main(String[] args) {
        System.out.println(pivotIndex2(new int[]{1, 7, 3, 6, 5, 6}));
        System.out.println(pivotIndex2(new int[]{-1, -1, -1, 0, 1, 1}));
    }
}
