package com.menea.dsa.bigo.interview.array;

import java.util.Arrays;

public class ProductOfArrayExceptSelfBruteForce {
    public static void main(String[] args) {

        int[] nums = {1, 2, 3, 4};

        int[] result = productExceptSelf(nums);

        System.out.println(Arrays.toString(result));
    }

    public static int[] productExceptSelf(int[] nums) {
          int n = nums.length;
          int[] result  = new int[n];

          for(int i = 0; i < n; i++){
              int product = 1;

              // Inner loop: multiply all elements except nums[i]
              for(int j = 0; j < n; j++){
                  if(i != j) {
                      product *= nums[j];
                  }
              }
              result[i] = product;
          }
          return result;

    }
}
