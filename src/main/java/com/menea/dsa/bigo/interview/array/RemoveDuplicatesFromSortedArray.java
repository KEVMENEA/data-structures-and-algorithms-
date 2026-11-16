package com.menea.dsa.bigo.interview.array;

public class RemoveDuplicatesFromSortedArray {
    public static void main(String[] args) {
//        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int[] nums = {1, 1, 1, 1};

        int uniqueCount  = removeDuplicates(nums);

        System.out.println("Unique count: " + uniqueCount );
        System.out.print("Modified array: ");
        for(int i = 0; i< uniqueCount; i++) {
            System.out.print(nums[i] + " ");
        }

    }


    public static int removeDuplicates(int[] nums) {
        if(nums.length == 0) return 0;

        //  Start pointer i at index 0 because the first element is always unique
        int i = 0;

        // i = j move next
        // i != j is keep
        for(int j = 1; j < nums.length; j++) {
            if(nums[i] != nums[j]) {
                nums[++i] = nums[j];
            }
        }
        return i + 1;   // Total count of unique elements
    }
}

