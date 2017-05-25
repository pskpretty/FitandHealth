
package chart;

import java.util.Map;
import java.util.HashMap;

import repast.simphony.statecharts.*;
import repast.simphony.statecharts.generator.GeneratedFor;

import .*;

@GeneratedFor("_an5tIDrtEeeqndJRESQ7yQ")
public class Statechart extends DefaultStateChart<Agent> {

    public static Statechart createStateChart(Agent agent, double begin) {
        Statechart result = createStateChart(agent);
        StateChartScheduler.INSTANCE.scheduleBeginTime(begin,result);
        return result;
    }

    public static Statechart createStateChart(Agent agent) {
        StatechartGenerator generator = new StatechartGenerator();
        return generator.build(agent);
    }

    private Statechart(Agent agent) {
        super(agent);
    }
    
    private static class MyStateChartBuilder extends StateChartBuilder<Agent> {

        public MyStateChartBuilder(Agent agent, AbstractState<Agent> entryState, String entryStateUuid) {
            super(agent, entryState, entryStateUuid);
            setPriority(0.0);
        }

        @Override
        public Statechart build() {
            Statechart result = new Statechart(getAgent());
            setStateChartProperties(result);
            return result;
        }
    }


private static class StatechartGenerator {

    private Map<String, AbstractState<Agent>> stateMap = new HashMap<String, AbstractState<Agent>>();

   
    public Statechart build(Agent agent) {
        throw new UnsupportedOperationException("Statechart has not been defined.");
        
    }
   
    private void createTransitions(MyStateChartBuilder mscb) {
         
    }
    
    
   
   
}
}
