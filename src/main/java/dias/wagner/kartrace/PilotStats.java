package dias.wagner.kartrace;

import java.time.LocalTime;

public class PilotStats implements Comparable<PilotStats> {
    private int position;
    private String pilotCode;
    private String pilotName;
    private int lapsCompleted;
    private LocalTime totalRaceTime;

    public PilotStats(LogLine logLine) {
        this.pilotCode = logLine.getPilotCode();
        this.pilotName = logLine.getPilotName();
        this.lapsCompleted = logLine.getLap();
        this.totalRaceTime = logLine.getLapTime();
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getPilotCode() {
        return this.pilotCode;
    }

    public void setPilotCode(String pilotCode) {
        this.pilotCode = pilotCode;
    }

    public String getPilotName() {
        return this.pilotName;
    }

    public void setPilotName(String pilotName) {
        this.pilotName = pilotName;
    }

    public int getLapsCompleted() {
        return this.lapsCompleted;
    }

    public void setLapsCompleted(int lapsCompleted) {
        this.lapsCompleted = lapsCompleted;
    }

    public LocalTime getTotalRaceTime() {
        return this.totalRaceTime;
    }

    public void setTotalRaceTime(LocalTime totalRaceTime) {
        this.totalRaceTime = totalRaceTime;
    }

    @Override
    public int compareTo(PilotStats other) {
        if (this.totalRaceTime.equals(other.totalRaceTime)) {
            return 0;
        }
        return this.totalRaceTime.isBefore(other.totalRaceTime) ? -1 : 1;
    }

    public String toString() {
        return String.format("%d %3s %-20s %d %12s",
            this.position, this.pilotCode, this.pilotName, this.lapsCompleted, this.totalRaceTime.toString());
    }
}