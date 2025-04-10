package assignment4;

import java.util.*;

public class Season {
    private final int seasonNumber;
    private final ArrayList<Episode> episodes = new ArrayList<>();
    private final Time length = new Time();
    private final TVShow parent;
    private int watchedEpisodes = 0;
    private boolean watched = false;

    public Season(int seasonNumber, TVShow parent) {
        this.seasonNumber = seasonNumber;
        this.parent = parent;
    }
    public Time getLength() {
        return this.length;
    }
    public int getSize() {
        return this.episodes.size();
    }
    public int getWatchedEpisodes() {
        return this.watchedEpisodes;
    }
    public boolean isWatched() {
        return this.watched;
    }
    public int getSeasonNumber() {
        return this.seasonNumber;
    }
    public Episode getEpisode(int idx) {
        return this.episodes.get(idx);
    }

    public void add(Episode episode) {
        episodes.add(episode);
        length.add(episode.getLength());
        watchedEpisodes++;
    }
    public void watch() {
        watchedEpisodes--;
        this.parent.watchEpisode();
        if(watchedEpisodes == 0) {
            watched = true;
            this.parent.watchSeason();
        }
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
        int i = 1;
        for (Episode episode : episodes) {
            System.out.printf("%d. %s\n", i++, episode.getTitle());
        }
    }

}
