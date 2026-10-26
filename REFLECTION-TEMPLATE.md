# Caffeine Quest reflection

Name: Mark Gonzales

## Five questions before coding

1. Purpose: The purpose of the merge operation is to combine two already sorted halves into one sorted section. It compares elements from both halves, places them in the correct order using a temporary buffer, and then copies them back into the original list.
2. Inputs and outputs: The items parameter is the original list, and buffer temporarily stores the merged elements. The order comparator determines which element comes first. The start, middle, and end parameters identify the boundaries of the two sorted halves. The method updates the original list and returns the total number of comparisons made during that merge.
3. Constraints: Both halves are already sorted before the merge operation begins. I cannot use Java's built-in sorting methods, and I must use the provided comparator. The merge must also preserve stability by keeping elements with equal keys in their original relative order.
4. Edge cases: If one half runs out of elements, the remaining elements from the other half are copied into the buffer without further comparisons. If two elements have equal keys, the left element is selected first to maintain stability.
5. Test oracle: I can verify that my implementation works by running SortCheck.java, which contains 12 supplied tests. I can also compare Student mode with Demo mode using the same starting coordinates and search filters. Both modes should produce identical store rankings and distances.

## Explore Demo mode

Starting latitude/longitude: 
Starting latitude [37.3022] 
Starting longitude [-120.483] 

| Filter | Rank | Store ID / name | Distance in miles |
|---|---:|---|---:|
| All stores | 1 | 15622/Main Place - Merced | 0.08 |
| All stores | 2 | 11150/580 West Olive - Merced | 1.20 |
| All stores | 3 | 17430/Childs & Hwy 99 - Merced | 1.84 |
| Drive-through only | 1 | 17430/ Childs & Hwy 99 - Merced |  1.84 |
| Drive-through only | 2 | 17356/ Bellevue & Shaffer - Atwater |7.32 |
| Drive-through only | 3 | 6633/ Applegate & Hwy 99 - Atwater | 7.83 |

What changed, and why would alphabetical order not answer this search?
When I changed the filter to drive-through only, the results changed because Starbucks locations without a drive-through were excluded. The closest drive-through location was Childs & Hwy 99 instead of Main Place in Merced.

Alphabetical sorting would not answer which Starbucks locations are closest because it sorts stores by their names instead of their distances. To find the nearest locations, the program needs to calculate the distance from the starting coordinates and sort the stores from nearest to farthest.

## Hand trace: merge [2, 7] and [3, 5]

| Step | Compared values (if any) | Item copied | Buffer so far |
|---|---|---|---|
| 1 | 2 vs. 3 (2 < 3) | 2 | [2] |
| 2 | 7 vs. 3 (7 > 3) | 3 | [2, 3] |
| 3 | 7 vs. 5 (7 > 5) | 5 | [2, 3, 5] |
| 4 | Right half empty | 7 | [2, 3, 5, 7] |

## Validation

Final SortCheck output or evidence filename: 
PASS: Empty list 
PASS: One item 
PASS: Two reversed items 
PASS: Duplicates and negatives 
PASS: Already sorted 
PASS: Reverse sorted 
PASS: Odd-sized random list 
PASS: Stable equal-key order 
PASS: Counts comparisons 
PASS: All Starbucks records 
PASS: Drive-through Starbucks records 
PASS: Distance ties use store ID 

12 passed; 0 failed. 

All supplied checks passed.

Additional test: input-[8, 3, 8, 1, 5, 3] / expected output-[1, 3, 3, 5, 8, 8] / actual output-[1, 3, 3, 5, 8, 8] / reason: I created this test to check whether my merge sort correctly handles duplicate values mixed with other unsorted numbers. The actual output matched my expected output, showing that the algorithm correctly sorted the numbers without losing any duplicate values.

Student-mode first three results for the two original searches:
Student Mode: All Stores
1. Store: Main Place - Merced | Store ID: 15622 | Dis: 0.08 miles
2. Store: 580 West Olive - Merced | Store ID: 11150 | Dis: 1.20 miles
3. Store: Childs & Hwy 99 - Merced | Store ID: 17430 | Dis: 1.84 miles
Student Mode: Drive-through Only
1. Store: Childs & Hwy 99 - Merced | Store ID: 17430 | Dis: 1.84 miles
2. Store: Bellevue & Shaffer - Atwater | Store ID: 17356 | Dis: 7.32 miles
3. Store: Applegate & Hwy 99 - Atwater | Store ID: 6633 | Dis: 7.83 miles


How do they compare with Demo mode? Student map evidence filename:
 Both Demo mode and Student mode produced the same Starbucks locations in the same order and with the same distances for the All Stores and Drive-through Only searches. This shows that my merge sort correctly ranks the locations by distance. Demo mode uses Java's built-in sorting algorithm, while Student mode uses my merge sort, so their comparison counts and execution times may differ.
student-map-screenshot.png
 

## Investigate one feature (150-250 words)

Topic and class/method: Comparators in MergeSort.java, specifically the merge() method.

Short relevant code excerpt: order.compare(items.get(left), items.get(right)) <= 0

Explanation and concrete example: I investigated how comparators work in the Caffeine Quest application. In MergeSort.java, the merge() method uses a comparator to determine which element should come first. It compares the current elements from the left and right halves of the list. If the comparison returns zero or a negative number, the left element is selected. Otherwise, the right element is selected. For example, comparing 2 and 3 selects 2 first. When two values are equal, choosing the left element preserves their original order and keeps the sorting algorithm stable.

Source consulted (title and URL or course reference): https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/Comparator.html

# Customization

Feature:

Files changed and explanation:

New-feature test and result:

Default-behavior regression check:

## Algorithm explanation

1. Time complexity and reasoning: Merge sort has a time complexity of O(n log n). The algorithm repeatedly divides the list into smaller halves, creating approximately log n levels. At each level, merging the elements takes O(n) work, giving an overall time complexity of O(n log n).
2. Auxiliary space: My merge sort uses O(n) auxiliary space because it creates a temporary buffer to store elements during merging. The recursive calls also require O(log n) stack space, but the overall auxiliary space remains O(n).
3. Stability and how my code preserves it: Stability means that elements with equal sorting keys keep their original relative order. My implementation preserves stability by using <= 0 when comparing elements. This causes the left element to be selected first when two values are equal.
4. Why Demo/Student comparison counts may differ: Demo mode uses Java's built-in sorting algorithm, while Student mode uses my merge sort. Since they use different sorting approaches, they may perform different numbers of comparisons even when they produce the same results.
5. Why a single short timing is insufficient: One timing measurement cannot reliably determine which algorithm is faster because execution time can be affected by background programs, CPU activity, and Java runtime optimizations. Running multiple tests using the same inputs would provide a more accurate comparison.

## Demonstration checklist

- [ ] Student search and map
- [ ] Explain one merge comparison
- [ ] Show customization
