package fitandHealth;

import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;

public class Agent {
	private ContinuousSpace<Object> space;
	private Grid<Object> grid;
	private double currentEnergy;
	private boolean inviteReceived;
	private boolean womReceived;
	static final private double INVITE_ENERGY = 1.0;
	
	public Agent(ContinuousSpace<Object> space, Grid<Object> grid, int energy) {
		this.space = space;
		this.grid = grid;
		this.currentEnergy = energy;
	}
	
	public void receiveInvite(Club club) {
		inviteReceived = true;
	}
	
	public void receiveWom(ConsumerAgent agent) {
		inviteReceived = true;
	}
	
	public boolean isInviteReceived() {
		return inviteReceived;
	}
	
	public boolean isWomReceived() {
		return womReceived;
	}
	
	public void incrementEnergy() {
		currentEnergy += INVITE_ENERGY;
		inviteReceived = false;
		womReceived = false;
	}
	
	public double getCurrentEnergy() {
		return currentEnergy;
	}
}
