package com.menea.dsa.bigo.interview.array;

public class MaxDifference {
    public static int maximumDifference(int[] nums){
        if(nums == null || nums.length < 2){
            return -1;
        }
        int minCurrent = nums[0];
        int maxDiff = -1;

        for(int i = 1; i < nums.length; i++){
            // min = 7
            // nums[i] = 0
            if(nums[i] > minCurrent) {

                int currentDiff = nums[i] - minCurrent;
                // TODO: Find a larger element after a small element

                // 1 - 7 = -6 < 7
                // 5 - 1 = 4
                if (currentDiff > maxDiff) {
                    maxDiff = currentDiff;
                }
            }else {
                minCurrent = nums[i];
            }
        }

        return maxDiff;
    }
    public static void main(String[] args) {
        int[] nums = {8, 1, 6, 2, 9};
        System.out.println("Maximum Difference: " + maximumDifference(nums));
    }
}
