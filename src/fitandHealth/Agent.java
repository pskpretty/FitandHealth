package fitandHealth;

import repast.simphony.context.Context;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.parameter.Parameters;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.util.ContextUtils;

public class Agent  {
	private ContinuousSpace<Object> space;
	private Grid<Object> grid;
	private double currentEnergy;
	private boolean inviteReceived;
	private boolean womReceived;
	Parameters params=RunEnvironment.getInstance().getParameters();
	 private double MAX_ENERGY = params.getDouble("max_energy");
	 private double INVITE_ENERGY  = params.getDouble("invite_energy");
	 private double TIRING_FACTOR = params.getDouble("tiring_factor");
	 public double POTENTIAL_CONSUMENT_BORDER =params.getDouble("POTENTIAL_CONSUMENT_BORDER");
	
	public Agent(ContinuousSpace<Object> space, Grid<Object> grid, double energy) {
		this.space = space;
		this.grid = grid;
		this.currentEnergy = energy;
	}
	    
	
	public Grid<Object> getGrid() {
		return grid;
	}
	
	public ContinuousSpace<Object> getSpace() {
		return space;
	}
	public void receiveInvite(Club club) {
		inviteReceived = true;
	}
	
	public void receiveWom(ConsumerAgent agent) {
		womReceived = true;
	}
	
	public boolean isInviteReceived() {
		return inviteReceived;
	}
	
	public boolean isWomReceived() {
		return womReceived;
	}
	
	public void incrementEnergy() {
		currentEnergy += INVITE_ENERGY;
		if(currentEnergy >= MAX_ENERGY) {
			currentEnergy = MAX_ENERGY;
		}
		inviteReceived = false;
		womReceived = false;
	}
	
	public void tiring() {
		currentEnergy -= TIRING_FACTOR;
	}
	
	public double getCurrentEnergy() {
		return currentEnergy;
	}
	
	public void dead() {
		Context<Agent> context = ContextUtils.getContext(this);
		context.remove(this);
	}
	
	public void convertToConsumer() {
		NdPoint spacePt = space.getLocation(this);
		GridPoint pt = grid.getLocation(this);
		Context<Agent> context = ContextUtils.getContext(this);
		context.remove(this);
		
		ConsumerAgent agent = new ConsumerAgent(space, grid, currentEnergy);
		context.add(agent);
		space.moveTo(agent, spacePt.getX(), spacePt.getY());
		grid.moveTo(agent, pt.getX(), pt.getY());
	}

	public void convertToPotential() {
		NdPoint spacePt = space.getLocation(this);
		GridPoint pt = grid.getLocation(this);
		Context<Agent> context = ContextUtils.getContext(this);
		context.remove(this);
		
		PotentialAgent agent = new PotentialAgent(space, grid, currentEnergy);
		context.add(agent);
		space.moveTo(agent, spacePt.getX(), spacePt.getY());
		grid.moveTo(agent, pt.getX(), pt.getY());
	}
}
