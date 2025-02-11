import java.util.*;

public class Assignment1PartB {
    public static void search(long n) {
        
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Please input a starting number (0 to quit): ");
        long n=s.nextInt();
        while(n!=0) {
            search(n);

            System.out.print("Please input a starting number (0 to quit): ");
            n = s.nextInt();
        }
    }
}
