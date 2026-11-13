import java.util.ArrayList;
import java.util.List;


public class Simulation {
    private List<Agent> agents;
    private SimulationSettings settings;
    private int currentRound;
    private int currentTurn;
    private int totalInteractions;
    private int totalCooperations;
    private List<String> roundResults;
    private boolean simulationRunning;

    public Simulation() {
        this.agents = new ArrayList<>();
        this.roundResults = new ArrayList<>();
    }

    public Simulation(List<Agent> agents, SimulationSettings settings) {
        this();
        this.agents = agents;
        this.settings = settings;
    }

    //Runs the given number of rounds and throws an illigal state if anything is wrong or missing
    public void runSimulation() {
        validateReadyToRun();

        currentRound = 0;
        totalInteractions = 0;
        totalCooperations = 0;
        roundResults.clear();
        simulationRunning = true;

        while (shouldStartNextRound()) {
            currentRound++;
            executeRound();
        }

        simulationRunning = false;
    }

//Runs 1 round and throws illigal state if somehting is wrong
    public void executeRound() {
        validateReadyToRun();

        currentTurn = 0;
        while (shouldStartNextTurn()) {
            currentTurn++;
            executeTurn();
        }

        population();
        refreshResources();
        recordRoundResults();
    }

 //Checks if everything is there
    private void validateReadyToRun() {
        if (settings == null) {
            throw new IllegalStateException("Simulation settings have not been provided.");
        }
        if (agents == null) {
            throw new IllegalStateException("Simulation agents have not been provided.");
        }
        if (settings.numberOfRounds <= 0) {
            throw new IllegalStateException(
                    "Number of rounds must be positive, but was " + settings.numberOfRounds + ".");
        }
        if (settings.numberOfTurns <= 0) {
            throw new IllegalStateException(
                    "Number of turns must be positive, but was " + settings.numberOfTurns + ".");
        }
    }

    private boolean shouldStartNextRound() {
        boolean roundsRemain = currentRound < settings.numberOfRounds;
        return simulationRunning && roundsRemain && hasRemainingAgents();
    }

    private boolean shouldStartNextTurn() {
        boolean turnsRemain = currentTurn < settings.numberOfTurns;
        return simulationRunning && turnsRemain && hasRemainingAgents();
    }

    private boolean hasRemainingAgents() {
        return !agents.isEmpty();
    }

    public void executeTurn() {
    }

    //selects agents that will interact this turn based on interaction probability
    public List<Agent> selectAgents(){
        return new ArrayList<>();
    }

    //applies behaviors, cost of helping, and benefit of receiving help to selected agents
    public void agentInteractions() {
    }

    //needs resource refresh rate
    public void refreshResources() {
    }

    //Manages population
    public void population() {
    }

    public void recordRoundResults() {
    }

    public void stopSimulation() {
    }

    // Returns round-by-round results for the ResultsProcessor
    public List<String> getRoundResults() {
        //TODO
        return roundResults;
    }

    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.simulationRunning = true;
        UserInterface ui = new UserInterface();
    }
}
