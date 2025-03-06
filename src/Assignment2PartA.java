import java.util.*;
public class Assignment2PartA {

    public static int[][] dp;
    public static String[][] path; // store which tile you came from before for backtracking
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
    public static void reconstructPath(int r, int c, int[][] cost, StringBuilder costs, StringBuilder sPath) { // helper method to backtrack the path
        costs.insert(0, cost[r][c]+" ");
        if(r==ROWS-1 && c==0) {
            return;
        }
        sPath.insert(0, path[r][c]+' ');
        if(path[r][c].equals("NORTH")) {
            reconstructPath(r+1, c, cost, costs, sPath);
        } else {
            reconstructPath(r, c-1, cost, costs, sPath);
        }
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        System.out.println("Finding the Cheapest Routes: ");
        for(int gNum =1; gNum<=T; gNum++) {
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

            StringBuilder costs = new StringBuilder(), sPath= new StringBuilder();
            reconstructPath(0, COLS-1, cost, costs, sPath);
            int cheapestCost = dp[0][COLS-1];

            System.out.printf("\nGrid #%d: \n", gNum);
            for(int[] row : cost) {
                for(int col : row) {
                    System.out.printf("%d ", col);
                }
                System.out.println();
            }
            System.out.printf("Cheapest Route: %s\n", costs);
            System.out.printf("Direction: %s\n", sPath);
            System.out.printf("Cheapest Cost: $%d\n", cheapestCost);
        }
    }
}
