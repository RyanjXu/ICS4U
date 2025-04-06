package assignment2;

import java.util.*;

/**
 * Name: Ryan Xu
 * Date: March 20th, 2025
 * Description:
 * This program finds the cheapest way through a graph with a recursive approach and memoization.
 */
public class Assignment2PartA {

    public static int[][] dp; // memoization table
    public static String[][] path; // store which tile you came from before for backtracking
    public static int ROWS, COLS; // boundaries

    /**
     * Recursive method to find the cheapest path. We are using naive recursive solution with memoization of cheapest path
     * We start from the beginning and for each cell simply store the cheapest cost to get there
     * @param r current row
     * @param c current column
     * @param cost node costs
     */
    public static void search(int r, int c, int[][] cost) {
        if(r<0 || c >=COLS) { // out of bounds only two are needed since we can only move in 2 directions
            return;
        }
        if(r<ROWS-1) { // off the bottom
            if(c>0) { // off the left edge
                boolean pre = dp[r][c-1]>dp[r+1][c]; // determine whether it is cheaper to come from the west or the south.
                path[r][c] = (pre ? "NORTH" : "EAST"); // store
                dp[r][c]=(pre ? dp[r+1][c] : dp[r][c-1]) + cost[r][c]; // store the cheaper cost
            } else { // must head north
                path[r][c] = "NORTH";
                dp[r][c]=cost[r][c]+dp[r+1][c];
            }
        } else { // must head east
            path[r][c] = "EAST";
            dp[r][c]=cost[r][c]+dp[r][c-1];
        }
        // check the next two cells
        search(r-1, c, cost);
        search(r, c+1, cost);
    }

    /**
     * Method to construct the strings for our output
     * We start from the end and simply backtrack until we get the beginning
     * @param r current row
     * @param c current column
     * @param cost node costs
     * @param costs output string for the costs
     * @param sPath output string for the path
     */
    public static void reconstructPath(int r, int c, int[][] cost, StringBuilder costs, StringBuilder sPath) { // helper method to backtrack the path
        costs.insert(0, cost[r][c]+" "); // store cost
        if(r==ROWS-1 && c==0) { // base case
            return;
        }
        sPath.insert(0, path[r][c]+' '); //store path
        if(path[r][c].equals("NORTH")) { // figure out where we went next
            reconstructPath(r+1, c, cost, costs, sPath);
        } else {
            reconstructPath(r, c-1, cost, costs, sPath);
        }
    }

    /**
     * Main method
     * @param args not used
     */
    public static void main(String[] args) {
        // input
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        System.out.println("Finding the Cheapest Routes: ");
        for(int gNum =1; gNum<=T; gNum++) {
            //input and variable declarations
            ROWS=  s.nextInt(); COLS = s.nextInt();
            int[][] cost = new int[ROWS][COLS];
            dp = new int[ROWS][COLS]; // standard dp to track min cost to get to each indice
            path = new String[ROWS][COLS]; // this will track the previous decision used to get there.
            for(int i =0; i<ROWS; i++) {
                for(int j =0; j<COLS; j++) {
                    cost[i][j] = s.nextInt();
                }
            }
            dp[ROWS-1][0] = cost[ROWS-1][0]; // the cost of the start is known
            search(ROWS-2, 0, cost); // search both of the possible directions from start
            search(ROWS-1, 1, cost);
            path[ROWS-2][0]= "NORTH"; //standard
            path[ROWS-1][1]= "EAST"; // standard

            // output variables
            StringBuilder costs = new StringBuilder(), sPath= new StringBuilder();
            reconstructPath(0, COLS-1, cost, costs, sPath);
            int cheapestCost = dp[0][COLS-1];

            // output
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
