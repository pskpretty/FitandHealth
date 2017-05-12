package fitandHealth;

import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;

public class PotentialAgents extends Agent {
	
	public PotentialAgents(ContinuousSpace<Object> space, Grid<Object> grid, int energy) {
		super(space, grid, energy);
	}
	
	@ScheduledMethod(start = 1, interval = 0.5)
	public void step() {
		if(isInviteReceived() || isWomReceived()){
			incrementEnergy();
		}
	}
}

