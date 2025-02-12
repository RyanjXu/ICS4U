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


    public static void main(String[] args) {
        Scanner s  = new Scanner(System.in);

        System.out.println("Bye Bye My Pokemon Cards\n");
        ArrayList<Card> wongCards = new ArrayList<Card>();

        char choice = 'y';
        int cards = 0;
        int cnt = 0;
        double leftoverCardCost = 0;
        double maxCost = 0;
        String mostExpensiveName = null;
        while(choice!='n') {
            System.out.printf("Please enter the name of card #%d: ", ++cnt);
            String name = s.nextLine();
            System.out.print("Please enter the energy type of the Pokemon: ");
            String type = s.nextLine();
            System.out.print("Please enter the amount for this card: $");
            double cost = Double.parseDouble(s.nextLine());

            System.out.printf("Will Ms. Wong buy this $%.2f card? (y/n): ", cost);
            choice = s.nextLine().charAt(0);
            if(choice == 'y') {
                wongCards.add(new Card(name, type, cost));
            } else {
                cards++;
                leftoverCardCost+=cost;
                maxCost = Math.max(maxCost, cost);
                mostExpensiveName = (maxCost==cost ? name : mostExpensiveName);
            }

            System.out.print("Are you selling anymore cards? (y/n): ");
            choice = s.nextLine().charAt(0);
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
