import java.io.*;
import java.util.*;

/**
 * Name: Ryan Xu
 * Date: February 12th, 2025
 * Description:
 *  Print out the largest sum of consecutive integers in an array
 */
public class Assignment1PartC {
    public static void main(String[] args) { //main method
        try { // error check
            Scanner s = new Scanner(new File("input.txt"));
            int cases = s.nextInt();
            for(int z = 1; z<=cases; z++) { //z stores current case being worked on for output
                int N = s.nextInt(); //number of array values
                int max = Integer.MIN_VALUE; //initialize to the lowest possible value for comparison
                int[] arr = new int[N];
                for(int j = 0; j<N; j++) { //read into array
                    arr[j] = s.nextInt();
                }
                for(int i = 0; i <N; i++) { //calculate all the possible sums starting on all array indices from a[0] to a[N-1]
                    int sum = arr[i];
                    for(int j = i+1; j<N; j++) {
                        sum+=arr[j];
                        max=Math.max(max,sum); //store the greater of the calculated sum and the stored sum
                    }
                }
                System.out.printf("Case #%d: %d\n", z, max); //output
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
