package assignment4;
import java.io.*;

public class Driver {
    public static int displayMenu (int menuNum, BufferedReader stdIn) throws IOException {

        if (menuNum == 0) {
            System.out.println ("----------  MAIN MENU  -----------");
            System.out.println ("1) Accessing your TV shows list");
            System.out.println ("2) Accessing within a particular TV show");
            System.out.println ("3) Exit");
            System.out.println ("----------------------------------");
        }
        else if (menuNum == 1) {
            System.out.println ("\n---------  SUB-MENU #1  ----------");
            System.out.println ("1) Display a list of all your TV shows"); // Just the show titles, numbered in order
            System.out.println ("2) Display info on a particular TV show");
            System.out.println ("3) Add a TV show"); // Adds a show by reading from input file
            System.out.println ("4) Remove (show or season)");
            System.out.println ("5) Show status");
            System.out.println ("6) Return back to main menu.");
            System.out.println ("----------------------------------");
        }
        else {
            System.out.println ("\n---------  SUB-MENU #2  ----------");
            System.out.println ("1) Display all episodes (in the last sorted order) ");
            System.out.println ("2) Display info on a particular episode ");
            System.out.println ("3) Watch an episode");
            System.out.println ("4) Add an episode");
            System.out.println ("5) Remove episode(s) (4 options)");
            System.out.println ("6) Sort episodes (3 options)");
            System.out.println ("7) Return back to main menu");
            System.out.println ("----------------------------------");
        }

        System.out.print ("\n\nPlease enter your choice:  ");
        int choice = Integer.parseInt (stdIn.readLine ());

        return choice;
    }


    public static void main (String[] args) throws IOException {
        BufferedReader stdIn = new BufferedReader (new InputStreamReader (System.in));
        int mainMenuChoice, subMenuChoice;
        mainMenuChoice = displayMenu (0, stdIn);

        if (mainMenuChoice == 1)
            subMenuChoice = displayMenu (1, stdIn);
        else if (mainMenuChoice == 2)
            subMenuChoice = displayMenu (2, stdIn);
    }
}
