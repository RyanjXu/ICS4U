import java.util.*;
import java.io.*;

public class Assignment3 {
    public static int binarySearch(ArrayList<SwitchGame> games, String name) {
        int low = 0, high = games.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            SwitchGame game = games.get(mid);
            int c = game.name.toLowerCase().compareTo(name.toLowerCase());
            if (c == 0) {
                return mid;
            } else if (c < 0) {
                low = mid + 1;
            } else {
                high = mid -1;
            }
        }
        return -1;
    }
    public static void printTypes(String type, ArrayList<SwitchGame> games) {
        for(SwitchGame game : games) {
            if(game.type.equalsIgnoreCase(type)) {
                System.out.println(game);
            }
        }
    } 
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(new File("input.txt"));
        ArrayList<SwitchGame> games = new ArrayList<>();
        HashSet<String> types = new HashSet<>();
        //input
        while(s.hasNextLine()) {
            String line = s.nextLine();
            String[] words = line.split(" ");
            try {
                if(words.length<3) {
                    throw new NumberFormatException();
                }
                // Parsing and creating the SwitchGame object
                double rating = Double.parseDouble(words[0]);
                String type = String.join(" ", Arrays.copyOfRange(words, 1, words.length - 1));
                String name = words[words.length - 1];
                types.add(type.toLowerCase());
                SwitchGame cur = new SwitchGame( rating,name, type);
                games.add(cur);
            } catch(NumberFormatException e) {
                System.out.println("Invalid Line!");
            }
        }
        Collections.sort(games);
        s.close();
        s = new Scanner(System.in);
        while(true) {
            System.out.println("Please enter your choice (name of a game or a type): ");
            String choice = s.nextLine();
            if(choice.equalsIgnoreCase("exit")) {
                System.exit(0);
            } else if(types.contains(choice.toLowerCase())) {
                printTypes(choice, games);
            } else if(binarySearch(games, choice) >= 0) {
                System.out.println(games.get(binarySearch(games, choice)));
            } else {
                System.out.println("Invalid Choice! (name/type does not exist)");
            }
        }
    }
}
