package com.menea.dsa.bigo.interview.array;

public class MaxSubArrayKanedaSolution {
    public static int maxSubArray(int[] nums){

        int currentSum = nums[0];
        int maxSum = nums[0];

        // starting from index = 1
        for(int i = 1; i < nums.length; i++){

            // Either extend the previous subarray or start
            // new from current element
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(currentSum, maxSum);
        }
        return maxSum;

    }


    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        System.out.println(maxSubArray(nums));
    }
}
