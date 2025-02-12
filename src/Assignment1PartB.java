import java.util.*;
public class Assignment1PartB {
    public static long reverse(long n) {
        long res= 0;
        while(n!=0) {
            res=res*10+n%10;
            n/=10;
        }
        return res;
    }
    public static void search(long n, int steps) {
        long rev = reverse(n);
        if(n==rev) {
            System.out.printf(" becomes the palindrome %d after %d steps\n", n, steps);
            return;
        }
        if(n > Long.MAX_VALUE-rev) {
            System.out.println(" does not become a palindrome");
            return;
        }
        search(n + rev, ++steps);
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        long n;
        while(true) {
            System.out.print("Please input a starting number (0 to quit): ");
            if(s.hasNextLong()) {
                n = s.nextLong();
                if(n==0) {
                    break;
                } else if(n>0) {
                    System.out.print(n);
                    search(n, 0);
                } else {
                    System.out.println("Invalid input. Please enter a positive integer.");
                }
            } else {
                System.out.println("Invalid input. Please enter a positive integer.");
                s.next();
            }
        }
        System.out.println("Program is complete");
        s.close();
    }
}
