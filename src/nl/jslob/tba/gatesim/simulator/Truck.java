package nl.jslob.tba.gatesim.simulator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import nl.jslob.tba.gatesim.components.Component;

/**
 * Truck is the object that simulates a physical truck. In this simulation they
 * keep track of their own statistics, untill they can offload them to the
 * Statistics class in the World Component.
 *
 * @author jslob
 *
 */
public class Truck implements Comparable<Truck> {

    /**
     * id is the unique identifier of this truck.
     */
    private String id;

    /**
     * kind is the type of truck this is, a truck that is here to receive or
     * deliver goods. "DLVR" means the truck enters empty and leaves full.
     * "RECV" means the truck enters full and leaves empty.
     */
    private String kind;

    /**
     * Current location of the truck.
     */
    private Component location;

    /**
     * This field tracks if the truck has ever been in a queue that was too
     * long.
     */
    private boolean longQueue;

    /**
     * queueSeconds is the number of seconds this truck has been in a queue of a
     * component.
     */
    private long queueSeconds;

    /**
     * startQueue is the time the truck entered a queue of a component. This is
     * needed to determine the time the truck was in a queue when it leaves the
     * queue.
     */
    private LocalDateTime startQueue;

    /**
     * Contructor of a Truck. It needs to have an id and specify what type of
     * truck we are dealing with (will it have a load when entering the
     * simulation?). The time of arrival is handled by the Schedule class.
     *
     * @param id
     *            The unique id of a truck.
     * @param kind
     *            String that determines if a truck is loaded on arrival.
     */
    public Truck(final String id, final String kind) {
        this.id = id;
        this.kind = kind;
        location = null;
        queueSeconds = 0;
        longQueue = false;
    }

    @Override
    public final int compareTo(final Truck o) {
        return this.id.compareTo(o.id);
    }

    /**
     * endQueueTime should be called when the truck leaves a queue. This allows
     * the truck to calculate the total amount of time it spent in a queue.
     *
     * @param time
     *            The time the truck will leave the queue.
     */
    public final void endQueueTime(final LocalDateTime time) {
        if (startQueue == null) {
            throw new IllegalStateException("Truck was not in a queue");
        }
        long extraSeconds = startQueue.until(time, ChronoUnit.SECONDS);

        if (extraSeconds < 0) {
            System.out.println(startQueue + " until " + time + " = "
                    + extraSeconds);
            throw new IllegalStateException(
                    "Cannot spend negative time in queue");
        }
        queueSeconds += extraSeconds;
        startQueue = null;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Truck other = (Truck) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    /**
     * Get the unique id of the truck.
     *
     * @return unique truck id.
     */
    public final String getId() {
        return id;
    }

    /**
     * Get the truck kind (whether the system sees it as DLVR or RECV.
     *
     * @return truck kind as defined in xlsx.
     */
    public final String getKind() {
        return kind;
    }

    /**
     * Get the current location of the truck.
     *
     * @return Current component the truck is in.
     */
    public final Component getLocation() {
        return location;
    }

    /**
     * Get whether this truck was in a queue that was too long.
     *
     * @return true if the truck was in a queue that was too long.
     */
    public final boolean getLongQueue() {
        return longQueue;
    }

    /**
     * Get the number of seconds that this truck was in a queue.
     *
     * @return the number of seconds this truck spent in a queue.
     */
    public final long getQueueSeconds() {
        return queueSeconds;
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /**
     * Reports to this truck that it is in a queue that is too long.
     */
    public final void inLongQueue() {
        longQueue = true;
    }

    /**
     * The truck was put into a queue. The truck can now keep track of time, to
     * know how long it spent in a queue.
     *
     * @param time The time at which this truck was put into the queue.
     */
    public final void putInQueue(final LocalDateTime time) {
        if (startQueue != null) {
            throw new IllegalStateException("Truck is already in a queue");
        }
        startQueue = time;
    }

    /**
     * Changes the current location of the Truck.
     *
     * @param location New location that the truck moves to.
     */
    public final void setLocation(final Component location) {
        this.location = location;
    }

    @Override
    public final String toString() {
        return id + " " + location;
    }
}
