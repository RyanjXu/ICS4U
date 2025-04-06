package assignment4;

public class Time implements Comparable<Time> {
    private int hour;
    private int minute;
    private int second;

    public Time(String time) {
        this.hour = Integer.parseInt(time.substring(0, 2));
        this.minute = Integer.parseInt(time.substring(3, 5));
        this.second = Integer.parseInt(time.substring(6, 8));
    }
    public Time() { // Overloaded constructor for empty time
        this.hour = 0;
        this.minute = 0;
        this.second = 0;
    }

    public void setTime(String time) {
        this.hour = Integer.parseInt(time.substring(0, 2));
        this.minute = Integer.parseInt(time.substring(3, 5));
        this.second = Integer.parseInt(time.substring(6, 8));
    }
    public void add(Time time) {
        // sums
        int secondsSum = this.second + time.second;
        int minutesSum = this.minute + time.minute + secondsSum/60;
        int hoursSum = this.hour + time.hour + minutesSum/60;
        // handle overflows
        this.second = secondsSum%60;
        this.minute = minutesSum%60;
        this.hour = hoursSum;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // same reference
        if (obj == null || getClass() != obj.getClass()) return false; // null or different class

        Time other = (Time) obj;
        return this.hour == other.hour && this.minute == other.minute && this.second == other.second;
    }
    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d\n", hour, minute, second);
    }
    @Override
    public int compareTo(Time o) { // default sort order
        if (this.hour != o.hour) {
            return this.hour - o.hour;
        } else if (this.minute != o.minute) {
            return this.minute - o.minute;
        } else {
            return this.second - o.second;
        }
    }
}
