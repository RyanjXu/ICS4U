public class SwitchGame implements Comparable<SwitchGame> {
    public String name;
    public String type;
    public double rating;
    public SwitchGame(double rating, String name, String type) {
        this.name = name;
        this.type = type;
        this.rating = rating;
    }
    @Override
    public boolean equals(Object obj) {
        if(getClass() != obj.getClass()) {
            return false;
        }
        return this.name.equals(((SwitchGame)obj).name);
    }
    @Override
    public String toString() {
        return String.format("Name: %s%nType: %s%nRating: %.2f", name, type, rating);
    }

    public int compareTo(SwitchGame o) {
        return this.name.compareTo(o.name);
    }
}