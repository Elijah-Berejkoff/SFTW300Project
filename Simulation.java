
public static class Simulation {
    List <Agent> agents;
    SimulationSettings settings;
    int currentRound;
    int currentTurn;
    int totalCooperations;
    List <String> roundResults;
    boolean simulationRunning;
    
}

public static void main(String[] args) {
    Simulation simulation = new Simulation();
    simulation.simulationRunning = true;
    UserInterface ui = new UserInterface();
}
    
