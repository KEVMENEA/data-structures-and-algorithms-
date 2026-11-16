package com.menea.dsa.bigo.interview.array;

public class MaxSubArrayBrute {
    public static void main(String[] args) {
        int[] nums = {-2, -1, -3, 0};
        System.out.println("Maximum Subarray Sum: " + maxSubArray(nums));
    }

    public static int maxSubArray(int[] nums){
        // Initialize maxSum to the first element (handles all-negative arrays correctly)
        int maxSum = nums[0];

        // Outer loop pick the starting index of the subarray
        for(int i = 0; i < nums.length; i++){
            int currentSum = 0;

            // inner loop pick the ending index of the subarray
            for(int j = i; j < nums.length; j++){
                currentSum += nums[j];
                maxSum = Math.max(currentSum, maxSum);
            }
        }
        return maxSum;

    }
}
