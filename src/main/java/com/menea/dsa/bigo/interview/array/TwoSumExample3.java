package com.menea.dsa.bigo.interview.array;

import java.util.Arrays;
import java.util.HashMap;

public class TwoSumExample3 {

    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> mapNumber = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            int complement = target - nums[i];
            if(mapNumber.containsKey(complement)) {
                return new int[]{mapNumber.get(complement), i};
            }
            mapNumber.put(nums[i], i);
         }

        return new int[]{};
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 18;
        int[] result = twoSum(nums, target);

        System.out.println(Arrays.toString(result));
    }
}
