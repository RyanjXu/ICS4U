import java.util.*;
import java.io.*;

/**
 * Name: Ryan Xu
 * Date: February 12th, 2025
 * Description:
 * This terminal application facilitates the selling of Pokémon cards.
 * Users input card details, and the program determines whether a buyer (Ms. Wong) purchases them.
 * A receipt summarizing purchases and remaining unsold cards is generated.
 */

// Simple record class to store the cards
record Card(String name, String type, double cost) {
    public Card {
        Objects.requireNonNull(name);
        Objects.requireNonNull(type);
    }
}

public class Assignment1PartA {
    /**
     * Method to grab the card name
     * @param s passing scanner
     * @param numCards number of cards
     * @return valid string for the name of the card
     */
    public static String getName(Scanner s, int numCards) {
        System.out.printf("Please enter the name of card #%d: ", numCards);
        String name = s.nextLine();
        if(name.isBlank()) { //invalid name
            System.out.println("\tINVALID! ");
            return getName(s, numCards);
        } else { //valid name
            return name;
        }
    }
    /**
     * Method to grab the card type
     * @param s passing scanner
     * @return valid type as a string
     */
    public static String getType(Scanner s) {
        System.out.print("Please enter the energy type of the Pokemon: ");
        Set<String> types = new HashSet<>(Set.of("grass", "fire","water", "poison", "fairy", "psychic", "ghost", "dark", "other")); // all valid card types
        String type = s.nextLine();
        if (types.contains(type.toLowerCase().strip())) { // input is a valid type
            return type.strip(); //strip for later formatting on the output file
        } else { // invalid input try again
            System.out.print("\tINVALID. ");
            return getType(s);
        }
    }

    /**
     * Method to grab the cost of the cards
     * @param s scanner
     * @return valid non-negative cost of the card as a double
     */
    public static double getCost(Scanner s) {
        System.out.print("Please enter the amount for this card: $");
        if(s.hasNextDouble()) { // user inputted a double
            double cost = s.nextDouble();
            s.nextLine();
            if(cost>=0) { // valid input
                return cost;
            } else { // user's cost is negative
                System.out.print("\tINVALID. ");
                return getCost(s);
            }
        } else { // any input that isn't a double
            System.out.print("\tINVALID. ");
            s.next(); // read invalid input
            return getCost(s);
        }
    }

    /**
     * Method to grab the users response to (y/n) choice
     * @param s passing scanner
     * @param prompt original prompt of the question
     * @return boolean indicating user's choice
     */
    public static boolean getChoice(Scanner s, String prompt) {
        System.out.print(prompt);
        String choice = s.nextLine();
        if(choice.equals("y") || choice.equals("Y")) { // user indicated yes
            return true;
        } else if(choice.equals("n") || choice.equals("N")) { // user indicated no
            return false;
        } else { // invalid input
            System.out.print("\tINVALID! ");
            return getChoice(s, prompt);
        }
    }

    public static void main(String[] args) { //main method
        //variable initialization
        Scanner s  = new Scanner(System.in);
        ArrayList<Card> wongCards = new ArrayList<>(); // container to store cards that Ms.Wong bought
        int leftoverCards = 0;
        int numCards = 0;
        double leftoverCardCost = 0;
        double highestCost = 0;
        double totalCost = 0;
        String mostExpensiveName = null;
        boolean on = true; // boolean for program state

        System.out.println("Bye Bye My Pokemon Cards\n");
        while(on) {
            //grab the variables
            String name = getName(s, ++numCards);
            String type = getType(s);
            double cost = getCost(s);
            if(getChoice(s, String.format("Will Ms. Wong buy this $%.2f card? (y/n): ", cost))) { //wong is buying store in arrayList
                wongCards.add(new Card(name, type, cost));
            } else { //wong not buying, add to total cost, store largest cost.
                leftoverCards++;
                leftoverCardCost+=cost;
                highestCost = Math.max(highestCost, cost);
                mostExpensiveName = (highestCost==cost ? name : mostExpensiveName);
            }
            on=getChoice(s, "Are you selling anymore cards? (y/n): ");
        }
        
        //print
        try {
            PrintWriter w = new PrintWriter(new FileWriter("receipt.txt"), true);
            w.println("Summary of Ms.Wong's purchases:");
            w.printf("%-24s%-24s%-17s\n", "CARD", "TYPE", "COST");
            w.printf("%-24s%-24s%-17s\n", "----", "----", "----");
            for(Card c : wongCards) { // for-each loop
                w.printf("%-24s%-24s$%-16.2f\n", c.name(), c.type(), c.cost());
                totalCost += c.cost();
            }
            w.println("-----------------------------------------------------------------");
            w.printf("%-48s$%-16.2f\n\n", "FINAL COST", totalCost);
            if(leftoverCards >0) {
                w.printf("You still need to sell %s for $%.2f\n", (leftoverCards ==1 ? "1 card" : leftoverCards + " cards"), leftoverCardCost); // grammar simplified with a ternary operator
                w.printf("The most expensive card you are selling is %s for $%.2f\n", "", highestCost);
            }
            w.close();
        } catch (IOException e) {
            System.out.println("Output Error!");
        }
    }
}
