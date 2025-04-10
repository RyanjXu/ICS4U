package assignment4;
import java.io.*;
import java.util.*;

public class Driver {
    static ArrayList<TVShow> shows = new ArrayList<>();
    public static int displayMenu (int menuNum, BufferedReader stdIn) throws IOException {
        int maxchoice;
        if (menuNum == 0) {
            maxchoice = 3;
            System.out.println ("----------  MAIN MENU  -----------");
            System.out.println ("1) Accessing your TV shows list");
            System.out.println ("2) Accessing within a particular TV show");
            System.out.println ("3) Exit");
            System.out.println ("----------------------------------");
        }
        else if (menuNum == 1) {
            maxchoice = 6;
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
            maxchoice = 7;
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


        int choice = 0;
        while(choice<1 || choice>maxchoice) {
            System.out.print ("\nPlease enter your choice:  ");
            try {
                choice = Integer.parseInt(stdIn.readLine());
            } catch (NumberFormatException e) {
                System.out.println ("Invalid Choice!");
            }
        }
        return choice;
    }
    public static void readShows (String fileName) throws IOException {
        Scanner fileIn = new Scanner (new File(fileName));
        String showTitle = fileIn.nextLine().strip();
        String genre = fileIn.nextLine().strip();
        TVShow show = new TVShow(showTitle, genre);
        while(fileIn.hasNextLine()) {
            int seasonNumber = Integer.parseInt(fileIn.nextLine().split(" ")[1]);
            Season s = new Season(seasonNumber, show);
            int T = Integer.parseInt(fileIn.nextLine());
            for(int i =1; i<=T; i++) {
                int episodeNum = Integer.parseInt(fileIn.nextLine().strip().split(" ")[1]);
                String episodeName = fileIn.nextLine().strip();
                String episodeLength = fileIn.nextLine().strip();
                Episode e = new Episode(episodeName, episodeNum, new Time(episodeLength), s);
                s.add(e);
            }
            show.add(s);
        }
        shows.add(show);
    }
    public static void main (String[] args) throws IOException {
        BufferedReader stdIn = new BufferedReader (new InputStreamReader (System.in));
        readShows("src/assignment4/bossBaby.txt"); // testing code
        readShows("src/assignment4/umbrella.txt");
        int mainMenuChoice, subMenuChoice;
        while(true) {
            mainMenuChoice = displayMenu (0, stdIn);
            if (mainMenuChoice == 1) {
                subMenuChoice = displayMenu (1, stdIn);
                switch(subMenuChoice) {
                    case 1: // names
                        for(TVShow t : shows) {
                            System.out.printf("Title: %s\n", t.getName());
                        }
                        break;
                    case 2: // info on one show
                        for(int i =1; i<=shows.size(); i++) {
                            System.out.printf("%d. %s\n",i, shows.get(i-1).getName());
                        }
                        System.out.print("Please enter the number of the show you wish to get info on: ");
                        try {
                            int choice = Integer.parseInt(stdIn.readLine());
                            if(choice<1 || choice>shows.size()) {
                                throw new NumberFormatException();
                            }
                            System.out.println(shows.get(choice-1));
                        } catch(NumberFormatException e) {
                            System.out.println("Invalid Input!");
                        }
                        break;
                    case 3: // add a show
                        System.out.print("File name: ");
                        String filename = stdIn.readLine();
                        try {
                            readShows(filename);
                        } catch (FileNotFoundException e) {
                            System.out.println("File not found!");
                        }
                        break;
                    case 4: // remove a show
                        System.out.print("1 to remove by tv show name, 2 to remove by season number: ");
                        try {
                            int choice = Integer.parseInt(stdIn.readLine());
                            if(choice != 1 && choice != 2) {
                                throw new NumberFormatException();
                            }
                            if(choice == 1) {
                                System.out.println("Please enter the name of the show you wish to remove: ");
                                String name = stdIn.readLine();
                                shows.removeIf(show -> show.getName().equals(name));
                                System.out.println("Removed all shows matching the name " + name);
                            } else {
                                for(int i =1; i<=shows.size(); i++) {
                                    System.out.printf("%d. %s\n",i, shows.get(i-1).getName());
                                }
                                choice = Integer.parseInt(stdIn.readLine());
                                if(choice<1 || choice>shows.size()) {
                                    throw new NumberFormatException();
                                }
                                shows.remove(choice);
                                System.out.println("Removed show " + choice);
                            }
                        } catch(NumberFormatException e) {
                            System.out.println("Invalid Input!");
                        }
                        break;
                    case 5: // IMPLEMENT LATER

                        break;
                    case 6: // IMPLEMENT LATER

                        break;
                }
            }

            else if (mainMenuChoice == 2) {
                for(int i =1; i<=shows.size(); i++) {
                    System.out.printf("%d. %s\n",i, shows.get(i-1).getName());
                }
                System.out.print("Please enter the number of the show you wish to get info on: ");
                try {
                    int choice = Integer.parseInt(stdIn.readLine());
                    if(choice<1 || choice>shows.size()) {
                        throw new NumberFormatException();
                    }
                    TVShow show = shows.get(choice-1);
                    show.printSeasons();
                    System.out.print("Please pick a season of the show to focus on: ");
                    choice = Integer.parseInt(stdIn.readLine());
                    if(choice<1 || choice>show.getSize()) {
                        throw new NumberFormatException();
                    }
                    Season s = show.getSeason(choice-1);
                    subMenuChoice = displayMenu (2, stdIn);
                    switch(subMenuChoice) {
                        case 1:
                            s.displayEpisodeTitles();
                            break;
                        case 2:
                            s.displayEpisodeTitles();
                            System.out.print("Please pick an episode to display: ");
                            choice = Integer.parseInt(stdIn.readLine());
                            if(choice<1 || choice>s.getSize()) {
                                throw new NumberFormatException();
                            }
                            System.out.println(s.getEpisode(choice-1));
                            break;
                        case 3: // IMPLEMENT LATER

                            break;
                        case 4:// IMPLEMENT LATER

                            break;
                        case 5:// IMPLEMENT LATER
                            break;
                        case 6: // IMPLEMENT LATER

                            break;
                        case 7:// IMPLEMENT LATER

                            break;
                    }
                } catch(NumberFormatException e) {
                    System.out.println("Invalid Input!");
                }

            }
            else if (mainMenuChoice == 3) { // exit case
                System.out.println("Thanks for using my program!");
                System.exit(0);
            }
        }
    }
}
