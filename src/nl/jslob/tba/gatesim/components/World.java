package nl.jslob.tba.gatesim.components;

import nl.jslob.tba.gatesim.simulator.Statistics;
import nl.jslob.tba.gatesim.simulator.Truck;

/**
 * The World Component is used twice in a simulation. Both as the place where
 * trucks come from, the first component in the simulation and the place trucks
 * will end up, the last components of the simulation. When a truck reaches this
 * last state, it will report back its personal statistics to the Statistics
 * Class.
 *
 * @author jslob
 *
 */
public class World implements Component {

    /**
     * Link to the simulation Statistics object.
     */
    private Statistics stats;

    /**
     * Constructor for the World component. It needs a link to the simulation
     * Statistics object.
     *
     * @param stats
     *            link to the simulation Statistic object.
     */
    public World(final Statistics stats) {
        this.stats = stats;
    }

    @Override
    public final void acceptTruck(final Truck t) {
        stats.addTruck(t);
    }

    @Override
    public void releaseTruck(final Truck t) {

    }

}
