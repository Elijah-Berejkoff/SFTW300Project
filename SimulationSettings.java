public class SimulationSettings {
   private int populationSize;
   private int numberOfRounds;
   private int numberOfTurns;
   private double altruisticProportion;
   private double selfishProportion;
   private double reciprocalProportion;
   private double helpCost;
   private double helpBenefit;
   private double interactionProbability;
   private double resourceRefreshRate;
   private double initialResources;

   public boolean validateSettings() {
      double totalProportion = altruisticProportion + selfishProportion + reciprocalProportion;
      if (this.populationSize > 0
         && this.numberOfRounds > 0
         && this.numberOfTurns > 0 
         && this.isValidProportion(this.altruisticProportion)
         && this.isValidProportion(this.selfishProportion)
         && this.isValidProportion(this.reciprocalProportion)
         && this.isValidProportion(this.interactionProbability)
         && this.helpCost >= 0
         && this.helpBenefit >= 0
         && this.resourceRefreshRate >= 0
         && this.initialResources >= 0
         && Math.abs(totalProportion-1.0) <0.001) {
         return true;
      }else {
         return false;
      }
   }
public boolean validateParameter(String name, double value) {
      if (name == null || !Double.isFinite(value)) {
         return false;
      }
      if (name.equals("populationSize") 
            || name.equals("numberOfRounds") 
            || name.equals("numberOfTurns")) {
         if (value > 0 && value == Math.floor(value)) {
            return true;
         }else{
            return false;
         }
      }
      if (name.equals("altruisticProportion") 
            || name.equals("selfishProportion")
            || name.equals("reciprocalProportion") 
            || name.equals("interactionProbability")) {
         if (value >= 0 && value <= 1) {
            return true;
         }else{
            return false;
         }
      }
      if (name.equals("helpCost") 
            || name.equals("helpBenefit")
            || name.equals("resourceRefreshRate") 
            || name.equals("initialResources")) {
         if (value >= 0) {
            return true;
         }else{
            return false;
         }
      }
      return false;
   }
   private boolean isValidProportion(double value) {
      if (Double.isFinite(value)
            && value >= 0
            && value <= 1) {
         return true;
      } else {
         return false;
      }
   }
   public boolean updateParameter(String name, double value) {
      if (!this.validateParameter(name, value)) {
         return false;
      }
 
      if (name.equals("populationSize")) {
         this.populationSize = (int) value;
      } else if (name.equals("numberOfRounds")) {
         this.numberOfRounds = (int) value;
      } else if (name.equals("numberOfTurns")) {
         this.numberOfTurns = (int) value;
      } else if (name.equals("altruisticProportion")) {
         this.altruisticProportion = value;
      } else if (name.equals("selfishProportion")) {
         this.selfishProportion = value;
      } else if (name.equals("reciprocalProportion")) {
         this.reciprocalProportion = value;
      } else if (name.equals("helpCost")) {
         this.helpCost = value;
      } else if (name.equals("helpBenefit")) {
         this.helpBenefit = value;
      } else if (name.equals("interactionProbability")) {
         this.interactionProbability = value;
      } else if (name.equals("resourceRefreshRate")) {
         this.resourceRefreshRate = value;
      } else if (name.equals("initialResources")) {
         this.initialResources = value;
      } else {
         return false; 
      }
      return true;
   }
 
   public double getParameters(String name) {
      if (name==null) {
         return -1;
      }else if (name.equals("populationSize")) {
         return this.populationSize;
      } else if (name.equals("numberOfRounds")) {
         return this.numberOfRounds;
      } else if (name.equals("numberOfTurns")) {
         return this.numberOfTurns;
      } else if (name.equals("altruisticProportion")) {
         return this.altruisticProportion;
      } else if (name.equals("selfishProportion")) {
         return this.selfishProportion;
      } else if (name.equals("reciprocalProportion")) {
         return this.reciprocalProportion;
      } else if (name.equals("helpCost")) {
         return this.helpCost;
      } else if (name.equals("helpBenefit")) {
         return this.helpBenefit;
      } else if (name.equals("interactionProbability")) {
         return this.interactionProbability;
      } else if (name.equals("resourceRefreshRate")) {
         return this.resourceRefreshRate;
      } else if (name.equals("initialResources")) {
         return this.initialResources;
      }
      return -1; 
   }
 
   public String getSettingsSummary() {
      return "Population size: " + this.populationSize + "\n"
            + "Rounds: " + this.numberOfRounds + "\n"
            + "Turns: " + this.numberOfTurns + "\n"
            + "Altruistic: " + this.altruisticProportion + "\n"
            + "Selfish: " + this.selfishProportion + "\n"
            + "Reciprocal: " + this.reciprocalProportion + "\n"
            + "Cost: " + this.helpCost + "\n"
            + "Benefit: " + this.helpBenefit + "\n"
            + "Interaction rate: " + this.interactionProbability + "\n"
            + "Refresh rate: " + this.resourceRefreshRate + "\n"
            + "Initial resources: " + this.initialResources + "\n";
   }
}
