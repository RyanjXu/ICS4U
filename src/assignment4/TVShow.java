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
    public int getSize() {
        return seasons.size();
    }
    public String getName() {
        return name;
    }
    public Season getSeason(int idx) {
        return seasons.get(idx);
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
    public void printSeasons() {
        for(int i =1 ; i<=seasons.size(); i++) {
            System.out.printf("%d. Season %d\n", i, seasons.get(i-1).getSeasonNumber());
        }
    }
    public void removeSeason(int idx) {
        Season s = seasons.get(idx);
        if(s.isWatched()) {
            seasonsWatched--;
        }
        episodesUnwatched-=s.getSize()-s.getWatchedEpisodes();
        length.subtract(s.getLength());
        seasons.remove(idx);
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
