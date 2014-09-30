package nl.jslob.tba.gatesim.simulator;

/**
 * Statistics is an object that holds the results of the simulation and can
 * report back in the end. In this simulation, the total queue time of trucks
 * and the queue violations should show up.
 *
 * @author jslob
 */
public class Statistics {

    /**
     * Number of trucks that went through the entire simulation.
     */
    private int truckNumber;

    /**
     * Total time that all the trucks spent in queues.
     */
    private long queueTime;

    /**
     * Has there been any queue violation in the entire simulation?
     */
    private boolean queueViolation;

    /**
     * Constructor for the Statistics class, starts all statistics at zero.
     */
    public Statistics() {
        queueTime = 0;
        queueViolation = false;
        truckNumber = 0;
    }

    /**
     * Add a truck to the statistics. The truck holds the information that has
     * to be added to the overall statistics.
     *
     * @param t
     *            Truck that went through all components.
     */
    public final void addTruck(final Truck t) {
        truckNumber++;
        if (t.getQueueSeconds() < 0) {
            throw new IllegalStateException(
                    "Cannot spend negative time in queue");
        }
        queueTime += t.getQueueSeconds();
        if (t.getLongQueue()) {
            queueViolation = true;
        }
    }

    /**
     * Number of trucks that went through all the components.
     *
     * @return Number of trucks that went through all the components
     */
    public final int getNumOfTrucks() {
        return truckNumber;
    }

    /**
     * Checks if there has been any violation of queue length in the simulation.
     *
     * @return true if there has been a violation of queue length restriction
     */
    public final boolean getQueueViolation() {
        return queueViolation;
    }

    /**
     * Get the total time that all the trucks spent waiting in a queue.
     *
     * @return total time that all trucks spent waiting in a queue
     */
    public final long getTotalQueueTime() {
        return queueTime;
    }
}
