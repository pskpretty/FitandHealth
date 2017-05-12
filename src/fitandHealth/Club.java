package fitandHealth;

import java.util.Iterator;
import java.util.List;

import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.query.space.grid.GridCell;
import repast.simphony.query.space.grid.GridCellNgh;
import repast.simphony.query.space.grid.MooreQuery;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;

public class Club {
	private ContinuousSpace<Object> space;
	private Grid<Object> grid;
	private Boolean broadcast;
	
	public Club(ContinuousSpace<Object> space, Grid<Object> grid) {
		super();
		this.space = space;
		this.grid = grid;
	}
	/*At every iteration health club is expected to send advertisement to nearby potential agents
	  */
	@ScheduledMethod(start = 1, interval = 1)
	public void step(){
		GridPoint pt = grid.getLocation(this);
		GridCellNgh<PotentialAgents> nghCreator = new GridCellNgh<PotentialAgents>(grid, pt, PotentialAgents.class, 1, 1);
		List<GridCell<PotentialAgents>> gridCells = nghCreator.getNeighborhood(true);
		
		/*a code snippet to update the invite to true of potential agents that receive broadcast from health club
		//MooreQuery<PotentialAgents> query=new MooreQuery(grid,this);
		//Iterator<PotentialAgents> iter=query.query().iterator();
		 for(Object o:query.query()){
			System.out.println(o);
			if(o instanceof PotentialAgents){
				System.out.println("Potential agent"+o);
			}
		}*/
		
		broadcast = true;
	}
	
	}

