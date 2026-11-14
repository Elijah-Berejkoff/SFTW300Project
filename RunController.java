
public class RunController {
	private SimulationSettings settings;
	private SimulationSetup setup;
	private Simulation simulation;
	private ResultsProcessor resultsProcessor;
	private FileManager fileManager;
	private boolean simulationRunning;
	
	public RunController() {
		this.settings = new SimulationSettings();
		this.setup = new SimulationSetup(this.settings);
		this.resultsProcessor = new ResultsProcessor();
		this.fileManager = new FileManager();
		this.simulationRunning = false;
	}
	
	public void startSimulation() {
		if (validateParameters()) {
			this.setup.initializeSimulation();
			this.simulation = new Simulation(this.setup.getAgents(), this.settings);	// Creates a new simulation by getting the agents and parameters
			this.simulationRunning = true;
			System.out.println("Simulation started");
		} else {
			System.out.println("Failed to start simulation");
		}
	}
	
	public void reqStopSimulation() {
		if (this.simulationRunning && this.simulation != null) {	// Checks if the simulation is running and if the simulation object exists
			this.simulation.stopSimulation();	// stopSimulation being used from Simulation class
		}
		this.simulationRunning = false;
		System.out.println("Simulation stopped");
	}
	
	public void updateSettings() {
		
	}
	
	public boolean validateParameters() {
		if (this.settings != null) {					// Checks if there is settings from the SimulationSettings object
			return this.settings.validateSettings();
		}
		return false;
	}
	
	public void saveResults() {
		if (this.fileManager != null) {		// Validates if fileManager exists
			boolean resultSaved = this.fileManager.saveResults("Placeholder");	//Checks if results were saved with this line and the next
			if (resultSaved) {
				System.out.println("Results saved");
			} else {
				System.out.println("Error saving results");
			}
		}
	}
	
	public String loadResults() {
		if (this.fileManager != null) {
			return this.fileManager.loadResults();
		}
		return "Results could not be loaded";
	}
	
	public void analyzeResults() {
		if (this.resultsProcessor != null && this.simulation != null) {		// Checks if objects exist
			this.resultsProcessor.generateSummary();	// Organizes raw simulation data
		} else {
			System.out.println("Results could not be analyzed");
		}
	}
	
	public void resetData() {
		this.simulationRunning = false;
		this.simulation = null;
		if (this.setup != null) {
			this.setup.resetPopulation();
		}
		if (this.resultsProcessor != null) {
			this.resultsProcessor.clearResults();
		}
	}
	
	public void returnToMainMenu() {
		
	}
	
	public boolean isSimulationRunning() {
		return simulationRunning;
	}
	
	public void setSettings(SimulationSettings settings) {
		this.settings = settings;
	}
	
	public SimulationSettings getSettings() {
		return settings;
	}


}
