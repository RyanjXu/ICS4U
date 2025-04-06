package assignment3;

import java.util.*;
import java.io.*;

/**
 * Name: Ryan Xu
 * Date: March 30th, 2025
 * Description:
 * Terminal app that stores switch games read from a file
 */
public class Assignment3 {
    /**
     * Modified binary search that finds the first index of what we're looking for.
     * @param games container of games
     * @param comp string that we are searching for
     * @param name true to search by name, false to search by type
     * @return index of FIRST OCCURRENCE of the game we are searching for
     */
    public static int binarySearch(ArrayList<SwitchGame> games, String comp, boolean name) {
        int low = 0, high = games.size() - 1;
        int result = -1;  // To store the first occurrence index, -1 if not found

        while (low <= high) {
            int mid = (low + high) / 2;
            SwitchGame game = games.get(mid);

            int c = ((name)
                    ? game.getName().compareToIgnoreCase(comp)
                    : game.getType().compareToIgnoreCase(comp));

            if (c == 0) {
                result = mid;        // Store the occurrence index
                high = mid - 1;      // Search left for first occurrence
            } else if (c < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException { // Main method
        // Reading from the input file
        Scanner s = new Scanner(new File("a3cases.txt"));
        ArrayList<SwitchGame> gamesByName = new ArrayList<>();
        ArrayList<SwitchGame> gamesByType = new ArrayList<>();
        ArrayList<SwitchGame> gamesByRating = new ArrayList<>();

        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] words = line.split(" ");
            try {
                if (words.length < 3) { // invalid line
                    throw new NumberFormatException();
                }

                // Parsing and creating the assignment3.SwitchGame object
                double rating = Double.parseDouble(words[0]);
                String name = String.join(" ", Arrays.copyOfRange(words, 1, words.length - 1));
                String type = words[words.length - 1];

                // Add it into each of the three lists
                SwitchGame cur = new SwitchGame(rating, name, type);
                gamesByName.add(cur);
                gamesByType.add(cur);
                gamesByRating.add(cur);

            } catch (NumberFormatException e) {
                System.out.println("Invalid Line!");
            }
        }

        // Sorting the lists
        Collections.sort(gamesByName);
        Collections.sort(gamesByType, new sortByType());
        Collections.sort(gamesByRating, new sortByRating());
        s.close();
        s = new Scanner(System.in);

//        for(assignment3.SwitchGame game: gamesByType) { debugging code
//            System.out.println(game);
//        }

        // Main loop
        while (true) {
            System.out.print("\nRyan's Switch Game Indexer\n1. Exit\n2. Search by name\n3. Search by type\nPlease enter your choice: ");
            try {
                int choice = Integer.parseInt(s.nextLine());
                if (choice == 1) { // exit
                    System.out.println("Thanks for using my program!");
                    System.exit(0);

                } else if (choice == 2) {  // Search by name
                    System.out.println("Please enter the name of the game: ");
                    String name = s.nextLine().strip();
                    int start = binarySearch(gamesByName, name, true);

                    if (start == -1) {
                        System.out.println("Game not found.");
                        continue;
                    }

                    for (int i = start; i < gamesByName.size(); i++) { // since games are stored by name, print all games with the same name
                        SwitchGame g = gamesByName.get(i);

                        if (!g.getName().equalsIgnoreCase(name)) { // break at the first different name
                            break;
                        }

                        System.out.println(g);

                        // Print the ranking
                        int rank = gamesByRating.indexOf(g) + 1;
                        System.out.printf("Ranking: %d out of %d\n", rank, gamesByRating.size());
                    }

                } else if (choice == 3) {  // Search by type
                    System.out.println("Please enter the type of game you would like to search for: ");
                    String type = s.nextLine().strip();
                    int start = binarySearch(gamesByType, type, false);

                    if (start == -1) { // type not found
                        System.out.println("Game type not found.");
                        continue;
                    }
                    ArrayList<SwitchGame> gamesOfType = new ArrayList<>();
                    for (int i = start; i < gamesByType.size(); i++) { // store all games of the type provided by the user.
                        SwitchGame g = gamesByType.get(i);
                        if (!g.getType().equalsIgnoreCase(type)) {
                            break;
                        }
                        gamesOfType.add(g);
                    }

                    // Used a set here to check that names/ratings are the same for efficiency. This could be done with an ArrayList and checking if each
                    // title/rating is already contained within the list. But that's very inefficient
                    Set<String> names = new HashSet<>();
                    Set<Double> ratings = new HashSet<>();
                    for(SwitchGame g : gamesOfType) {
                        names.add(g.getName());
                        ratings.add(g.getRating());
                    }
                    if (names.size()==1) {
                        System.out.println("All titles are the same. Sorting by rating:");
                        Collections.sort(gamesOfType, new sortByRating()); // sort to print
                    } else if (ratings.size()==1) {
                        System.out.println("All ratings are the same. Sorting by name:");
                        Collections.sort(gamesOfType); // sort to print
                    } else {
                        // Ask user for sorting choice
                        System.out.print("How would you like it to be sorted? (name/rating): ");
                        String sortChoice = s.nextLine().strip().toLowerCase();
                        if (sortChoice.equals("name")) {
                            Collections.sort(gamesOfType); // sort to print
                        } else if (sortChoice.equals("rating")) {
                            Collections.sort(gamesOfType, new sortByRating()); // sort to print
                        } else {
                            System.out.println("Invalid choice. Sorting by name by default.");
                            Collections.sort(gamesOfType);
                        }
                    }
                    for (SwitchGame g : gamesOfType) {  // Print games
                        System.out.println(g);

                        // Print the ranking
                        int rank = gamesByRating.indexOf(g) + 1;
                        System.out.printf("Ranking: %d out of %d\n", rank, gamesByRating.size());
                    }
                }
            } catch (NumberFormatException e) { // error catching
                System.out.println("Invalid Input!");
            }
        }
    }
}
