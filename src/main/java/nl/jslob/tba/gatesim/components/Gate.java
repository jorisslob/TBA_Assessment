package nl.jslob.tba.gatesim.components;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import nl.jslob.tba.gatesim.simulator.Schedule;
import nl.jslob.tba.gatesim.simulator.Truck;
import nl.jslob.tba.gatesim.util.GammaDistributionRate;

/**
 * Gate is a component in a harbor that has one or more lanes. The lanes are
 * queues and trucks pick the shortest queue. The processing time in the gate is
 * determined by a Gamma Distribtion.
 *
 * @author jslob
 *
 */
public class Gate implements Component {

    /**
     * MAXQUEUESIZE determines how long a queue can be before violating the
     * restrictions of this simulation. If other simulations are run with other
     * requirements, it is better to externalize this number to a configuration
     * file.
     *
     * The value is 31 here, because the head of the queue is actually the gate
     * itself.
     */
    private static final int MAXQUEUESIZE = 31;

    /**
     * GammaDistributionRate provides the random waiting times at the gate.
     */
    private GammaDistributionRate gd;

    /**
     * queues is a list of queues that hold the trucks until they can be
     * processed. The head of the queue is the gate.
     */
    private List<Queue<Truck>> queues;

    /**
     * The schedule is a link to the general schedule to request a callback and
     * to get the current time.
     */
    private Schedule schedule;

    /**
     * Constructs a gate with a specified number of queue lanes and a link to
     * the scheduler, and the shape parameters of the Gamma Distribution.
     *
     * @param lanes
     *            Number of lanes of this gate
     * @param schedule
     *            The schedule object of this simulation
     * @param alpha
     *            The shape parameter of the gamma distribution
     * @param beta
     *            The rate parameter of the gamma distribution
     */
    public Gate(final int lanes, final Schedule schedule, final int alpha,
            final int beta) {
        queues = new ArrayList<Queue<Truck>>();
        for (int l = 0; l < lanes; l++) {
            queues.add(new LinkedList<Truck>());
        }
        this.schedule = schedule;
        gd = new GammaDistributionRate(alpha, beta);
    }

    public final void acceptTruck(final Truck t) {
        t.setLocation(this);

        // Find the smallest queue
        Queue<Truck> min = queues.get(0);
        int minVal = queues.get(0).size();
        for (Queue<Truck> q : queues) {
            if (q.size() < minVal) {
                min = q;
                minVal = q.size();
            }
        }
        if (minVal > MAXQUEUESIZE) {
            t.inLongQueue();
        }
        min.offer(t);
        // If the queue was empty, we can process this truck right away!
        if (minVal == 0) {
            schedule.nextTruckSecondFromNow(t, gd.getValueInSeconds());
        } else {
            // Otherwise we have to wait till other trucks are released...
            t.putInQueue(schedule.getNow());
        }
    }

    public final void releaseTruck(final Truck t) {
        for (Queue<Truck> q : queues) {
            if (q.peek() == t) {
                q.poll(); // The requested truck is released
                // The next in the queue might become active
                if (!q.isEmpty()) {
                    Truck next = q.peek();
                    next.endQueueTime(schedule.getNow());
                    schedule.nextTruckSecondFromNow(q.peek(),
                            gd.getValueInSeconds());
                }
                return;
            }
        }
        throw new IllegalStateException("Truck to be released not found!");
    }
}
