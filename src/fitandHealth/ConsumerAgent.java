package fitandHealth;
import java.util.List;

import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.query.space.grid.GridCell;
import repast.simphony.query.space.grid.GridCellNgh;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.SpatialMath;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.util.SimUtilities;
public class ConsumerAgent extends Agent{

	public ConsumerAgent(ContinuousSpace<Object> space, Grid<Object> grid, double energy) {
		super(space, grid, energy);
	}

	@ScheduledMethod(start = 1, interval = 0.5)
	public void step() {
		if(isInviteReceived()){
			incrementEnergy();
		} else {
			tiring();
		}
		
		if(getCurrentEnergy() <= POTENTIAL_CONSUMENT_BORDER) {
			convertToPotential();
			return;
		}
	}
	
	@ScheduledMethod(start = 1, interval = 0.5)
	public void womAdv() {
		Grid<Object> grid = getGrid();
		GridPoint pt = grid.getLocation(this);
		
		GridCellNgh<PotentialAgent> nghCreator = new GridCellNgh<PotentialAgent>(grid, pt, PotentialAgent.class, 1, 1);
		
		List<GridCell<PotentialAgent>> gridCells = nghCreator.getNeighborhood(true);
		
		for (GridCell<PotentialAgent> cell : gridCells) {
			for(PotentialAgent agent : cell.items()) {
				sendWom(agent);
			}
		}
	}
	
	@ScheduledMethod(start = 1, interval = 1)
	public void run() {
		Grid<Object> grid = getGrid();
		
		GridPoint pt = grid.getLocation(this);
		
		GridCellNgh<PotentialAgent> nghCreator = new GridCellNgh<PotentialAgent>(grid, pt, PotentialAgent.class, 1, 1);
		
		List<GridCell<PotentialAgent>> gridCells = nghCreator.getNeighborhood(true);
		
		SimUtilities.shuffle(gridCells, RandomHelper.getUniform());
		
		GridPoint pointWithMostPotentialAgents = null;
		int maxCount = -1;
		for (GridCell<PotentialAgent> cell : gridCells) {
			if (cell.size() > maxCount) {
				pointWithMostPotentialAgents = cell.getPoint();
				maxCount = cell.size();
			}
		}
		moveTowards(pointWithMostPotentialAgents);
	}
	
	public void moveTowards(GridPoint pt) {
		Grid<Object> grid = getGrid();
		ContinuousSpace<Object> space = getSpace();

		if (!pt.equals(grid.getLocation(this))) {
			NdPoint myPoint = space.getLocation(this);
			NdPoint otherPoint = new NdPoint(pt.getX(), pt.getY());
			
			double angle = SpatialMath.calcAngleFor2DMovement(space, myPoint, otherPoint);
			space.moveByVector(this, 1, angle, 0);
			myPoint = space.getLocation(this);
			grid.moveTo(this, (int) myPoint.getX(), (int) myPoint.getY());
		}
	}
	
	
	public void sendWom(PotentialAgent agent){

		agent.receiveWom(this);
	}
}
