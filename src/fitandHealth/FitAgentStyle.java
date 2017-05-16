package fitandHealth;

import java.awt.Color;

import repast.simphony.visualizationOGL2D.DefaultStyleOGL2D;

public class FitAgentStyle extends DefaultStyleOGL2D {

	 @Override
	    public Color getColor(Object object) {
	        if ( ((FitAgent)object).getCurrentEnergy()<=3)
	        	  return Color.BLUE;
	        else if (((FitAgent) object).getCurrentEnergy()>3)
	            return Color.RED;
	        else
	        	return Color.BLACK;
	        }
}
