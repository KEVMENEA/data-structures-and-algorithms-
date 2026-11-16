package com.menea.dsa.bigo.interview.array;

public class BuyTimeSellStock {
    public static void main(String[] args) {
//        int[] price = {7, 1, 5, 3, 6, 4};
        int[] price = {5, 2, 8};
        System.out.println("Buy-Time-to-Sell-Stock: " + maxProfit(price));
    }

    public static int maxProfit(int[] prices) {
        // TODO: Initialize state
        if(prices == null || prices.length == 0){
            return 0;
        }
        // minPrice is a buying price.
        // maxProfit is the difference between selling and buying prices.
        int minPrice = prices[0];
        int maxProfit = 0;

        // TODO: Scan the prices
        for(int i = 1; i < prices.length; i++){

            // Imagine selling at today's price
            int potentialProfit = prices[i] - minPrice;

            // Remember the best profit
            maxProfit = Math.max(maxProfit, potentialProfit);

            // Remember the cheapest buying price
            minPrice = Math.min(minPrice, prices[i]);

        }

        // TODO: Return the result
        return maxProfit;

    }



}
