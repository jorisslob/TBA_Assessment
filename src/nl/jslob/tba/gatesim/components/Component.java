package nl.jslob.tba.gatesim.components;

import nl.jslob.tba.gatesim.simulator.Truck;

/**
 * A component is something that modifies the schedule of a truck. Any time a
 * truck has a new ETA it has been caused by a component.
 * 
 * @author jslob
 *
 */
public interface Component {

	public double processTimeTruck(Truck t);

	public boolean isQueueViolated();

	public double queueTimeTruck(Truck t);

	public void acceptTruck(Truck t);

	public void releaseTruck(Truck t);
}
