package com.menea.dsa.bigo.interview.array;

import java.util.Arrays;

public class ProductExceptSelf {
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        // Pass: Calculate  left product
        int leftProduct = 1;

        // index = [0, 1, 2, 3]
        // leftProduct = [1, 1, 2, 6]
        // leftProduct (1*1, 1*1, 2*1, 3*2)
        // result = [1, 1, 2, 6]
        for(int i = 0; i < n; i++){
            result[i] = leftProduct;
            leftProduct *= nums[i];
        }

        // Pass : Calcualte right product
        // count index : 3, 2, 1, 0 (reverse)
        // when index[3] = 4, before = 1 => updated = 4
        // inext[2] = 3, before = 3 => updated = 12
        // .....
        int rightProduct = 1;
        for(int i = n -1; i >= 0; i--){
            result[i] *= rightProduct;
            rightProduct *= nums[i];
        }

        return result;

    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 4, 5};

        // Call your method and print the result.
        System.out.println(Arrays.toString(productExceptSelf(nums)));
    }
}
