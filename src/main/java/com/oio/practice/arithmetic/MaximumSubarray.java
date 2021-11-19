package com.oio.practice.arithmetic;

/**
 * 53. 最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例 1：
 *
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * 示例 2：
 *
 * 输入：nums = [1]
 * 输出：1
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：0
 * 示例 4：
 *
 * 输入：nums = [-1]
 * 输出：-1
 * 示例 5：
 *
 * 输入：nums = [-100000]
 * 输出：-100000
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 *
 *
 * 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
 *
 * @Author: Liqc
 * @Date: 2021/11/16 18:19
 */
public class MaximumSubarray {

    /**
     * 分治法
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        return solutionOne(nums);
    }


    /**
     * 方法一：贪心算法
     *     使用二个变量，一个记录最大值，一个记录当前连续累加值。
     *     如果之前的累加小于0，则丢弃。一直记录过程中产生的最大值。
     *
     * @param nums
     * @return
     */
    private int solutionOne(int[] nums) {
        // 初始值设置为第一个值
        int sum = nums[0];

        if (nums.length == 1) {
            return sum;
        }

        int cur = sum;
        for (int i = 1; i < nums.length; i++) {
            // 如果之前的和小于0，则抛弃之前的值
            // 若当前的值之前的和小于0，则丢弃当前元素之前的和
            int indexVal = nums[i];
            cur = Math.max(cur + indexVal, indexVal);
            // 比较最大值和当前累加的值
            sum = Math.max(cur, sum);
        }
        return sum;
    }

    /**
     * 动态规划算法
     *  穷举分析
     *  确定边界
     *  找规律，确定最优子结构
     *  状态转移方程
     *
     * 思路：每个数前面的数大于0就加到当前数上，走完后取最大值就是结果
     *
     * @param nums
     * @return
     */
    private int solutionTwo(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int maxVal = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int pre = nums[i - 1];
            if (pre > 0) {
                nums[i] = nums[i] + pre;
            }
            maxVal = Math.max(maxVal, nums[i]);
        }
        return maxVal;
    }

}
