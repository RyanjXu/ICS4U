package assignment4;
import java.util.*;
public class TVShow {
    private String name;
    private String genre;
    private final ArrayList<Season> seasons = new ArrayList<Season>();
    private final Time length = new Time();
    private static int TVShowCount = 0;

    public TVShow(String name, String genre) {
        this.name = name;
        this.genre = genre;
        TVShowCount++;
    }

    public void add(Season season) {
        seasons.add(season);
        length.add(season.getLength());
    }

    
}
