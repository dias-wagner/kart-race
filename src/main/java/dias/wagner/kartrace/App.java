package dias.wagner.kartrace;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

/**
 * Application entry point. 
 */
public final class App {
    
    /**
     * @param args expects the race log path as an argument.
     */
    public static void main(String[] args) throws IOException {
        
        if (args.length != 1) {
            System.err.println("Especifique o log da corrida.");
            System.exit(1);
        }

        try {
            KartRace kartRace = new KartRace(Paths.get(args[0]));

            kartRace.runRace();

            List<PilotStats> raceResults = kartRace.getRaceResults();
            
            System.out.println("Resultado da corrida:");
            raceResults.forEach(pilotStats -> System.out.println(pilotStats));

            System.out.println("\nMelhor volta da corrida:");
            System.out.println(kartRace.getBestLap());
        } catch(IOException exception) {
            System.err.printf("Erro ao abrir o arquivo '%s'.\n", args[0]);
            System.exit(2);
        }
        
    }
}
