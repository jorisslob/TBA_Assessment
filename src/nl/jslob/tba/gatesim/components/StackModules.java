package nl.jslob.tba.gatesim.components;

import java.util.LinkedList;
import java.util.Queue;

import nl.jslob.tba.gatesim.simulator.Schedule;
import nl.jslob.tba.gatesim.simulator.Truck;
import nl.jslob.tba.gatesim.util.GammaDistributionRate;

/**
 * StackModules are the component where trucks are loaded and unloaded. They are
 * special in this simulation because multiple StackModules share a single
 * queue. The StackModules have a Gamma Distribution that determines the time
 * that a truck is busy with loading and unloading.
 *
 * @author jslob
 *
 */
public class StackModules implements Component {

    /**
     * Maximum Queue Size according to the simulation. If this changes, consider
     * placing this in a configuration file.
     */
    private static final int MAXQUEUESIZE = 30;

    /**
     * The Gamma Distribution determines the waiting time for the truck to load
     * or unload.
     */
    private GammaDistributionRate gd;

    /**
     * Number of stack modules that are in use right now.
     */
    private int inUse;

    /**
     * The amount of stack modules are available in this component.
     */
    private int modules;

    /**
     * The queue for all the stack modules.
     */
    private Queue<Truck> queue;

    /**
     * Link to the schedule to request the time or request a callback.
     */
    private Schedule schedule;

    /**
     * Constructor that determines the speed and number of Stack Modules.
     *
     * @param modules
     *            number of stack modules in this component.
     * @param schedule
     *            link to the schedule of the simulation
     * @param alpha
     *            shape parameter of the gamma distribution
     * @param beta
     *            rate parameter of the gamma distribution
     */
    public StackModules(final int modules, final Schedule schedule,
            final int alpha, final int beta) {
        this.modules = modules;
        this.schedule = schedule;
        queue = new LinkedList<Truck>();
        gd = new GammaDistributionRate(alpha, beta);
    }

    @Override
    public final void acceptTruck(final Truck t) {
        t.setLocation(this);
        if (inUse < modules) {
            if (!queue.isEmpty()) {
                throw new IllegalStateException(
                        "module is empty, but queue not");
            }
            inUse++;
            schedule.nextTruckSecondFromNow(t, gd.getValueInSeconds());
        } else {
            if (queue.size() > MAXQUEUESIZE) {
                t.inLongQueue();
            }
            queue.add(t);
            t.putInQueue(schedule.getNow());
        }
    }

    @Override
    public final void releaseTruck(final Truck t) {
        if (inUse < 1) {
            throw new IllegalStateException(
                    "Cannot release another truck, there are none in use");
        }
        inUse--;
        if (!queue.isEmpty()) {
            Truck next = queue.poll();
            next.endQueueTime(schedule.getNow());
            schedule.nextTruckSecondFromNow(next, gd.getValueInSeconds());
            inUse++;
        }
    }
}
