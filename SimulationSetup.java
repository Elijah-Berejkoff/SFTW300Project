import java.util.ArrayList;
import java.util.List;
public class SimulationSetup {
    private boolean initialized;
    private List<Agent> agents;
    SimulationSettings settings;
    public SimulationSetup(SimulationSettings settings) {
        this.initialized = false;
        this.agents = new ArrayList<>();
        this.settings = settings;
    }
    public boolean initialize() {
        if (this.settings == null || !this.settings.validateSettings()){
            return false;
        }
        int population = (int)this.settings.getParameters("populationSize");
        double initialResource = (double)this.settings.getParameters("initialResource");
        double altruisticCount = population*this.settings.getParameters("altruisticProportion");
        double selfishCount = population * this.settings.getParameters("selfishProportion");
        this.agents.clear();
        for (int i = 0; i < population; i++) {
            String behavior;
            if (i < altruisticCount) {
                behavior = "altruistic";
            } else if (i < altruisticCount + selfishCount) {
                behavior = "selfish";
            } else {
                behavior = "Reciprocal";
            }
                this.agents.add(new Agent(i, behavior, initialResource));
            }
        this.initialized = true;
        return true;
    }
    public boolean initializeSimulation() {
        return this.initialize();
    }
    public void resetPopulation() {
        this.agents.clear();
        this.initialized = false;
    }
    public boolean isInitialized() {
        return this.initialized;
    }
    
    public List<Agent> getAgents() {
        return this.agents;
    }
    
    public SimulationSettings getSettings() {
        return this.settings;
    }
}
