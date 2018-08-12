package dias.wagner.kartrace;

import java.time.LocalTime;

/**
 * Represents a race log line.
 */
public class LogLine implements Comparable<LogLine> {
    private LocalTime hour;
    private String pilotCode;
    private String pilotName;
    private int lap;
    private LocalTime lapTime;
    private float avgLapSpeed;

    /**
     * @param hour moment in which a pilot completes a lap
     * @param pilotCode pilot code. Must be unique among pilots.
     * @param pilotName pilot name.
     * @param lap number of the completed lap.
     * @param lapTime minute, second and nano of this {@link LocalTime} represent the completed lap time.
     * @param avgLapSpeed the average lap speed
     */
    public LogLine(LocalTime hour, String pilotCode, String pilotName, int lap, LocalTime lapTime, float avgLapSpeed) {
        this.hour = hour;
        this.pilotCode = pilotCode;
        this.pilotName = pilotName;
        this.lap = lap;
        this.lapTime = lapTime;
        this.avgLapSpeed = avgLapSpeed;
    }

    /**
     * @return moment in which a pilot completes a lap
     */
    public LocalTime getHour() {
        return this.hour;
    }

    /**
     * @return pilot code
     */
    public String getPilotCode() {
        return this.pilotCode;
    }

    /**
     * @return  pilot name
     */
    public String getPilotName() {
        return this.pilotName;
    }

    /**
     * @return number of the completed lap
     */
    public int getLap() {
        return this.lap;
    }

    /**
     * @return A {@link LocalTime} with minute, second and nano representing the completed lap time.
     */
    public LocalTime getLapTime() {
        return this.lapTime;
    }

    /**
     * @return the average lap speed
     */
    public float getAvgLapSpeed() {
        return this.avgLapSpeed;
    }

    /**
     * @return a string representing this log line in the order {@link #getHour()}, {@link #getPilotCode()}, 
     * {@link #getPilotName()}, {@link #getLap()}, {@link #getLapTime()}, {@link #getAvgLapSpeed()}.
     */
    public String toString() {
        return String.format("%12s %3s %-20s %d %12s %.3f",
            hour.toString(), pilotCode, pilotName, lap, lapTime.toString(), avgLapSpeed);
    }

    /**
     * The {@link Comparable#compareTo(LogLine)} implementation, ordering by {@link #getPilotCode()} and {@link #getLap()}.
     * 
     * @param other the {@link LogLine} object to compare to.
     */
    @Override
    public int compareTo(LogLine other) {
        if (this.pilotCode != other.pilotCode) {
            return this.pilotCode.compareTo(other.pilotCode);
        } 

        return Integer.compare(this.lap, other.lap);
    }
}