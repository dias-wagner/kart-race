package dias.wagner.kartrace;

import java.time.LocalTime;

/**
 * Represents the pilot stats.
 */
public class PilotStats implements Comparable<PilotStats> {
    private int position;
    private String pilotCode;
    private String pilotName;
    private int lapsCompleted;
    private LocalTime totalRaceTime;
    private int bestLap;
    private LocalTime bestLapTime;
    private float avgSpeed;

    /**
     * Constructor. Initializes the object from a log line. The idea is to use the first line
     * of a pilot to construct this object in order to correctly compute the race results.
     * 
     * @param logLine a log line
     */
    public PilotStats(LogLine logLine) {
        this.pilotCode = logLine.getPilotCode();
        this.pilotName = logLine.getPilotName();
        this.lapsCompleted = logLine.getLap();
        this.totalRaceTime = logLine.getLapTime();
        this.bestLap = logLine.getLap();
        this.bestLapTime = logLine.getLapTime();
        this.avgSpeed = logLine.getAvgLapSpeed();
    }

    /**
     * @return the finish position
     */
    public int getPosition() {
        return this.position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * @return the pilot code
     */
    public String getPilotCode() {
        return this.pilotCode;
    }

    /**
     * @param pilotCode the pilot code to set
     */
    public void setPilotCode(String pilotCode) {
        this.pilotCode = pilotCode;
    }

    /**
     * @return the pilot name
     */
    public String getPilotName() {
        return this.pilotName;
    }

    /**
     * @param pilotName the pilot name to set
     */
    public void setPilotName(String pilotName) {
        this.pilotName = pilotName;
    }

    /**
     * @return laps completed
     */
    public int getLapsCompleted() {
        return this.lapsCompleted;
    }

    /**
     * @param lapsCompleted number of laps completed to set
     */
    public void setLapsCompleted(int lapsCompleted) {
        this.lapsCompleted = lapsCompleted;
    }

    /**
     * @return the total race time
     */
    public LocalTime getTotalRaceTime() {
        return this.totalRaceTime;
    }

    /**
     * @param totalRaceTime the total race time to set
     */
    public void setTotalRaceTime(LocalTime totalRaceTime) {
        this.totalRaceTime = totalRaceTime;
    }

    /**
     * @return the best lap
     */
    public int getBestLap() {
        return this.bestLap;
    }

    /**
     * @param bestLap the best lap to set
     */
    public void setBestLap(int bestLap) {
        this.bestLap = bestLap;
    }

    /**
     * @return the best lap time
     */
    public LocalTime getBestLapTime() {
        return this.bestLapTime;
    }

    /**
     * @param bestLapTime the best lap time to set
     */
    public void setBestLapTime(LocalTime bestLapTime) {
        this.bestLapTime = bestLapTime;
    }

    /**
     * @return the average speed
     */
    public float getAvgSpeed() {
        return avgSpeed;
    }

    /**
     * @param avgSpeed the average speed to set
     */
    public void setAvgSpeed(float avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    /**
     * The {@link Comparable#compareTo(PilotStats)} implementation, ordering by {@link #getTotalRaceTime()}.
     * 
     * @param other the {@link PilotStats} object to compare to.
     */
    @Override
    public int compareTo(PilotStats other) {
        if (this.lapsCompleted > other.lapsCompleted) {
            return -1;
        }
        
        if (this.lapsCompleted < other.lapsCompleted) {
            return 1;
        }

        if (this.totalRaceTime.equals(other.totalRaceTime)) {
            return 0;
        }
        return this.totalRaceTime.isBefore(other.totalRaceTime) ? -1 : 1;
    }

    /**
     * @return a string representing these pilot stats in the order {@link #getPosition()}, {@link #getPilotCode()}, 
     * {@link #getPilotName()}, {@link #getLapsCompleted()}, {@link #getTotalRaceTime()}, {@link #getBestLap()},
     * {@link #getBestLapTime()}, {@link #getAvgSpeed()}.
     */
    public String toString() {
        return String.format("%d %3s %-20s %d %12s %d %12s %.3f",
            this.position, this.pilotCode, this.pilotName, this.lapsCompleted, this.totalRaceTime.toString(), 
            this.bestLap, this.bestLapTime.toString(), this.avgSpeed);
    }

    /**
     * @param winnerRaceTime the winner race time
     * @return a string representing these pilot stats in the order {@link #getPosition()}, {@link #getPilotCode()}, 
     * {@link #getPilotName()}, {@link #getLapsCompleted()}, {@link #getTotalRaceTime()}, {@link #getBestLap()},
     * {@link #getBestLapTime()}, {@link #getAvgSpeed()}, and the time diff from the winner.
     */
    public String toString(LocalTime winnerRaceTime) {
        LocalTime diff = LocalTime.of(
            this.totalRaceTime.getHour(),
            this.totalRaceTime.getMinute(),
            this.totalRaceTime.getSecond(),
            this.totalRaceTime.getNano()
        );

        diff = diff
            .minusHours(winnerRaceTime.getHour())
            .minusMinutes(winnerRaceTime.getMinute())
            .minusSeconds(winnerRaceTime.getSecond())
            .minusNanos(winnerRaceTime.getNano());
        
        return this.toString() + " +" + diff;
    }
}