import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Simulation {
    private List<Agent> agents;
    private SimulationSettings settings;
    private int currentRound;
    private int currentTurn;
    private int totalInteractions;
    private int totalCooperations;
    private List<String> roundResults;
    private boolean simulationRunning;
    private Random random = new Random();

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
        if (getNumberOfRounds() <= 0) {
            throw new IllegalStateException(
                    "Number of rounds must be positive, but was " + getNumberOfRounds() + ".");
        }
        if (getNumberOfTurns() <= 0) {
            throw new IllegalStateException(
                    "Number of turns must be positive, but was " + getNumberOfTurns() + ".");
        }
    }

    private boolean shouldStartNextRound() {
        boolean roundsRemain = currentRound < getNumberOfRounds();
        return simulationRunning && roundsRemain && hasRemainingAgents();
    }

    private boolean shouldStartNextTurn() {
        boolean turnsRemain = currentTurn < getNumberOfTurns();
        return simulationRunning && turnsRemain && hasRemainingAgents();
    }

    private boolean hasRemainingAgents() {
        return !agents.isEmpty();
    }

    // SimulationSettings fields are private, so read them through getParameters
    private int getNumberOfRounds() {
        return (int) settings.getParameters("numberOfRounds");
    }

    private int getNumberOfTurns() {
        return (int) settings.getParameters("numberOfTurns");
    }

    //One turn: selected agents pair up and interact
    public void executeTurn() {
        agentInteractions();
    }

    //selects agents that will interact this turn based on interaction probability
    public List<Agent> selectAgents(){
        double interactionProbability = settings.getParameters("interactionProbability");
        List<Agent> selected = new ArrayList<>();
        for (Agent agent : agents) {
            if (random.nextDouble() < interactionProbability) {
                selected.add(agent);
            }
        }
        Collections.shuffle(selected, random);
        return selected;
    }

    //applies behaviors, cost of helping, and benefit of receiving help to selected agents
    public void agentInteractions() {
        double helpCost = settings.getParameters("helpCost");
        double helpBenefit = settings.getParameters("helpBenefit");
        List<Agent> selected = selectAgents();

        // Pairs agents up two at a time; an odd agent out sits this turn out
        for (int i = 0; i + 1 < selected.size(); i += 2) {
            Agent first = selected.get(i);
            Agent second = selected.get(i + 1);
            first.interact(second);
            totalInteractions++;

            // An altruist pays the cost so the other agent gets the benefit
            if ("Altruist".equals(first.getBehavior())) {
                second.getHelp(first, -helpCost, helpBenefit);
                totalCooperations++;
            }
            if ("Altruist".equals(second.getBehavior())) {
                first.getHelp(second, -helpCost, helpBenefit);
                totalCooperations++;
            }
        }
    }

    //needs resource refresh rate
    public void refreshResources() {
        double refreshRate = settings.getParameters("resourceRefreshRate");
        for (Agent agent : agents) {
            agent.updateResources(refreshRate);
        }
    }

    //Manages population: agents that ran out of resources are removed
    public void population() {
        agents.removeIf(agent -> agent.getResources() <= 0);
    }

    //Saves a one-line summary of the round for the ResultsProcessor
    public void recordRoundResults() {
        int altruists = 0;
        int selfish = 0;
        double totalResources = 0;
        for (Agent agent : agents) {
            if ("Altruist".equals(agent.getBehavior())) {
                altruists++;
            } else {
                selfish++;
            }
            totalResources += agent.getResources();
        }
        double averageResources = agents.isEmpty() ? 0 : totalResources / agents.size();

        roundResults.add("Round " + currentRound
                + ": population=" + agents.size()
                + ", altruists=" + altruists
                + ", selfish=" + selfish
                + ", interactions=" + totalInteractions
                + ", cooperations=" + totalCooperations
                + ", avgResources=" + String.format("%.2f", averageResources));
    }

    public void stopSimulation() {
        simulationRunning = false;
    }

    // Returns round-by-round results for the ResultsProcessor
    public List<String> getRoundResults() {
        return roundResults;
    }

    public List<Agent> getAgents() {
        return agents;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public int getTotalInteractions() {
        return totalInteractions;
    }

    public int getTotalCooperations() {
        return totalCooperations;
    }

    public boolean isSimulationRunning() {
        return simulationRunning;
    }

    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.simulationRunning = true;
        UserInterface ui = new UserInterface();
    }
}
