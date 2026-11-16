# Java DSA Interview Preparation — Arrays

My personal Java Data Structures and Algorithms interview preparation notes.

**Language:** Java  
**Goal:** Software Engineer interviews  
**Focus:** Understand patterns, write correct Java code, debug independently, and explain solutions clearly.

> My learning rule: Don't memorize code. Understand what each variable means, why the algorithm works, and when to use the pattern.

## Progress Tracker

| # | Problem | Pattern | Status |
|---|---|---|---|
| 1 | Contains Duplicate | HashSet | ✅ Passed independently |
| 2 | Two Sum | HashMap | ✅ Passed independently |
| 3 | Move Zeroes | Two Pointers | 🔁 Revisit empty-array case |
| 4 | Best Time to Buy and Sell Stock | Running Minimum | 🔁 Reimplement independently |
| 5 | Maximum Difference | Running Minimum | ✅ Passed with guidance |
| 6 | Remove Duplicates from Sorted Array | Two Pointers | ✅ First-attempt pass |
| 7 | Product of Array Except Self | Prefix/Suffix Products | ✅ First-attempt pass |
| 8 | Maximum Subarray | Kadane's Algorithm | ✅ First-attempt pass |
| 9 | Rotate Array | Array Manipulation | ⏳ Next |
| 10 | Container With Most Water | Two Pointers | ⏳ Upcoming |

**Progress:** 8/10 problems covered.

---

# 1. Quick Revision Cheat Sheet

Read this section every day before solving new problems.

| Problem | Key variables | Most important idea |
|---|---|---|
| Contains Duplicate | `seen`, `num` | Have I seen this number before? |
| Two Sum | `map`, `complement`, `i` | Find the missing value needed to reach the target |
| Move Zeroes | `read`, `write` | Read nonzero elements and write them to the front |
| Stock Profit | `minPrice`, `maxProfit` | Cheapest earlier price → best possible profit |
| Maximum Difference | `minCurrent`, `maxDiff` | Smallest earlier number → largest positive difference |
| Remove Duplicates | `i`, `j` | `i` = last unique position; `j` = scanner |
| Product Except Self | `leftProduct`, `rightProduct`, `result` | Product on left × product on right |
| Maximum Subarray | `currentSum`, `maxSum` | Start a new subarray or extend the previous one |

## Important Java Techniques

```java
// HashSet: detect a repeated value
if (!seen.add(num)) return true;

// HashMap: search for a complement
int complement = target - nums[i];

// Running minimum
minPrice = Math.min(minPrice, prices[i]);

// Running maximum
maxProfit = Math.max(maxProfit, potentialProfit);

// Two pointers: write at the next position
nums[++i] = nums[j];

// Kadane: start fresh or extend
currentSum = Math.max(nums[i], currentSum + nums[i]);
```

---

# 2. Contains Duplicate

**Pattern:** HashSet  
**Difficulty:** Easy  
**LeetCode:** 217

## Problem

Given an integer array, return `true` if any value appears at least twice. Otherwise, return `false`.

**Example:**

```text
Input:  [1, 2, 3, 1]
Output: true
```

## Key Idea

Use a `HashSet` to remember values encountered so far.

- `seen` = numbers already processed.
- `num` = the current number.

If `seen.add(num)` returns `false`, the number already exists.

## Java Code

```java
import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicateSolution {

    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();

        for (int num : nums) {
            if (!seen.add(num)) {
                return true;
            }
        }

        return false;
    }
}
```

## Complexity

- **Time:** O(n) average
- **Extra space:** O(n)

## How to Explain in an Interview

"I use a HashSet to track numbers I've already seen. For each element, I try to add it to the set. If the insertion fails, that number already exists, so I return true. Otherwise, I finish the traversal and return false."

## Remember

**HashSet = Have I seen this value before?**

Common mistake: Using nested loops when a HashSet can eliminate repeated searches.

---

# 3. Two Sum

**Pattern:** HashMap  
**Difficulty:** Easy  
**LeetCode:** 1

## Problem

Return the indices of two different elements whose sum equals the target.

```text
nums   = [2, 7, 11, 15]
target = 9
output = [0, 1]
```

## Key Idea

For every number, ask:

**What other number do I need to reach the target?**

`complement = target - nums[i]`

- `map` = previously encountered number → its index.
- `i` = current index.
- `complement` = missing number needed to reach the target.

## Java Code

```java
import java.util.HashMap;
import java.util.Map;

public class TwoSumSolution {

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (map.containsKey(complement)) {
                return new int[] {
                    map.get(complement), i
                };
            }

            map.put(nums[i], i);
        }

        return new int[0];
    }
}
```

## Complexity

- **Time:** O(n) average
- **Extra space:** O(n)

## How to Explain in an Interview

"I iterate through the array and calculate the complement needed to reach the target. I check whether I've already encountered that complement using a HashMap. If it exists, I return the stored index and the current index. Otherwise, I store the current number with its index."

## Remember

**Check complement BEFORE inserting the current number.**

Why? To avoid using the same element twice.

---

# 4. Move Zeroes

**Pattern:** Two Pointers  
**Difficulty:** Easy  
**LeetCode:** 283

## Problem

Move all zeroes to the end of the array while preserving the original order of nonzero elements.

```text
Input:  [0, 1, 0, 3, 12]
Output: [1, 3, 12, 0, 0]
```

## Key Variables

- `read` = scans every number.
- `write` = next position for a nonzero number.

## Key Idea

1. Copy nonzero numbers toward the beginning.
2. Fill the remaining positions with zeroes.

## Java Code

```java
public class MoveZeroes {

    public static void moveZeroes(int[] nums) {
        int write = 0;

        // Move nonzero numbers to the front
        for (int read = 0; read < nums.length; read++) {
            if (nums[read] != 0) {
                nums[write] = nums[read];
                write++;
            }
        }

        // Fill remaining positions with zeroes
        while (write < nums.length) {
            nums[write] = 0;
            write++;
        }
    }
}
```

## Complexity

- **Time:** O(n)
- **Extra space:** O(1)

## How to Explain in an Interview

"I use a read pointer to visit every element and a write pointer to track the next available position for nonzero values. I copy nonzero values to the front in their original order. After processing the array, I fill the remaining positions with zeroes."

## Remember

```text
read  → Find nonzero elements
write → Place them at the front
```

**My previous mistake:** I incorrectly handled an empty array.

Correct behavior:

```text
Input:  []
Output: []
```

No exception is needed for an empty array.

**Revision status:** Reimplement independently.

---

# 5. Best Time to Buy and Sell Stock

**Pattern:** Running Minimum / One-Pass State Tracking  
**Difficulty:** Easy  
**LeetCode:** 121

## Problem

Buy one stock on one day and sell it on a later day. Return the maximum profit, or `0` if no profit is possible.

```text
Input:  [7, 1, 5, 3, 6, 4]
Output: 5
```

Buy at `1`, sell at `6`.

## Key Variables

- `minPrice` = cheapest price encountered so far.
- `potentialProfit` = profit if selling today.
- `maxProfit` = best valid profit found so far.

## Key Idea

**Current selling price − cheapest earlier buying price.**

## Java Code

```java
public class BuyTimeSellStock {

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int minPrice = prices[0];
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            int potentialProfit = prices[i] - minPrice;

            maxProfit = Math.max(maxProfit, potentialProfit);

            minPrice = Math.min(minPrice, prices[i]);
        }

        return maxProfit;
    }
}
```

## Complexity

- **Time:** O(n)
- **Extra space:** O(1)

## How to Explain in an Interview

"I traverse stock prices from left to right and maintain the lowest buying price encountered so far. At each day, I calculate the profit from selling at the current price. I update the maximum profit if today's profit is better, and I update the minimum price when I find a cheaper buying opportunity."

## Remember

```text
minPrice  → Cheapest buying opportunity
maxProfit → Best profit found so far
```

**My previous mistake:**

Incorrect:

```java
int potentialProfit = prices[i] - prices[i - 1];
```

This compares today only with yesterday.

Correct:

```java
int potentialProfit = prices[i] - minPrice;
```

**Important:** Buying must happen before selling.

**Revision status:** Reimplement without notes.

---

# 6. Maximum Difference

**Pattern:** Running Minimum  
**Difficulty:** Easy  
**LeetCode:** 2016

## Problem

Find the largest positive value of:

`nums[j] - nums[i]`

where `i < j`.

If no positive difference exists, return `-1`.

```text
Input:  [8, 1, 6, 2, 9]
Output: 8
```

## Key Variables

- `minCurrent` = smallest value encountered so far.
- `currentDiff` = difference with the current number.
- `maxDiff` = largest positive difference found.

## Java Code

```java
public class MaxDifference {

    public static int maximumDifference(int[] nums) {
        if (nums == null || nums.length < 2) {
            return -1;
        }

        int minCurrent = nums[0];
        int maxDiff = -1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > minCurrent) {
                int currentDiff = nums[i] - minCurrent;

                if (currentDiff > maxDiff) {
                    maxDiff = currentDiff;
                }
            } else {
                minCurrent = nums[i];
            }
        }

        return maxDiff;
    }
}
```

## Complexity

- **Time:** O(n)
- **Extra space:** O(1)

## How to Explain in an Interview

"I scan the array from left to right while maintaining the smallest earlier value. Whenever the current value is larger than that minimum, I calculate their difference and compare it with the best difference found so far. If the current value is smaller, it becomes the new minimum."

## Remember

```text
minCurrent → Smallest earlier number
maxDiff    → Best positive difference
```

**My previous mistakes:**

Incorrect:

```java
minCurrent = maxDiff;
```

Correct:

```java
maxDiff = currentDiff;
```

Each variable has one responsibility.

Also, update the running minimum when the current value is smaller; don't put that update inside a condition that only executes for larger values.

### Stock Profit vs. Maximum Difference

| Stock Profit | Maximum Difference |
|---|---|
| No profitable transaction → `0` | No positive difference → `-1` |
| Track `minPrice` | Track `minCurrent` |
| Track `maxProfit` | Track `maxDiff` |

**Revision status:** Completed with debugging guidance.

---

# 7. Remove Duplicates from Sorted Array

**Pattern:** Two Pointers / In-Place Modification  
**Difficulty:** Easy  
**LeetCode:** 26

## Problem

Given a sorted array, remove duplicate values in place and return the number of unique elements.

```text
Input:  [0, 0, 1, 1, 2, 2, 3]

Return: 4

First 4 values: [0, 1, 2, 3]
```

## Key Variables

**Most important point:**

```text
i → Index of the LAST unique element
j → Scanner that examines each number
```

## Key Idea

Because the array is sorted, duplicate values are adjacent.

When the scanner finds a new unique value, move it to the next unique position.

## Java Code

```java
public class RemoveDuplicatesFromSortedArray {

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int i = 0;

        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                nums[++i] = nums[j];
            }
        }

        return i + 1;
    }
}
```

## Understand `nums[++i] = nums[j]`

This statement means:

1. Move `i` forward to the next unique position.
2. Copy the new unique value from `j` to `i`.

For example:

```text
Original: [1, 1, 2]

i = 0
j = 1 → duplicate, skip
j = 2 → new unique value

Increment i to 1
Write nums[1] = 2

Valid portion: [1, 2]
Return: 2
```

## Complexity

- **Time:** O(n)
- **Extra space:** O(1)

## How to Explain in an Interview

"I use two pointers. Pointer `i` tracks the last unique element, and pointer `j` scans the sorted array. When I find a value different from the last unique value, I increment `i` and copy that value into the new position. Finally, I return `i + 1`, which is the number of unique elements."

## Why Is Overwriting Safe?

The scanning pointer stays ahead of or equal to the write position.

The algorithm only overwrites positions that have already been processed, so unread elements are preserved.

## Remember

```text
i = Last unique position
j = Scan next element
Return i + 1
```

**Common mistake:** Returning `i` instead of `i + 1`.

**My result:** First-attempt pass, zero hints.

---

# 8. Product of Array Except Self

**Pattern:** Prefix Product + Suffix Product  
**Difficulty:** Medium  
**LeetCode:** 238

## Problem

Return an array where each output element equals the product of all input elements except the one at the same index.

Do not use division.

```text
Input:  [1, 2, 3, 4]
Output: [24, 12, 8, 6]
```

## Key Variables

- `result[i]` = product of elements to the left after the first pass.
- `leftProduct` = running product from the left.
- `rightProduct` = running product from the right.

## Key Idea

**Product Except Self = Left Product × Right Product**

## Java Code

```java
public class ProductExceptSelf {

    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        int leftProduct = 1;

        // Pass 1: Store products to the left
        for (int i = 0; i < n; i++) {
            result[i] = leftProduct;
            leftProduct *= nums[i];
        }

        int rightProduct = 1;

        // Pass 2: Multiply by products to the right
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= rightProduct;
            rightProduct *= nums[i];
        }

        return result;
    }
}
```

## Complexity

- **Time:** O(n)
- **Auxiliary space:** O(1), excluding the output array.
- **Output array:** O(n)

## How to Explain in an Interview

"I split the product except self into two parts: elements to the left and elements to the right. In the first pass, I store left-side products in the result array. In the reverse pass, I maintain a running right-side product and multiply it with each stored left product. This avoids division and uses constant auxiliary space."

## Most Important Rule

**Use the accumulated product before multiplying by the current element.**

Correct:

```java
result[i] = leftProduct;
leftProduct *= nums[i];
```

Why? The current element must not be included in its own answer.

The same rule applies to the right-side traversal.

## Edge Cases

- One zero → only the zero's position may have a nonzero answer.
- Two or more zeros → every result is zero.
- Negative numbers → multiplication handles signs naturally.

**My result:** First-attempt pass, zero hints.

---

# 9. Maximum Subarray

**Pattern:** Kadane's Algorithm / Dynamic Programming  
**Difficulty:** Medium  
**LeetCode:** 53

## Problem

Find the largest possible sum of a nonempty contiguous subarray.

```text
Input:  [-2, 1, -3, 4, -1, 2, 1, -5, 4]
Output: 6
```

Maximum subarray:

`[4, -1, 2, 1]`

Sum:

`4 - 1 + 2 + 1 = 6`

## Key Variables

- `currentSum` = best subarray sum ending at the current position.
- `maxSum` = best subarray sum found anywhere so far.

## Key Idea

At each number, decide:

**Start a new subarray OR extend the previous subarray.**

## Java Code

```java
public class MaxSubArrayKadaneSolution {

    public static int maxSubArray(int[] nums) {
        int currentSum = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(
                nums[i],
                currentSum + nums[i]
            );

            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }
}
```

## Complexity

- **Time:** O(n)
- **Extra space:** O(1)

## How to Explain in an Interview

"I maintain two variables. `currentSum` represents the maximum sum of a subarray that must end at the current index. `maxSum` stores the best sum found anywhere so far. At each element, I choose whether to start a new subarray or extend the previous one, then update the overall maximum."

## Most Important Rule

```text
currentSum → Best subarray ending HERE
maxSum     → Best subarray found ANYWHERE
```

## Important Initialization

Correct:

```java
int currentSum = nums[0];
int maxSum = nums[0];
```

Don't initialize both to zero.

For an all-negative array:

```text
Input:  [-5, -2, -9]
Output: -2
```

The problem requires a nonempty subarray.

## Common Mistakes

- Resetting whenever an individual number is negative.
- Confusing `currentSum` with `maxSum`.
- Starting `maxSum` at zero and failing negative-only cases.

A negative number can still belong to the best subarray.

**My result:** First-attempt pass, zero hints.

---

# 10. Compare Similar Patterns

## Two Pointers

| Problem | First pointer | Second pointer |
|---|---|---|
| Move Zeroes | `write` = next nonzero position | `read` = scanner |
| Remove Duplicates | `i` = last unique position | `j` = scanner |

**Remember:** Two pointers can serve different responsibilities. Always define what each pointer means.

## Running Minimum

| Problem | Running minimum | Best result |
|---|---|---|
| Stock Profit | `minPrice` | `maxProfit` |
| Maximum Difference | `minCurrent` | `maxDiff` |

**Remember:** Compare today's value with the best earlier opportunity, not necessarily yesterday's value.

## State Tracking

| Problem | State carried forward |
|---|---|
| Stock Profit | Lowest price so far |
| Maximum Difference | Smallest number so far |
| Product Except Self | Running products from both directions |
| Maximum Subarray | Best subarray sum ending here |

**General question:** What information from previous elements do I need to remember to make the best decision now?

---

# 11. How to Explain Any DSA Solution in an Interview

Use this five-step structure.

### Step 1 — Understand the problem

"The input is ..., and I need to return ... . The important constraint is ... ."

### Step 2 — Explain brute force

"My first approach is to ... . This works because ... . However, it repeats ... ."

### Step 3 — Identify the optimization

"I noticed that ... . Instead of recalculating it, I can maintain ... ."

### Step 4 — Explain variable responsibilities

"My variable `x` represents ... . My variable `y` represents ... ."

### Step 5 — Explain correctness and complexity

"At each iteration, the algorithm maintains ... . Therefore, it produces the correct result. The time complexity is ..., and the extra space complexity is ... ."

**Interview reminder:** Don't only say "I use two pointers" or "I use a HashMap." Explain why that technique fits the problem.

---

# 12. My Personal Debugging Checklist

Before submitting Java code:

- [ ] Did I understand the input and output?
- [ ] Did I choose the pattern for a reason?
- [ ] Can I explain every variable?
- [ ] Did I initialize the variables correctly?
- [ ] Am I updating the correct variable?
- [ ] Is my update inside the correct condition?
- [ ] Did I accidentally include an element that should be excluded?
- [ ] Did I confuse indices with values?
- [ ] Does my solution handle negative numbers and zeros?
- [ ] Does it handle a single-element array?
- [ ] Does it handle an empty array if permitted?
- [ ] Can I trace the algorithm manually?
- [ ] Can I explain the time and space complexity?
- [ ] Do my comments match the actual code?

---

# 13. Daily Interview Revision — 10 Minutes

### Minutes 1–2: Recall patterns

Read the Quick Revision Cheat Sheet.

### Minutes 3–5: Recall variable meanings

Choose two problems and explain their variables without opening the Java implementations.

Example:

```text
Remove Duplicates:
i = Last unique position
j = Scanning position

Maximum Subarray:
currentSum = Best ending here
maxSum = Best found anywhere
```

### Minutes 6–8: Mental dry run

Choose one input and trace the variables manually.

Example:

```text
Stock Profit: [7, 1, 5, 3, 6, 4]

minPrice: 7 → 1
maxProfit: 0 → 4 → 5

Final answer: 5
```

### Minutes 9–10: Explain out loud

Practice answering:

- What is the pattern?
- Why does it work?
- What does each variable mean?
- What is the most common mistake?
- What is the complexity?

---

# 14. Next Problems

### Problem 9 — Rotate Array

**Status:** Not started.

Learning focus: In-place array manipulation and reasoning about positions.

### Problem 10 — Container With Most Water

**Status:** Not started.

Learning focus: Two-pointer decisions and reasoning about which pointer should move.

No implementations added yet. I will complete these notes after solving each problem independently.

---

# 15. Final Lessons

1. **HashSet:** Remember previously seen values.
2. **HashMap:** Remember values and their associated indices.
3. **Two Pointers:** Separate reading from writing, or divide pointer responsibilities.
4. **Running Minimum:** Remember the best earlier opportunity.
5. **Prefix/Suffix:** Reuse accumulated calculations in both directions.
6. **Kadane's Algorithm:** Decide whether to restart or extend.
7. **Debugging:** Every variable has one responsibility.
8. **Interview skills:** Explain the reasoning, not just the code.

> **My goal is not to solve hundreds of problems by memorizing solutions. My goal is to recognize patterns, implement them correctly, and explain why they work.**