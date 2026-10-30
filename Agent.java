
public class Agent {
	private int agentID;
	private String behaviorType;
	private boolean isAlive;
	private double resources;
	private int cooperationCount;
	private int interactionCount;
	private double indiviudalScore;

	public Agent(int agentID, String behaviorType, double resources) {
		this.agentID = agentID;
		this.behaviorType = behaviorType;
		this.isAlive = true;
		this.resources = resources;
		this.cooperationCount = 0;
		this.interactionCount = 0;
		this.indiviudalScore = 0.0;
	}

	public void interact(Agent other) {
		if (!this.isAlive || !other.isAlive)
			return; // checks if both agents interacting are available

		this.interactionCount++;
		other.interactionCount++;

		String agentChoice = this.decideBehavior();
		String otherChoice = other.decideBehavior(); // updates agents choices and interaction

	}

	public String decideBehavior() {
		if (this.interactionCount == 0) {
			return this.behaviorType;
		}

		double cooperationRate;
		cooperationRate = this.cooperationCount / this.interactionCount;

		if (cooperationRate >= 0.5) {
			return this.behaviorType = "Selfish";
		} else
			return this.behaviorType = "Altruist";

	}

	public void getHelp(Agent other, double cost, double benefit) {
		other.updateResources(cost);
		this.updateResources(benefit);
		this.cooperationCount++;
	}

	public void updateResources(double amount) {
		this.resources += amount;
	}

	public boolean checkSurvival() {
		if (this.resources > 0.0)
			; // checks if agent has enough resources to still be available
		return this.isAlive = true;
	}

	public double calculateScore() {
		this.indiviudalScore = this.resources + (this.cooperationCount * 2); // 2 is a temporary value for now
		return this.indiviudalScore;
	}

	public String getBehavior() {
		return this.behaviorType;
	}

	public double getResources() {
		return this.resources;
	}
}
