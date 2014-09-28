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

    /**
     * Function gets called when the Schedule class determines that a truck
     * should go to this component. The component MUST change the location in
     * the truck and then it can deal with the truck as it wants.
     *
     * @param t
     *            is the truck that the component receives
     */
    void acceptTruck(Truck t);

    /**
     * Function gets called when the Schedule class determines that a truck
     * should leave this component.
     *
     * @param t
     *            is the truck that will leave the component
     */
    void releaseTruck(Truck t);
}
