package caffeinequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSortExtraTest {

    public static void main(String[] args) {

        // Create an unsorted list with duplicate values
        List<Integer> numbers = new ArrayList<>(
            Arrays.asList(8, 3, 8, 1, 5, 3)
        );

        System.out.println("Input: " + numbers);

        // Sort using our MergeSort implementation
        long comparisons = MergeSort.sort(
            numbers, Integer::compareTo
        );

        System.out.println("Actual output: " + numbers);
        System.out.println("Comparisons: " + comparisons);
    }
}