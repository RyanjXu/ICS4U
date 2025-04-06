package assignment4;

import java.util.Comparator;

public class Episode implements Comparable<Episode> {
    private final String title;
    private final int episodeNumber;
    private boolean watched = false;
    private final Time length;

    public Episode(String title, int episodeNumber, Time length) { // Constructor
        this.title = title;
        this.episodeNumber = episodeNumber;
        this.length = length;
    }
    // getters
    public Time getLength() {
        return this.length;
    }
    public String getTitle() {
        return this.title;
    }

    public void watch() {
        this.watched = true;
    }
    @Override
    public String toString() {
        return String.format("Title: %s%nEpisode #: %d%nLength: %s%nWatched: %b%n", title, episodeNumber, length, watched);
    }
    @Override
    public int compareTo(Episode episode) { // default sort order by episode number
        return Integer.compare(this.episodeNumber, episode.episodeNumber);
    }
    // Static nested Comparators
    private static class sortByTitle implements Comparator<Episode> {
        @Override
        public int compare(Episode o1, Episode o2) {
            return o1.title.compareTo(o2.title);
        }
    }
    private static class sortByTime implements Comparator<Episode> {
        @Override
        public int compare(Episode o1, Episode o2) {
            return o1.length.compareTo(o2.length);
        }
    }
    // public fields for access
    public static final Comparator<Episode> BY_TITLE = new sortByTitle();
    public static final Comparator<Episode> BY_TIME = new sortByTime();
}

