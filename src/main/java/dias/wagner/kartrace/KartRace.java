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

public class KartRace {
    
    private Stream<String> lines;
    private LogLineParser logLineParser;
    private Map<String, PilotStats> raceStats;

    public KartRace(Path raceLogPath) throws IOException {
        this.lines = Files.lines(raceLogPath);
        this.logLineParser = new LogLineParser();
        this.raceStats = new HashMap<>();
    }

    public List<PilotStats> getRaceResults() {
        System.out.printf("Processando arquivo de log da corrida...\n");
        lines
            .map(logLineParser::parseLine)
            .sorted()
            .forEach(logLine -> {
                if (raceStats.get(logLine.getPilotCode()) == null) {
                    raceStats.put(logLine.getPilotCode(), new PilotStats(logLine));
                } else {
                    PilotStats pilotStats = raceStats.get(logLine.getPilotCode());
                    pilotStats.setLapsCompleted(logLine.getLap());
                    pilotStats.setTotalRaceTime(pilotStats.getTotalRaceTime()
                        .plusMinutes(logLine.getLapTime().getMinute())
                        .plusSeconds(logLine.getLapTime().getSecond())
                        .plusNanos(logLine.getLapTime().getNano())
                    );
                }
            });

        AtomicInteger position = new AtomicInteger();

        return raceStats.values().stream()
            .sorted()
            .map((PilotStats pilotStats) -> { 
                pilotStats.setPosition(position.incrementAndGet()); 
                return pilotStats;
            })
            .collect(Collectors.toList());
    }
}