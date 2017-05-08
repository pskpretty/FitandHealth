package fitandHealth;

import repast.simphony.context.DefaultContext;
import repast.simphony.context.space.grid.GridFactoryFinder;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.parameter.Parameters;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridBuilderParameters;
import repast.simphony.space.grid.RandomGridAdder;
import repast.simphony.space.grid.WrapAroundBorders;
import repast.simphony.valueLayer.GridValueLayer;

public class FitBuilderSpace{ /*extends DefaultContext<Object> {
	Parameters p = RunEnvironment.getInstance().getParameters();
	private int xdim = (Integer) p.getValue("worldWidth");
	private int ydim = (Integer) p.getValue("worldHeight");

	public FitBuilderSpace(String fitSpace) {
		Grid<Object> grid = GridFactoryFinder.createGridFactory(null).createGrid("Grid", this,
				new GridBuilderParameters<Object>(new WrapAroundBorders(), new RandomGridAdder<Object>(), true, xdim,
						ydim));
		PGMReader reader = new PGMReader(fitSpace);
		int matrix[][] = reader.getMatrix();
		GridValueLayer currentEnergy = new GridValueLayer("CurrentEnergy", true, new WrapAroundBorders(), xdim, ydim);
		GridValueLayer maxEnergy = new GridValueLayer("MaxEnergy", true, new WrapAroundBorders(), xdim, ydim);
		this.addValueLayer(currentEnergy);
		this.addValueLayer(maxEnergy);
		for (int x = 0; x < xdim; x++) {
			for (int y = 0; y < ydim; y++) {
				currentEnergy.set(matrix[x][y], x, y);
				maxEnergy.set(matrix[x][y], x, y);
			}
		}
	}*/

}
