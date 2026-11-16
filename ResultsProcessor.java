import java.util.List;
import java.util.ArrayList;


public class ResultsProcessor {
   private Simulation simulation;
   private SimulationSettings settings;

   
   public void setSimulation(Simulation simulation, SimulationSettings settings) {
	   this.simulation = simulation;
	   this.settings = settings;
   }
   
   public double calculateCooperationRate() {
	   if (simulation == null || simulation.getTotalInteractions() == 0) {
		   return 0;
	   }
	   
	   return (double) simulation.getTotalCooperations() / (simulation.getTotalInteractions() * 2);
   }
   
   public double calculateSurvivalRate() {
	   if (simulation == null || settings == null) {
		   return 0;
	   }
	   
	   double originalPopulation = settings.getParameters("populationSize");
	   
	   if (originalPopulation == 0) {
		   return 0;
	   }
	   
	   return simulation.getAgents().size() / originalPopulation;
   }
   
   public List<Double> calculateResourceDistribution() {
	    List<Double> resources = new ArrayList<>();

	    if (simulation == null) {
	        return resources;
	    }

	    for (Agent agent : simulation.getAgents()) {
	        resources.add(agent.getResources());
	    }

	    return resources;
	}
	
   public List<Double> calculateIndividualScores() {
	   List<Double> scores = new ArrayList<>();
	   
	   if (simulation == null) {
		   return scores;
	   }
	   
	   for (Agent agent : simulation.getAgents()) {
		   scores.add(agent.calculateScore());
	   }
	   
	   return scores;
   }
   
   public double calculateGroupScores() {
	   double groupScore = 0;
	   
	   for (double score : calculateIndividualScores()) {
		   groupScore += score;
	   }
	   
	   return groupScore;
   }
   
   public String summarizeBehaviorOutcomes() {
	   if (simulation == null) {
		   return "No simulation data available.";
	   }
	   
	   int altruists = 0;
	   int selfish = 0;
	   int reciprocal = 0;
	   
	   for (Agent agent : simulation.getAgents()) {
		   String behavior = agent.getBehavior();
		   
		   if (behavior.equals("Altruist")) {
			   altruists ++;
		   } else if (behavior.equals("Selfish")) {
			   selfish ++;
		   } else if (behavior.equals("Reciprocal")) {
			   reciprocal++;
		   }
	   }
	   
	   return "Altruists: " + altruists 
			   + "\nSelfish: " + selfish
			   + "\nReciprocal: " + reciprocal;
   }
   
   public List<String> calculatePopulationTrends() {
	   if (simulation == null) {
		   return new ArrayList<>();
	   }
	   
	   return simulation.getRoundResults();
   }
   
   public String generateSummary() {
	   if (simulation == null || settings == null) {
		   return "No simulation data available.";
	   }
	   
	   String summary = "Simulation Results\n";
	   summary += "-------------------------\n";
	   summary += "Cooperation Rate: " + (calculateCooperationRate() * 100) + "%\n";
	   summary += "Survival Rate: " + (calculateSurvivalRate() * 100) + "%\n";
	   summary += "Group Score: " + calculateGroupScores() + "\n";
	   summary += summarizeBehaviorOutcomes() + "\n";
	   
	   return summary;
   }
   
   public String getResultsForExport() {
	   if (simulation == null || settings == null) {
	        return "No simulation data available.";
	    }

	    String results = "Simulation Parameters\n";
	    results += settings.getSettingsSummary() + "\n";

	    results += generateSummary() + "\n";

	    results += "Round Results\n";
	    results += "------------------------\n";

	    for (String round : simulation.getRoundResults()) {
	        results += round + "\n";
	    }

	    return results;
   }
   
   public void clearResults() {
	   simulation = null;
	   settings = null;
   }
}
