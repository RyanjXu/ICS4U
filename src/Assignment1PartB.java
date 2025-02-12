import java.util.*;
/**
 * Name: Ryan Xu
 * Date: February 12th, 2025
 * Description:
 *  Check if a number becomes a palindrome after performing n operations on the initial number without exceeding the long limit.
 *  If the number becomes a palindrome, output the palindrome and the number of steps required.
 */

public class Assignment1PartB {
    /**
     * Utility method to reverse a number
     * @param n number being reversed
     * @return reversed number
     */
    public static long reverse(long n) {
        long res= 0;
        while(n!=0) {
            res=res*10+n%10;
            n/=10;
        }
        return res;
    }

    /**
     * Recursive method that checks if the number becomes a palindrome
     * @param n initial number
     * @param steps number of operations performed on the number
     */
    public static void search(long n, int steps) {
        long rev = reverse(n); //store the reversed number once
        if(n==rev) { //n is a palindrome
            System.out.printf(" becomes the palindrome %d after %d steps\n", n, steps);
            return;
        }
        if(n > Long.MAX_VALUE-rev) { //n does not become a palindrome before exceeding the long limit
            System.out.println(" does not become a palindrome");
            return;
        }
        search(n + rev, ++steps); //n is not a palindrome, perform our operations and check again
    }

    public static void main(String[] args) { //main method
        Scanner s = new Scanner(System.in);
        while(true) {
            System.out.print("Please input a starting number (0 to quit): ");
            if(s.hasNextLong()) {
                long n = s.nextLong();
                if(n==0) { //quit
                    break;
                } else if(n>0) { //valid input
                    System.out.print(n);
                    search(n, 0);
                } else { //negative number
                    System.out.println("Invalid input. Please enter a positive integer.");
                }
            } else { //invalid input
                System.out.println("Invalid input. Please enter a positive integer.");
                s.next(); //clear whatever was inputted
            }
        }
        System.out.println("Program is complete");
        s.close();
    }
}
