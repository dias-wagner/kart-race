
package dias.wagner.kartrace;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import org.junit.Assert;
import org.junit.Test;

public class PilotStatsTest {

    @Test
    public void testGettersAndSetters() {
        LocalTime hour = LocalTime.of(10, 0);
        LocalTime lapTime = LocalTime.of(0, 1, 30);
        
        LogLine line = new LogLine(hour, "000", "W.DIAS", 1, lapTime, 3.14f);

        PilotStats stats = new PilotStats(line);

        stats.setPosition(1);
        stats.setPilotCode("111");
        stats.setPilotName("J.AFONSO");
        stats.setLapsCompleted(4);
        stats.setTotalRaceTime(hour);

        Assert.assertEquals(1, stats.getPosition());
        Assert.assertEquals("111", stats.getPilotCode());
        Assert.assertEquals("J.AFONSO", stats.getPilotName());
        Assert.assertEquals(4, stats.getLapsCompleted());
        Assert.assertEquals(hour, stats.getTotalRaceTime());
        Assert.assertEquals(line.getLap(), stats.getBestLap());
        Assert.assertEquals(line.getLapTime(), stats.getBestLapTime());
    }

    @Test
    public void testToString() {
        LocalTime hour = LocalTime.of(10, 0);
        LocalTime lapTime = LocalTime.of(0, 1, 30);
        
        LogLine line = new LogLine(hour, "000", "W.DIAS", 1, lapTime, 3.14f);

        PilotStats stats = new PilotStats(line);

        Assert.assertEquals("0 000 W.DIAS               1     00:01:30 1     00:01:30", stats.toString());
    }

    @Test
    public void testCompareToEqual() {
        LocalTime hour = LocalTime.of(10, 0);
        LocalTime lapTime = LocalTime.of(0, 1, 30);
        
        LogLine line = new LogLine(hour, "000", "W.DIAS", 1, lapTime, 3.14f);

        PilotStats stats1 = new PilotStats(line);
        PilotStats stats2 = new PilotStats(line);
        
        Assert.assertEquals(0, stats1.compareTo(stats2));
    }

    @Test
    public void testCompareToThis() {
        LocalTime hour = LocalTime.of(10, 0);
        LocalTime lapTime = LocalTime.of(0, 1, 30);
        
        LogLine line1 = new LogLine(hour, "000", "W.DIAS", 1, lapTime, 3.14f);
        LogLine line2 = new LogLine(hour, "000", "W.DIAS", 1, lapTime.plus(1, ChronoUnit.MINUTES), 3.14f);

        PilotStats stats1 = new PilotStats(line1);
        PilotStats stats2 = new PilotStats(line2);
        
        Assert.assertEquals(-1, stats1.compareTo(stats2));
    }

    @Test
    public void testCompareToOther() {
        LocalTime hour = LocalTime.of(10, 0);
        LocalTime lapTime = LocalTime.of(0, 1, 30);
        
        LogLine line1 = new LogLine(hour, "000", "W.DIAS", 1, lapTime, 3.14f);
        LogLine line2 = new LogLine(hour, "000", "W.DIAS", 1, lapTime.minus(1, ChronoUnit.MINUTES), 3.14f);

        PilotStats stats1 = new PilotStats(line1);
        PilotStats stats2 = new PilotStats(line2);
        
        Assert.assertEquals(1, stats1.compareTo(stats2));
    }
}