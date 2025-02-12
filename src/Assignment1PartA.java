import java.util.*;
import java.io.*;

class Card {
    public String name;
    public String type;
    public double cost;
    public Card(String name, String type, double cost) {
        this.name = name;
        this.type = type;
        this.cost = cost;
    }
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public double getCost() {
        return cost;
    }
}

public class Assignment1PartA {

    public static String getType(Scanner s) {
        Set<String> types = new HashSet<>(Set.of("grass", "fire","water", "poison", "fairy", "psychic", "ghost", "dark", "other"));
        String type = s.nextLine();
        if (types.contains(type.toLowerCase().strip())) {
            return type;
        } else {
            System.out.print("\tINVALID. Please enter the energy type of the Pokemon: ");
            return getType(s);
        }
    }

    public static double getCost(Scanner s) {
        if(s.hasNextDouble()) {
            double cost = s.nextDouble();
            s.nextLine();
            if(cost>=0) {
                return cost;
            } else {
                System.out.print("\tINVALID. Please enter the amount for this card: $");
                s.next();
                return getCost(s);
            }
        } else {
            System.out.print("\tINVALID. Please enter the amount for this card: $");
            s.next();
            return getCost(s);
        }
    }

    public static boolean getChoice(Scanner s, String prompt) {
        String choice = s.nextLine();
        if(choice.equals("y") || choice.equals("Y")) {
            return true;
        } else if(choice.equals("n") || choice.equals("N")) {
            return false;
        } else {
            System.out.println(choice);
            System.out.printf("\tINVALID! %s", prompt);
            return getChoice(s, prompt);
        }
    }

    public static void main(String[] args) {
        Scanner s  = new Scanner(System.in);
        System.out.println("Bye Bye My Pokemon Cards\n");
        ArrayList<Card> wongCards = new ArrayList<>();

        boolean on = true;
        int cards = 0;
        int cnt = 0;
        double leftoverCardCost = 0;
        double maxCost = 0;
        String mostExpensiveName = null;
        while(on) {
            System.out.printf("Please enter the name of card #%d: ", ++cnt);
            String name = s.nextLine();
            System.out.print("Please enter the energy type of the Pokemon: ");
            String type = getType(s);
            System.out.print("Please enter the amount for this card: $");
            double cost = getCost(s);
            System.out.printf("Will Ms. Wong buy this $%.2f card? (y/n): ", cost);
            if(getChoice(s, String.format("Will Ms. Wong buy this $%.2f card? (y/n): ", cost))) {
                wongCards.add(new Card(name, type, cost));
            } else {
                cards++;
                leftoverCardCost+=cost;
                maxCost = Math.max(maxCost, cost);
                mostExpensiveName = (maxCost==cost ? name : mostExpensiveName);
            }
            System.out.print("Are you selling anymore cards? (y/n): ");
            on=getChoice(s, "Are you selling anymore cards? (y/n): ");
        }


        //print
        try {
            PrintWriter w = new PrintWriter(new FileWriter("receipt.txt"), true);
            w.println("Summary of Ms.Wong's purchases:");
            w.printf("%-24s%-24s%-17s\n", "CARD", "TYPE", "COST");
            w.printf("%-24s%-24s%-17s\n", "----", "----", "----");
            double wongCost = 0;
            for(Card c : wongCards) {
                w.printf("%-24s%-24s$%-16.2f\n", c.getName(), c.getType(), c.getCost());
                wongCost += c.getCost();
            }
            w.println("-----------------------------------------------------------------");
            w.printf("%-48s$%-16.2f\n\n", "FINAL COST", wongCost);
            if(cards>0) {
                w.printf("You still need to sell %s for $%.2f\n", (cards==1 ? "1 card" : cards + " cards"), leftoverCardCost);
                w.printf("The most expensive card you are selling is %s for $%.2f\n", "", maxCost);
            }
            w.close();
        } catch (IOException e) {
            System.out.println("Output Error!");
        }
    }
}
