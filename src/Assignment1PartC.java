import java.io.*;
import java.util.*;

/**
 * Name: Ryan Xu
 * Date: February 12th, 2025
 * Description:
 * This program reads multiple test cases from an input file and computes the largest sum of consecutive
 * integers in an array using Kadane's algorithm. The result for each test case is printed in the format:
 * "Case #x: maxSum", where x is the test case number and maxSum is the computed maximum subarray sum.
 */
public class Assignment1PartC {
    /**
     * The main method that executes the program.
     * It reads input from a file named "input.txt", processes multiple test cases,
     * and prints the maximum subarray sum for each case using Kadane's algorithm.
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) { //main method
        try { // error check
            Scanner s = new Scanner(new FileReader("input.txt"));
            int cases = s.nextInt();
            for (int caseNum = 1; caseNum <= cases; caseNum++) {
                int N = s.nextInt(); // Number of elements in the array
                int max = Integer.MIN_VALUE; // Initialize to the lowest possible value for comparison
                int[] arr = new int[N];

                for (int j = 0; j < N; j++) { // Read values into the array
                    arr[j] = s.nextInt();
                }

                /**
                 * Applying Kadane's Algorithm to find the maximum sum of consecutive integers in the array.
                 * The algorithm iterates through the array, keeping track of the current sum of the subarray.
                 * If adding the current element increases the sum, we continue; otherwise, we reset it to zero.
                 * And continue with the next subarray. The maximum sum encountered is stored and updated as needed.
                 */
                int currentSum = 0;
                for (int i = 0; i < N; i++) {
                    currentSum += arr[i];
                    max = Math.max(max, currentSum);
                    if (currentSum < 0) {
                        currentSum = 0;
                    }
                }

                // Output the result in the required format
                System.out.printf("Case #%d: %d\n", caseNum, max);
                s.close();
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
}
