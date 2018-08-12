package dias.wagner.kartrace;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class handles the full processing of the race log and computation of results.
 */
public class KartRace {
    
    /** Stream of raw lines */
    private Stream<String> lines;

    private LogLineParser logLineParser;
    
    /** Maps pilot codes to pilot race stats */
    private Map<String, PilotStats> raceStats;

    /** 
     * Constructor. Initialization of structures. 
     * 
     * @param raceLogPath path to race log
     */
    public KartRace(Path raceLogPath) throws IOException {
        this.lines = Files.lines(raceLogPath);
        this.logLineParser = new LogLineParser();
        this.raceStats = new HashMap<>();
    }

    /** 
     * Get the race results. 
     * 
     * @return the list of the pilot race stats, ordered by position
     */
    public List<PilotStats> getRaceResults() {
        System.out.printf("Processando arquivo de log da corrida...\n");
        lines
            // here we parse the log lines to LogLine objects
            .map(logLineParser::parseLine)
            
            // here we sort the LogLines
            .sorted()
            
            // here we compute the race stats from the sorted LogLines
            .forEach(logLine -> {
                if (raceStats.get(logLine.getPilotCode()) == null) {
                    // create the PilotStats for the current pilot
                    raceStats.put(logLine.getPilotCode(), new PilotStats(logLine));
                } else {
                    // update the PilotStats for the current pilot
                    PilotStats pilotStats = raceStats.get(logLine.getPilotCode());
                    
                    // since we receive the logLines in order, lapsCompleted must receive the current lap, ...
                    pilotStats.setLapsCompleted(logLine.getLap());
                    // ... the current lap time to pilot's total race time...
                    pilotStats.setTotalRaceTime(pilotStats.getTotalRaceTime()
                        .plusMinutes(logLine.getLapTime().getMinute())
                        .plusSeconds(logLine.getLapTime().getSecond())
                        .plusNanos(logLine.getLapTime().getNano())
                    );
                    // ... and verify if this lap is the pilot's best lap
                    if (logLine.getLapTime().isBefore(pilotStats.getBestLapTime())) {
                        pilotStats.setBestLap(logLine.getLap());
                        pilotStats.setBestLapTime(logLine.getLapTime());
                    }
                }
            });

        
        AtomicInteger position = new AtomicInteger();

        return raceStats.values().stream()
            // sort pilot stats based on natural order of ascending total race time
            .sorted()
            // update position
            .map((PilotStats pilotStats) -> { 
                pilotStats.setPosition(position.incrementAndGet()); 
                return pilotStats;
            })
            .collect(Collectors.toList());
    }
}