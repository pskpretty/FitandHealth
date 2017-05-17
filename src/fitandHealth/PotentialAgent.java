package fitandHealth;

import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;

public class PotentialAgent extends Agent {
	public PotentialAgent(ContinuousSpace<Object> space, Grid<Object> grid, double energy) {
		super(space, grid, energy);
	}
	
	@ScheduledMethod(start = 1, interval = 0.5)
	public void step() {
		if(isInviteReceived() || isWomReceived()){
			incrementEnergy();
		} else {
			tiring();
		}
		
		if(getCurrentEnergy() <= 0.0) {
			dead();
			return;
		}
		
		if(getCurrentEnergy() >= POTENTIAL_CONSUMENT_BORDER) {
			convertToConsumer();
		}
	}
}

