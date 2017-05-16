package fitandHealth;

import java.util.List;

import repast.simphony.context.Context;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.query.space.grid.GridCell;
import repast.simphony.query.space.grid.GridCellNgh;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.graph.Network;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.util.ContextUtils;

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
				sendInvite(agent,pt);
			}
		}
	}
	public void infect(GridPoint source,GridPoint destination){
		System.out.println("inside club draw edge");
		if(destination!=null&&source !=null)
		{
		Context<FitAgent> context = ContextUtils.getContext(this);
		Network < Object > net = ( Network < Object >) context .
				getProjection (" infection network ");
			net . addEdge ( destination , source );}
	}
	
	public void sendInvite(FitAgent agent,GridPoint source) {
		System.out.println("inside sendInvite club");
		GridPoint destination=agent.getGrid().getLocation(this);
		System.out.println(source+"source"+destination+"destination");
		infect(source,destination);
		agent.receiveInvite(this);
	}

}