package com.oio.practice.arithmetic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @Author: Liqc
 * @Date: 2021/8/24 22:09
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] nums = new int[]{2,7,11,15};
        int target = 9;
        int[] ints = twoSum(nums, target);
        Arrays.stream(ints).forEach(System.out::println);
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hash = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            // 如果包含与当前数相对应的数，表示已经找到
            if (hash.containsKey(target - nums[i])) {
                return new int[]{hash.get(target - nums[i]), i};
            }

            hash.put(nums[i], i);

        }

        return null;
    }

}
