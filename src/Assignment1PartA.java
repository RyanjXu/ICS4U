import java.util.*;
import java.io.*;

class Card {
    String name;
    String type;
    double cost;
    Card(String name, String type, double cost) {
        this.name = name;
        this.type = type;
        this.cost = cost;
    }
    void print() {

    }
}

public class Assignment1PartA {


    public static void main(String[] args) {
        Scanner s  = new Scanner(System.in);

        System.out.println("Bye Bye My Pokemon Cards\n");
        ArrayList<Card> wongCards = new ArrayList<Card>();

        char choice = 'y';
        int cards = 0;
        while(choice!='n') {
            System.out.printf("Please enter the name of card #%d: ", ++cards);
            String name = s.nextLine();
            System.out.print("Please enter the energy type of the Pokemon: ");
            String type = s.nextLine();
            System.out.print("Please enter the amount for this card: $");
            double cost = Double.parseDouble(s.nextLine());

            System.out.printf("Will Ms. Wong buy this $%.2f card? (y/n): ", cost);
            choice = s.nextLine().charAt(0);


            System.out.print("Are you selling anymore cards? (y/n): ");
            choice = s.nextLine().charAt(0);
        }


        //print
        try {
            PrintWriter w = new PrintWriter(new FileWriter("receipt.txt"), true);
            w.println("Summary of Ms.Wong's purchases:");

        } catch (IOException e) {
            System.out.println("Output Error!");
        }
    }
}
