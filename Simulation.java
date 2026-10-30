import java.util.List;
public class runSimulation(){
    public static void main(String[] args) {
        new Simulation();
        boolean SimulationRunning = true;
    }
    
}
public class Simulation {
    List <Agent> agents;
    SimulationSettings settings;
    int currentRound;
    int currentTurn;
    int totalCooperations;
    List <String> roundResults;
    boolean simulationRunning;
    
}
