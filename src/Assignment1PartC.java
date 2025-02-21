import java.io.*;
import java.util.*;

/**
 * Name: Ryan Xu
 * Date: February 12th, 2025
 * Description:
 *  Print out the largest sum of consecutive integers in an array
 */
public class Assignment1PartC {
    static StringTokenizer st;
    static String next(BufferedReader br) throws IOException {
        while(st==null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine().trim());
        }
        return st.nextToken();
    }

    static int readInt(BufferedReader br) throws IOException {
        return Integer.parseInt(next(br));
    }
    public static void main(String[] args) { //main method
        try { // error check
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            int cases = readInt(br);
            for(int caseNum = 1; caseNum<=cases; caseNum++) {
                int N = readInt(br); //number of array values
                int max = Integer.MIN_VALUE; //initialize to the lowest possible value for comparison
                int[] arr = new int[N];
                for(int j = 0; j<N; j++) { //read into array
                    arr[j] = readInt(br);
                }

                /**
                 * let's use kadane's algorithm to solve this problem
                 *
                 */
                int currentSum = 0;
                for (int i = 0; i < N; i++) {
                    currentSum += arr[i];
                    max = Math.max(max, currentSum);
                    if (currentSum < 0) {
                        currentSum = 0;
                    }
                }

                System.out.printf("Case #%d: %d\n", caseNum, max); //output
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
}
