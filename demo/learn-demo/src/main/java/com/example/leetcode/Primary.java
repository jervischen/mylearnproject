package com.example.leetcode;

/**
 * @author Chen Xiao
 * 删除排序数组中的重复项
 * @since 2021-05-29 21:04
 */
public class Primary {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 2};
        System.out.println(removeDuplicates(nums));
//        for (int i = 0; i < nums.length; i++) {
//            System.out.println(nums[i]);
//        }
    }

    public static int removeDuplicates(int[] nums) {
        int n = nums.length;
        int start = 0;
        int end = 1;

        while (end < n) {

            if (nums[end] != nums[end - 1]) {
                nums[start + 1] = nums[end];
                ++start;
            }

            ++end;
        }

        return start + 1;
    }

}
