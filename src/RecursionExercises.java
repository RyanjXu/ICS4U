import java.util.*;
import java.io.*;

public class RecursionExercises {
    public static int treats(int n) {
        if(n==1) {
            return 5;
        }
        return (n%2==0 ? 3 : 5) - (n%11==0 ? 1 : 0) + treats(n-1);
    }
    public static int divide(int n, int d) {
        if(n<d) {
            return (int) Math.round((double) n/d);
        }
        return 1+divide(n-d, d);
    }
    public static int find(String line) {
        if(line.isEmpty()) {
            return 0;
        }
        if(line.charAt(line.length()-1) -'0' > 9 && line.charAt(line.length()-1)!='(' && line.charAt(line.length()-1)!=')') {
            return 1+find(line.substring(0,line.length()-1));
        } else {
            return find(line.substring(0, line.length()-1));
        }
    }
    public static int convert(String line, int base) {
        int dig = line.charAt(0) - '0';
        if(line.length()==1) {
            return dig;
        }
        return dig*(int) Math.pow(base, line.length()-1) + convert(line.substring(1), base);
    }
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        boolean on = true;
        while(on) {
            System.out.println("Please choose which exercise you would like to run (1-7), 8 to exit: ");
            switch(s.nextInt()) {
                case 1:
                    System.out.println("Please enter the number of students you choose: ");
                    int choice = s.nextInt();
                    if(choice<=0) {
                        System.out.println("Please enter a positive integer!");
                    } else {
                        System.out.printf("Ms.Wong's cat will get %d treats\n", treats(choice));
                    }
                    break;
                case 2:
                    System.out.println("Please enter the numerator and denominator seperated by a space: ");
                    int n = s.nextInt();
                    int d = s.nextInt();
                    while(d==0) {
                        System.out.println("Denominator cannot be zero! Please enter another integer: ");
                        d = s.nextInt();
                    }
                    System.out.println ( (((n<0)^(d<0)) ? "-" : "") + divide(Math.abs(n), Math.abs(d)));
                    break;
                case 3:
                    System.out.println("Please enter your string: ");
                    String str = s.nextLine().trim();
                    System.out.println(find(str));
                    break;
                case 4:
                    System.out.println("Please enter the number and base you would like to convert: ");
                    String line = s.nextLine().trim();
                    int base = s.nextInt();
                    while(base<1) {
                        System.out.println("Base must be positive! Please try again: ");
                        base = s.nextInt();
                    }
                    System.out.println((line.charAt(0)=='-') ? "-" + convert(line.substring(1), base)  : convert(line, base) );
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    on = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
        s.close();
    }
}
