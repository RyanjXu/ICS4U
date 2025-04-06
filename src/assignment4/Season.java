package assignment4;

import java.util.*;

public class Season {
    private int seasonNumber;
    private final ArrayList<Episode> episodes = new ArrayList<>();
    private final Time length = new Time();

    public Season(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }
    public Time getLength() {
        return this.length;
    }


    public void add(Episode episode) {
        episodes.add(episode);
        length.add(episode.getLength());
    }
    public void sortByNum() {
        Collections.sort(this.episodes);
    }
    public void sortByTime() {
        Collections.sort(this.episodes, Episode.BY_TIME);
    }
    public void sortByTitle() {
        Collections.sort(this.episodes, Episode.BY_TITLE);
    }
    public void displayEpisodeTitles() {
        for (Episode episode : episodes) {
            System.out.println(episode.getTitle());
        }
    }

}
