import java.io.*;
import java.util.*;

public class Assignment1PartC {
    public static void main(String[] args) {
        try {
            Scanner s = new Scanner(new File("input.txt"));
            int cases = s.nextInt();
            for(int z = 1; z<=cases; z++) {
                int N = s.nextInt();
                int max = Integer.MIN_VALUE;
                int[] arr = new int[N];
                for(int j = 0; j<N; j++) {
                    arr[j] = s.nextInt();
                }
                for(int i = 0; i <N; i++) {
                    int sum = arr[i];
                    for(int j = i+1; j<N; j++) {
                        sum+=arr[j];
                        max=Math.max(max,sum);
                    }
                }
                System.out.printf("Case #%d: %d\n", z, max);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
