package fitandHealth;

import java.util.List;

import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.query.space.grid.GridCell;
import repast.simphony.query.space.grid.GridCellNgh;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;

public class Club {
	private ContinuousSpace<Object> space;
	private Grid<Object> grid;
	
	public Club(ContinuousSpace<Object> space, Grid<Object> grid) {
		this.space = space;
		this.grid = grid;
	}
	/*At every iteration health club is expected to send advertisement to nearby potential agents
	  */
	@ScheduledMethod(start = 1, interval = 1)
	public void step(){
		GridPoint pt = grid.getLocation(this);
		GridCellNgh<FitAgent> nghCreator = new GridCellNgh<FitAgent>(grid, pt, FitAgent.class, 1, 1);
		List<GridCell<FitAgent>> gridCells = nghCreator.getNeighborhood(true);
		
		for (GridCell<FitAgent> cell : gridCells){
			for(FitAgent agent : cell.items()) {
				sendInvite(agent);
			}
		}
	}
	
	public void sendInvite(FitAgent agent) {
		agent.receiveInvite(this);
	}

}