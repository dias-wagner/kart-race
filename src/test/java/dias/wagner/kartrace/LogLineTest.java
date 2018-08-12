package dias.wagner.kartrace;

import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;

import dias.wagner.kartrace.LogLine;

public class LogLineTest {

    @Test
    public void testToString() {
        LocalTime hour = LocalTime.of(10, 0);
        LocalTime lapTime = LocalTime.of(0, 1, 30);
        
        LogLine line = new LogLine(hour, "000", "W.DIAS", 1, lapTime, 3.14f);

        Assert.assertEquals("       10:00 000 W.DIAS               1     00:01:30 3,140", line.toString());
    }

    @Test
    public void testCompareToEqual() {
        LocalTime hour = LocalTime.of(10, 0);
        LocalTime lapTime = LocalTime.of(0, 1, 30);
        
        LogLine line1 = new LogLine(hour, "000", "W.DIAS", 1, lapTime, 3.14f);
        LogLine line2 = new LogLine(hour, "000", "W.DIAS", 1, lapTime, 3.14f);

        Assert.assertEquals(0, line1.compareTo(line2));
    }

    @Test
    public void testCompareToThis() {
        LocalTime hour = LocalTime.of(10, 0);
        LocalTime lapTime = LocalTime.of(0, 1, 30);
        
        LogLine line1 = new LogLine(hour, "000", "W.DIAS", 1, lapTime, 3.14f);
        LogLine line2 = new LogLine(hour, "000", "W.DIAS", 2, lapTime, 3.14f);

        Assert.assertEquals(-1, line1.compareTo(line2));
    }

    @Test
    public void testCompareToOther() {
        LocalTime hour = LocalTime.of(10, 0);
        LocalTime lapTime = LocalTime.of(0, 1, 30);
        
        LogLine line1 = new LogLine(hour, "001", "W.DIAS", 1, lapTime, 3.14f);
        LogLine line2 = new LogLine(hour, "000", "W.DIAS", 1, lapTime, 3.14f);

        Assert.assertEquals(1, line1.compareTo(line2));
    }
}