package nl.jslob.tba.gatesim.components;

import nl.jslob.tba.gatesim.simulator.Schedule;
import nl.jslob.tba.gatesim.simulator.Truck;

/**
 * Transit is a component that simulates a road. The waiting time is fixed.
 *
 * @author jslob
 *
 */
public class Transit implements Component {

    /**
     * WAITTIME for the Transit is fixed. All trucks spend 3 minutes in transit
     * from one place to the other. If this needs to change, consider making it
     * a paramter to the constructor.
     */
    private static final int WAITTIME = 3 * 60;

    /**
     * Link to the simulation scheduler. This is needed to request a callback.
     */
    private Schedule schedule;

    /**
     * Constructor of the Transit Component. Initializes the link to the
     * schedule.
     *
     * @param schedule
     *            Link to the simulator schedule
     */
    public Transit(final Schedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public final void acceptTruck(final Truck t) {
        t.setLocation(this);
        schedule.nextTruckSecondFromNow(t, WAITTIME);
    }

    @Override
    public void releaseTruck(final Truck t) {

    }

}
