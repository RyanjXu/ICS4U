import java.util.*;
public class Assignment2PartA {

    public static int[][] dp;
    public static String[][] path;
    public static int ROWS, COLS;
    public static void search(int r, int c, int[][] cost) {
        if(r<0 || c >=COLS) {
            return;
        }
        if(r<ROWS-1) { // off the bottom
            if(c>0) { // off the left edge
                boolean pre = dp[r][c-1]>dp[r+1][c]; // determine whether it is cheaper to come from the west or the south.
                path[r][c] = (pre ? "NORTH" : "EAST");
                dp[r][c]=(pre ? dp[r+1][c] : dp[r][c-1]) + cost[r][c];
            } else { // must head north
                path[r][c] = "NORTH";
                dp[r][c]=cost[r][c]+dp[r+1][c];
            }
        } else { // must head east
            path[r][c] = "EAST";
            dp[r][c]=cost[r][c]+dp[r][c-1];
        }
        search(r-1, c, cost);
        search(r, c+1, cost);
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        while(T-->0) {
            ROWS=  s.nextInt(); COLS = s.nextInt();
            int[][] cost = new int[ROWS][COLS];
            dp = new int[ROWS][COLS]; // standard dp to track min cost to get ot each indice
            path = new String[ROWS][COLS]; // this will track the previous decision used to get there.
            for(int i =0; i<ROWS; i++) {
                for(int j =0; j<COLS; j++) {
                    cost[i][j] = s.nextInt();
                }
            }
            // default variable definitions
            dp[ROWS-1][0] = cost[ROWS-1][0]; // cost of reaching these tiles is known
            search(ROWS-2, 0, cost);
            search(ROWS-1, 1, cost);
            path[ROWS-2][0]= "NORTH";
            path[ROWS-1][1]= "EAST";

            int cheapestCost = dp[0][COLS-1];
            System.out.println(cheapestCost);
        }
    }
}
