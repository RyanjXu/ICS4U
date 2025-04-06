package assignment3;

import java.util.Comparator;

class sortByType implements Comparator<SwitchGame> { // Comparator to sort by type
    @Override
    public int compare(SwitchGame o1, SwitchGame o2) {
        return o1.getType().toLowerCase().compareTo(o2.getType().toLowerCase());
    }
}
class sortByRating implements Comparator<SwitchGame> { // Comparator to sort by ratings
    @Override
    public int compare(SwitchGame o1, SwitchGame o2) {
        if(o1.getRating() == o2.getRating()) // equal
            return 0;
        return o1.getRating() < o2.getRating() ? 1 : -1;
    }
}
// Game object
public class SwitchGame implements Comparable<SwitchGame> {
    private final String name;
    private final String type;
    private final double rating;
    // Constructor and Getters
    public SwitchGame(double rating, String name, String type) {
        this.name = name;
        this.type = type;
        this.rating = rating;
    }
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public double getRating() {
        return rating;
    }

    @Override // equals for comparison returns boolean indicating whether the two objects are the same
    public boolean equals(Object obj) {
        if(getClass() != obj.getClass()) {
            return false;
        }
        return this.name.equals(((SwitchGame)obj).name) && this.type.equals(((SwitchGame)obj).type) && this.rating == ((SwitchGame)obj).rating;
    }
    @Override // to string returns string for printing
    public String toString() {
        return String.format("\nName: %s%nType: %s%nRating: %.2f", name, type, rating);
    }
    // default sort order (name) returns int for sorting
    public int compareTo(SwitchGame o) {
        return this.name.compareTo(o.name);
    }
}