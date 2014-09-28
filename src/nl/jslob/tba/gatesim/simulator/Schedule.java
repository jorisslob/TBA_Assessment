package nl.jslob.tba.gatesim.simulator;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Map.Entry;

import nl.jslob.tba.gatesim.components.Component;

import com.google.common.collect.TreeMultimap;

/**
 * A Schedule is an object that keeps track of all the events that are about to
 * happen. It is a wrapper around a collection that is ordered by Time and holds
 * the next step that needs to be done.
 *
 * @author jslob
 *
 */
public class Schedule {

    /**
     * now is the time of the event currently being processed.
     */
    private LocalDateTime now;

    /**
     * schedule holds a timetable with events that should happen. Each event is
     * determined by the location of the truck and the place within the
     * components list.
     */
    private TreeMultimap<LocalDateTime, Truck> schedule;

    /**
     * simulator holds a link to the simulator that this schedule belongs to.
     * This link is necessary to be able to get information on the harbor
     * components.
     */
    private Simulator simulator;

    /**
     * constructor is needed to properly initiate the schedule TreeMultimap.
     *
     * @param simulator
     *            The simulator object that this schedule is a part of
     */
    public Schedule(final Simulator simulator) {
        schedule = TreeMultimap.create();
        this.simulator = simulator;
    }

    /**
     * Provides an easy way to mass populate the schedule with new entry events.
     *
     * @param timelist
     *            A list of trucks with times of arrival
     * @param next
     *            The component these trucks have to be sent to
     */
    public final void addAll(final Map<Truck, LocalDateTime> timelist,
            final Component next) {
        for (Entry<Truck, LocalDateTime> e : timelist.entrySet()) {
            Truck t = e.getKey();
            LocalDateTime arrival = e.getValue();
            t.setLocation(next);
            schedule.put(arrival, t);
        }
    }

    /**
     * Let the scheduler do the next step of the simulation. This involves
     * getting the first truck that has an event registered, updating the time
     * of the simulation, removing this truck from the schedule, getting the
     * next component of the truck, releasing the truck from the previous
     * location and let the new component accept the truck.
     */
    public final void doNextStep() {
        // Find out which truck is next...
        LocalDateTime first = schedule.asMap().firstKey();
        Truck t = schedule.get(first).first();
        now = first;
        schedule.remove(first, t);

        // The component that has the truck should be notified that it is done.
        // And the next component should be notified that the truck is on its
        // way
        Component n = simulator.getNextComponent(t.getLocation());
        t.getLocation().releaseTruck(t);
        if (n == null) {
            System.out.println("Sending truck nowhere!");
            throw new IllegalStateException(
                    "The components do not end with a World component");
        }
        n.acceptTruck(t);
    }

    /**
     * Get the current time, the time of the event of the truck being simulated
     * right now.
     *
     * @return current time of the simulation
     */
    public final LocalDateTime getNow() {
        return now;
    }

    /**
     * Returns true if there are any events that still need processing.
     *
     * @return true if there are any events that still need processing
     */
    public final boolean isActive() {
        return schedule.size() > 0;
    }

    /**
     * Plan an event for a specific truck after a specified number of seconds.
     *
     * @param t
     *            truck that wants to be activated later
     * @param seconds
     *            time after now that the truck should be activated
     */
    public final void nextTruckSecondFromNow(final Truck t,
            final long seconds) {
        schedule.put(getNow().plusSeconds(seconds), t);
    }
}
