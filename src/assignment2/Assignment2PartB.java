package assignment2;

import java.util.*;
/**
 * Name: Ryan Xu
 * Date: March 20th, 2025
 * Description:
 * This program finds the cheapest way through a graph with a recursive approach and memoization.
 * We've taken steps to streamline the rather crude process used in Part A
 */
public class Assignment2PartB {
    // utility variables
    public static int[][] dp; // memoize the results for efficiency and less complicated code
    public static int ROWS, COLS; // bounds for the program
    public static int[][] parentRow, parentCol; // store the previous path to reconstruct
    public static boolean[][] visited; // make sure we aren't in a loop
    public static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // N, S, W, E
    public static final String[] DIR_NAMES = {"SOUTH","NORTH",  "EAST", "WEST"};

    /**
     * Recursive method to find the cheapest path. We are using naive recursive solution with memoization of cheapest path
     * The secret sigma technique here is that we simply check the cheapest way to get to end node from (r, c)
     * @param r current row
     * @param c current column
     * @param cost costs of each node
     * @return The minimum cost to reach the destination from (r, c)
     */
    public static int findCheapestPath(int r, int c, int[][] cost) {
        if (r < 0 || r >= ROWS || c < 0 || c >= COLS) return Integer.MAX_VALUE; // bounds check
        if (r == ROWS - 1 && c == 0) return cost[r][c]; // base case
        if (visited[r][c]) return dp[r][c]; // return memoized result if we have it

        visited[r][c] = true; // mark this node as visited in this recursion stack (all methods called by this method will recognize this cell as visited)

        int minCost = Integer.MAX_VALUE, bestRow = -1, bestCol = -1; // utility

        for (int d = 0; d < 4; d++) { // basic loop to do all four directions so we don't have to have the same code 4 times
            int nr = r + DIRECTIONS[d][0], nc = c + DIRECTIONS[d][1];

            if (nr >= 0 && nr < ROWS && nc >= 0 && nc < COLS) { // bounds check again to reduce number of calls
                int pathCost = findCheapestPath(nr, nc, cost);
                if (pathCost < minCost) {
                    minCost = pathCost;
                    bestRow = nr;
                    bestCol = nc;
                }
            }
        }

        visited[r][c] = false; // backtrack, the cell is now unmarked

        // store result and update path tracking
        dp[r][c] = cost[r][c] + minCost;
        parentRow[r][c] = bestRow;
        parentCol[r][c] = bestCol;
        return dp[r][c]; // store the minimum cost to get to end node from current node.
    }

    /**
     * recursive utility method to construct the output string
     * we start at the end so we are backtracking our way to the start
     * @param r current row
     * @param c current column
     * @param cost node costs
     * @param costs output string for the costs
     * @param sPath output string for the rows
     */
    public static void reconstructPath(int r, int c, int[][] cost, StringBuilder costs, StringBuilder sPath) {
        int pr = parentRow[r][c], pc = parentCol[r][c];
        costs.insert(0, cost[r][c] + " "); // store the step

        if (r == ROWS - 1 && c == 0) return; // exit case: back at the beginning
        for (int d = 0; d < 4; d++) { // run through the 4 options to find out which cell we came from
            if (pr == r + DIRECTIONS[d][0] && pc == c + DIRECTIONS[d][1]) {
                sPath.insert(0, DIR_NAMES[d] + " "); // store the step
                break;
            }
        }
        reconstructPath(pr, pc, cost, costs, sPath);
    }

    /**
     * Main method
     * @param args command-line args not used
     */
    public static void main(String[] args) {
        // standard
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        System.out.println("Finding the Cheapest Routes:");

        for (int gNum = 1; gNum <= T; gNum++) {
            // read input
            ROWS = s.nextInt();
            COLS = s.nextInt();
            int[][] cost = new int[ROWS][COLS];
            dp = new int[ROWS][COLS];
            parentRow = new int[ROWS][COLS];
            parentCol = new int[ROWS][COLS];
            visited = new boolean[ROWS][COLS];

            for (int i = 0; i < ROWS; i++)
                for (int j = 0; j < COLS; j++) {
                    cost[i][j] = s.nextInt();
                    dp[i][j] = Integer.MAX_VALUE; // initialize memoization table with large values to avoid confusion
                }

            int cheapestCost = findCheapestPath(0, COLS - 1, cost); // find the cheapest cost

            // variables needed for our output
            StringBuilder costs = new StringBuilder();
            StringBuilder sPath = new StringBuilder();
            reconstructPath(0, COLS - 1, cost, costs, sPath); // populate the variables

            // output
            System.out.printf("\nGrid #%d:\n", gNum);
            for (int[] row : cost) {
                for (int col : row) System.out.printf("%d ", col);
                System.out.println();
            }

            System.out.printf("Cheapest Route: %s\n", costs);
            System.out.printf("Direction: %s\n", sPath);
            System.out.printf("Cheapest Cost: $%d\n", cheapestCost);
        }
    }
}
