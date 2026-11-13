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
        
        for (int i = 0; i < population; i++) {
            this.agents.add(new Agent());
        }
        this.initialized = true;
        return true;
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
