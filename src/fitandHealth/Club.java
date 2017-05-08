package fitandHealth;

import java.util.List;

import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.query.space.grid.GridCell;
import repast.simphony.query.space.grid.GridCellNgh;
import repast.simphony.space.SpatialMath;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;

public class Club {
	private ContinuousSpace<Object> space;
	private Grid<Object> grid;
	private Boolean moved;
	
	public Club(ContinuousSpace<Object> space, Grid<Object> grid) {
		super();
		this.space = space;
		this.grid = grid;
	}
	/*Step 1. Club sent out invitation to the neighborhood at the beginning of iteration. (nearby 8 cells)
	Step 2. The neighborhood agents who receive the invite will increase their energy level by a count of 1.
	If (count>= 3 then (potential agent -> consumer agent)
	If count <3 then increment the energy level by 1
	If consumer agent doesn’t receive the advertisement, then their energy level decreases by 0.25 after each iteration because the tiring routine of fitness exercise.
	If consumer agent energy level flows below 3, then it becomes converted to potential agent.
	Step 3 After 30 second consumer agent sends word of mouth advertisement to nearby potential agents. this increases the energy level by 1.
	Step 4. During every iteration if either of the invite is not received then agent energy level decreases by 0.25 and if energy falls below 0 then agent dies out.*/
	@ScheduledMethod(start = 1, interval = 1)
	public void step(){
		GridPoint pt = grid.getLocation(this);
		GridCellNgh<PotentialAgents> nghCreator = new GridCellNgh<PotentialAgents>(grid, pt, PotentialAgents.class, 1, 1);
		List<GridCell<PotentialAgents>> gridCells = nghCreator.getNeighborhood(true);
		
		for (GridCell<PotentialAgents> cell : gridCells){
		
		}
	}
	public void moveTowards(GridPoint pt) {
		if (!pt.equals(grid.getLocation(this))) {
			NdPoint myPoint = space.getLocation(this);
			NdPoint otherPoint = new NdPoint(pt.getX(), pt.getY());
			double angle = SpatialMath.calcAngleFor2DMovement(space, myPoint, otherPoint);
			space.moveByVector(this, 1, angle, 0);
			myPoint = space.getLocation(this);
			grid.moveTo(this, (int) myPoint.getX(), (int) myPoint.getY());
			moved = true;
		}
	}
}
