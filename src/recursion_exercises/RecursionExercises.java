package recursion_exercises;

import java.util.*;

public class RecursionExercises {
    public static int treats(int n) {
        if(n==1) {
            return 5;
        }
        return (n%11==0 ? -1 : (n%2==0 ? 3 : 5)) + treats(n-1);
    }
    public static int divide(int n, int d) {
        if(n<d) {
            return  (2*n>=d ? 1 : 0);
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
    public static String removeDup(String line) {
        if(!Character.isLetter(line.charAt(0))) {
            return removeDup(line.substring(1));
        }
        if(line.length()==2) {
            return line.charAt(0)!=line.charAt(1)?line:line.substring(0,1);
        }
        if(!Character.isLetter(line.charAt(1))) {
            return removeDup(line.charAt(0) + line.substring(2));
        }
        return line.charAt(0)+ (Character.toLowerCase(line.charAt(0))!=Character.toLowerCase(line.charAt(1))?removeDup(line.substring(1)):removeDup(line.substring(2)));
    }
    private static String commas(int num) {
        String numStr = Integer.toString(num);
        if(num<10) {
            return numStr;
        }
        if(numStr.length()%3==1) {
            return  numStr.charAt(0) +"," + commas(Integer.parseInt(numStr.substring(1)));
        }
        return numStr.charAt(0) + commas(Integer.parseInt(numStr.substring(1)));
    }
    public static int sumArray(String[] arr, int n) {
        if(n==arr.length) {
            return 0;
        }
        try {
            int val = Integer.parseInt(arr[n]);
            return Math.abs(val)*n + sumArray(arr,n+1);
        } catch(NumberFormatException e) {
            return sumArray(arr, n+1);
        }
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while(true) {
            System.out.println("Please choose which exercise you would like to run (1-7), 8 to exit: ");
            switch(Integer.parseInt(s.nextLine())){
                case 1:
                    System.out.println("Please enter the number of students you choose: ");
                    int choice = Integer.parseInt(s.nextLine());
                    while (choice<=0) {
                        System.out.println("Please enter a positive integer!");
                        choice = Integer.parseInt(s.nextLine());
                    }
                    System.out.printf("Ms. Wong's cat will get %d treats\n", treats(choice));
                    break;
                case 2:
                    System.out.println("Please enter the numerator and denominator seperated by a space: ");
                    int numerator = s.nextInt();
                    int denominator = s.nextInt();
                    s.nextLine();
                    while(denominator ==0) {
                        System.out.println("Denominator cannot be zero! Please enter another integer: ");
                        denominator = Integer.parseInt(s.nextLine());
                    }
                    System.out.println ( (((numerator <0)^(denominator <0)) ? "-" : "") + divide(Math.abs(numerator), Math.abs(denominator)));
                    break;
                case 3:
                    System.out.println("Please enter your string: ");
                    String str = s.nextLine().strip();
                    System.out.println(find(str));
                    break;
                case 4:
                    System.out.println("Please enter the number and base you would like to convert: ");
                    String line = s.nextLine().strip();
                    int base = Integer.parseInt(s.nextLine());
                    while(base<1) {
                        System.out.println("Base must be positive! Please try again: ");
                        base = Integer.parseInt(s.nextLine());
                    }
                    System.out.println((line.charAt(0)=='-') ? "-" + convert(line.substring(1), base)  : convert(line, base) );
                    break;
                case 5:
                    System.out.println("Please enter the string you would like to remove duplicates from: ");
                    String string = s.nextLine().strip();
                    System.out.println(removeDup(string.replaceAll("[^a-zA-Z]", "")));
                    break;
                case 6:
                    System.out.println("Please enter the number you would like to format: ");
                    int num = Integer.parseInt(s.nextLine());
                    int abs = Math.abs(num);
                    System.out.print(((num<0)?"-":"+"));
                    System.out.print(Integer.toString(abs).length()%3==1 ? "00" : (Integer.toString(abs).length()%3==2 ? "0" : "") );
                    System.out.println(commas(abs));
                    break;
                case 7:
                    String[] arr = {"12", "-2", "10", "3.2", "haha", "-8"};
                    System.out.println(sumArray(arr, 0));
                    break;
                case 8:
                    System.out.println("Thanks for using my program!");
                    s.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }


}
