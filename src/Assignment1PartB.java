import java.util.*;
/**
 * Name: Ryan Xu
 * Date: February 12th, 2025
 * Description:
 * This program checks if a number becomes a palindrome after performing a series of operations.
 * Each operation involves adding the number to its reverse. If the number becomes a palindrome,
 * the program outputs the palindrome and the number of steps taken. If the number exceeds the
 * long limit, we assume the number does not become a palindrome.
 */

public class Assignment1PartB {
    /**
     * Utility method to reverse a number.
     * @param n Number being reversed.
     * @return Reversed number.
     */
    public static long reverse(long n) {
        long res = 0;
        while (n != 0) {
            res = res * 10 + n % 10;
            n /= 10;
        }
        return res;
    }

    /**
     * Recursive method that checks if the number becomes a palindrome through successive operations.
     * @param n Initial number.
     * @param steps Number of operations performed on the number.
     */
    public static void search(long n, int steps) {
        long rev = reverse(n); // Store the reversed number once to avoid redundant calculations.
        if (n == rev) { // Check if n is a palindrome.
            System.out.printf("%d becomes the palindrome %d after %d steps\n", n, n, steps);
            return;
        }
        if (n > Long.MAX_VALUE - rev) { // Check if our number overflows after the current operation
            System.out.println(n + " does not become a palindrome due to exceeding the long limit");
            return;
        }
        search(n + rev, steps + 1); // Perform the operation and recurse.
    }

    /**
     * Main method to take user input and process numbers to check for palindrome transformation.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.print("Please input a starting number (0 to quit): ");
            if (s.hasNextLong()) {
                long n = s.nextLong();
                if (n == 0) { // Quit condition.
                    break;
                } else if (n > 0) { // Valid input.
                    search(n, 0);
                } else { // Negative number case.
                    System.out.println("Invalid input. Please enter a positive integer.");
                }
            } else { // Handle invalid input.
                System.out.println("Invalid input. Please enter a positive integer.");
                s.next(); // Clear incorrect input.
            }
        }
        System.out.println("Program is complete.");
        s.close();
    }
}
