package fitandHealth;

import repast.simphony.context.Context;
import repast.simphony.context.space.continuous.ContinuousSpaceFactory;
import repast.simphony.context.space.continuous.ContinuousSpaceFactoryFinder;
import repast.simphony.context.space.grid.GridFactory;
import repast.simphony.context.space.grid.GridFactoryFinder;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.parameter.Parameters;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;
import repast.simphony.space.continuous.RandomCartesianAdder;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridBuilderParameters;
import repast.simphony.space.grid.RandomGridAdder;
import repast.simphony.space.grid.WrapAroundBorders;
public class FitBuilder implements ContextBuilder<Object>  {
	

	
	@Override
	public Context build(Context<Object> context) {

		context.setId("FitandHealth");
		ContinuousSpaceFactory spaceFactory = ContinuousSpaceFactoryFinder.createContinuousSpaceFactory(null);
		ContinuousSpace<Object> space = spaceFactory.createContinuousSpace("space", context,
				new RandomCartesianAdder<Object>(), new repast.simphony.space.continuous.WrapAroundBorders(), 50, 50);
		GridFactory gridFactory = GridFactoryFinder.createGridFactory(null);
		Grid<Object> grid = gridFactory.createGrid("Grid", context,
				new GridBuilderParameters<Object>(new WrapAroundBorders(), new RandomGridAdder<Object>(), true, 50,
						50));
		Parameters params=RunEnvironment.getInstance().getParameters();
		int clubCount = params.getInteger("club_count");
		for (int i = 0; i < clubCount; i++) {
			context.add(new Club(space, grid));
		}
		
		int potentialAgentCount = params.getInteger("potential_count");
		for (int i = 0; i < potentialAgentCount; i++) {
			double energy = RandomHelper.nextDoubleFromTo(0.0, 25.0);
			context.add(new PotentialAgent(space, grid, energy,i));
		}
		int consumerAgentCount = params.getInteger("consumer_count");
		for (int i = potentialAgentCount+1; i < potentialAgentCount+consumerAgentCount; i++) {
			double energy = RandomHelper.nextDoubleFromTo(25.0, 50.0);
			context.add(new ConsumerAgent(space, grid, energy,i));
		}
		
		for (Object obj : context) {
			NdPoint pt = space.getLocation(obj);
			grid.moveTo(obj, (int) pt.getX(), (int) pt.getY());
		}
		return context;
	}

}
