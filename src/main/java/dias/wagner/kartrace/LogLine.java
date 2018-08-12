package dias.wagner.kartrace;

import java.time.LocalTime;

public class LogLine implements Comparable<LogLine> {
    private LocalTime hour;
    private String pilotCode;
    private String pilotName;
    private int lap;
    private LocalTime lapTime;
    private float avgLapSpeed;

    public LogLine(LocalTime hour, String pilotCode, String pilotName, int lap, LocalTime lapTime, float avgLapSpeed) {
        this.hour = hour;
        this.pilotCode = pilotCode;
        this.pilotName = pilotName;
        this.lap = lap;
        this.lapTime = lapTime;
        this.avgLapSpeed = avgLapSpeed;
    }

    public LocalTime getHour() {
        return this.hour;
    }

    public String getPilotCode() {
        return this.pilotCode;
    }

    public String getPilotName() {
        return this.pilotName;
    }

    public int getLap() {
        return this.lap;
    }

    public LocalTime getLapTime() {
        return this.lapTime;
    }

    public float getAvgLapSpeed() {
        return this.avgLapSpeed;
    }

    public String toString() {
        return String.format("%12s %3s %-20s %d %12s %.3f",
            hour.toString(), pilotCode, pilotName, lap, lapTime.toString(), avgLapSpeed);
    }

    @Override
    public int compareTo(LogLine other) {
        if (this.pilotCode != other.pilotCode) {
            return this.pilotCode.compareTo(other.pilotCode);
        } 

        return Integer.compare(this.lap, other.lap);
    }
}