package dias.wagner.kartrace;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class KartRaceTest {

    @Test
    public void testRace() throws IOException {
        final String projectDir = System.getProperty("user.dir");
        KartRace kartRace = new KartRace(Paths.get(projectDir + "/target/classes/race-log.txt"));

        kartRace.runRace();

        List<PilotStats> raceResults = kartRace.getRaceResults();

        Assert.assertEquals(1, raceResults.get(0).getPosition());
        Assert.assertEquals("038", raceResults.get(0).getPilotCode());
        Assert.assertEquals("F.MASSA", raceResults.get(0).getPilotName());
        Assert.assertEquals(4, raceResults.get(0).getLapsCompleted());
        Assert.assertEquals("00:04:11.578", raceResults.get(0).getTotalRaceTime().toString());
        
        Assert.assertEquals(2, raceResults.get(1).getPosition());
        Assert.assertEquals("002", raceResults.get(1).getPilotCode());
        Assert.assertEquals("K.RAIKKONEN", raceResults.get(1).getPilotName());
        Assert.assertEquals(4, raceResults.get(1).getLapsCompleted());
        Assert.assertEquals("00:04:15.153", raceResults.get(1).getTotalRaceTime().toString());

        Assert.assertEquals(3, raceResults.get(2).getPosition());
        Assert.assertEquals("033", raceResults.get(2).getPilotCode());
        Assert.assertEquals("R.BARRICHELLO", raceResults.get(2).getPilotName());
        Assert.assertEquals(4, raceResults.get(2).getLapsCompleted());
        Assert.assertEquals("00:04:16.080", raceResults.get(2).getTotalRaceTime().toString());

        Assert.assertEquals(4, raceResults.get(3).getPosition());
        Assert.assertEquals("023", raceResults.get(3).getPilotCode());
        Assert.assertEquals("M.WEBBER", raceResults.get(3).getPilotName());
        Assert.assertEquals(4, raceResults.get(3).getLapsCompleted());
        Assert.assertEquals("00:04:17.722", raceResults.get(3).getTotalRaceTime().toString());

        Assert.assertEquals(5, raceResults.get(4).getPosition());
        Assert.assertEquals("015", raceResults.get(4).getPilotCode());
        Assert.assertEquals("F.ALONSO", raceResults.get(4).getPilotName());
        Assert.assertEquals(4, raceResults.get(4).getLapsCompleted());
        Assert.assertEquals("00:04:54.221", raceResults.get(4).getTotalRaceTime().toString());

        Assert.assertEquals(6, raceResults.get(5).getPosition());
        Assert.assertEquals("011", raceResults.get(5).getPilotCode());
        Assert.assertEquals("S.VETTEL", raceResults.get(5).getPilotName());
        Assert.assertEquals(3, raceResults.get(5).getLapsCompleted());
        Assert.assertEquals("00:06:27.276", raceResults.get(5).getTotalRaceTime().toString());

        Assert.assertEquals("23:51:14.216 038 F.MASSA              3 00:01:02.769 44,334", kartRace.getBestLap().toString());
    }

    @Test(expected=IllegalStateException.class)
    public void testGetRaceResultsNoRun() throws IOException {
        final String projectDir = System.getProperty("user.dir");
        KartRace kartRace = new KartRace(Paths.get(projectDir + "/target/classes/race-log.txt"));

        kartRace.getRaceResults();
    }

    @Test(expected=IllegalStateException.class)
    public void testGetBestLapNoRun() throws IOException {
        final String projectDir = System.getProperty("user.dir");
        KartRace kartRace = new KartRace(Paths.get(projectDir + "/target/classes/race-log.txt"));

        kartRace.getBestLap();
    }
}