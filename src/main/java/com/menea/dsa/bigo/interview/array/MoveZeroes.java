package com.menea.dsa.bigo.interview.array;

import java.util.Arrays;

public class MoveZeroes {
    static void main(String[] args) {
        int[] arr = { 2, 3,4 ,0, 4, -1};
        moveZeroes(arr);

        System.out.println(Arrays.toString(arr));

    }

    public static void moveZeroes(int[] nums) {
        if(nums == null || nums.length < 0){
            throw new RuntimeException("Invalid Input");
        }
        // TODO: Pointer for the position of the next non-zero element
        int insertPointer = 0;

        // TODO: First pass: Shift all non-zero elements to the front
        for(int num : nums){
            if(num != 0) {
                nums[insertPointer] = num;
                insertPointer++;
            }
        }
        // TODO: Second pass: Fill the remaining positions with zeros
        while (insertPointer < nums.length){
            nums[insertPointer] = 0;
            insertPointer++;
        }
    }
}
