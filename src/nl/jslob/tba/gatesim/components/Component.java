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

	public abstract void acceptTruck(Truck t);

	public abstract void releaseTruck(Truck t);
}
