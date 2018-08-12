package dias.wagner.kartrace;

import org.junit.Assert;
import org.junit.Test;

import dias.wagner.kartrace.LogLine;
import dias.wagner.kartrace.LogLineParser;

public class LogLineParserTest {
    
    @Test
    public void testParseLine() {
        LogLineParser logLineParser = new LogLineParser();

        LogLine logLine = logLineParser.parseLine("23:49:08.277      038 – F.MASSA                           1\t\t1:02.852                        44,275");

        Assert.assertEquals("23:49:08.277", logLine.getHour().toString());
        Assert.assertEquals("038", logLine.getPilotCode());
        Assert.assertEquals("F.MASSA", logLine.getPilotName());
        Assert.assertEquals(1, logLine.getLap());
        Assert.assertEquals("00:01:02.852", logLine.getLapTime().toString());
        Assert.assertEquals(44.275, logLine.getAvgLapSpeed(), 0.0005);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testParseLineInvalid() {
        LogLineParser logLineParser = new LogLineParser();

        logLineParser.parseLine("xxx23:49:08.277      038 – F.MASSA                           1\t\t1:02.852                        44,275");
    }
}
