package dias.wagner.kartrace;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public final class App {
    
    public static void main(String[] args) throws IOException {
        
        if (args.length != 1) {
            System.err.println("Especifique o log da corrida.");
            System.exit(1);
        }

        Path path = Paths.get(args[0]);
        
        try {
            KartRace kartRace = new KartRace(path);
            List<PilotStats> raceResults = kartRace.getRaceResults();

            raceResults.forEach(pilotStats -> System.out.println(pilotStats));
        } catch(IOException exception) {
            System.err.printf("Erro ao abrir o arquivo '%s'.\n", args[0]);
            System.exit(2);
        }
        
    }
}
