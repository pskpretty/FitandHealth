package fitandHealth;

import repast.simphony.context.Context;
import repast.simphony.context.space.continuous.ContinuousSpaceFactory;
import repast.simphony.context.space.continuous.ContinuousSpaceFactoryFinder;
import repast.simphony.context.space.grid.GridFactory;
import repast.simphony.context.space.grid.GridFactoryFinder;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;
import repast.simphony.space.continuous.RandomCartesianAdder;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridBuilderParameters;
import repast.simphony.space.grid.RandomGridAdder;
import repast.simphony.space.grid.WrapAroundBorders;
public class FitBuilder implements ContextBuilder<Object>  {
	

	private int xdim = 50;
	private int ydim = 50;
	
	@Override
	public Context build(Context<Object> context) {

		context.setId("FitandHealth");
		ContinuousSpaceFactory spaceFactory = ContinuousSpaceFactoryFinder.createContinuousSpaceFactory(null);
		ContinuousSpace<Object> space = spaceFactory.createContinuousSpace("space", context,
				new RandomCartesianAdder<Object>(), new repast.simphony.space.continuous.WrapAroundBorders(), 50, 50);
		GridFactory gridFactory = GridFactoryFinder.createGridFactory(null);
		Grid<Object> grid = gridFactory.createGrid("Grid", context,
				new GridBuilderParameters<Object>(new WrapAroundBorders(), new RandomGridAdder<Object>(), true, xdim,
						ydim));
		int clubCount = 5;
		for (int i = 0; i < clubCount; i++) {
			context.add(new Club(space, grid));
		}
		
		int agentCount = 15;
		for (int i = 0; i < agentCount; i++) {
			double energy = RandomHelper.nextDoubleFromTo(0.0, 25.0);
			context.add(new PotentialAgent(space, grid, energy));
		}
		
		for (Object obj : context) {
			NdPoint pt = space.getLocation(obj);
			grid.moveTo(obj, (int) pt.getX(), (int) pt.getY());
		}
		return context;
	}

}
