package assignment4;
import java.util.*;
public class TVShow {
    private final String name;
    private final String genre;
    private final ArrayList<Season> seasons = new ArrayList<Season>();
    private final Time length = new Time();
    private static int TVShowCount = 0;
    private int seasonsWatched = 0;
    private int episodesUnwatched = 0;

    public TVShow(String name, String genre) {
        this.name = name;
        this.genre = genre;
        TVShowCount++;
    }

    public void add(Season season) {
        seasons.add(season);
        length.add(season.getLength());
        episodesUnwatched+=season.getSize();
    }
    public void watchEpisode() {
        episodesUnwatched--;
    }
    public void watchSeason() {
        seasonsWatched++;
    }
    public void displayStatus() {
        for(Season season : seasons) {
            System.out.printf("%d Episodes watched out of %d.\n", season.getWatchedEpisodes(), season.getSize());
        }
        System.out.printf("%d seasons completely watched.\n", seasonsWatched);
        System.out.printf("%d episodes unwatched.\n", episodesUnwatched);
    }
    @Override
    public String toString() {
        return String.format("Title: %s%nGenre: %s%nNum of seasons: %s%nTotal Time: %s%n", name, genre, seasons.size(), length);
    }
    
}
