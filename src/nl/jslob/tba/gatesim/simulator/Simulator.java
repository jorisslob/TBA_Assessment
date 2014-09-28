package nl.jslob.tba.gatesim.simulator;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;

import nl.jslob.tba.gatesim.components.Component;
import nl.jslob.tba.gatesim.components.Gate;
import nl.jslob.tba.gatesim.components.StackModules;
import nl.jslob.tba.gatesim.components.Transit;
import nl.jslob.tba.gatesim.components.World;
import nl.jslob.tba.gatesim.util.ExcelTruckReader;

/**
 * Simulator Object that deals with time and registers components Components
 * include TruckActivity, Gates, Transit, Stacks and Queues The Simulator Object
 * is responsible to check if there is still activity in its components and
 * ending the simulation with a statistics overview.
 *
 * In this particular Simulator, only two parameters are important: (entry lanes
 * and exit lanes). We will measure the performance by adding up the total queue
 * waiting time of the trucks and the amount of restriction violations
 *
 * @author jslob
 */
public class Simulator {

    /**
     * Components is a list of locations in the harbor that a truck has to visit
     * in order. In the implementation of the Simulator the components have to
     * start and finish with a World class. Trucks come from the outside world
     * and go back to the outside world.
     *
     * components is an instance of a LinkedList, because we need access to its
     * elements by index. An array would also work (even faster), but I choose
     * this class for readability of the code.
     */
    private LinkedList<Component> components;

    /**
     * The schedule has all the events that are waiting to happen.
     */
    private Schedule schedule;

    /**
     * Statistics object holds the important values for this simulation.
     */
    private Statistics stats;

    /**
     * Creates a Simulator with a specified number of entry and exit lanes.
     *
     * @param entry
     *            number of entry lanes
     * @param exit
     *            number of exit lanes
     */
    public Simulator(final int entry, final int exit) {
        // Initialize internal structures of the Simulator
        components = new LinkedList<Component>();
        schedule = new Schedule();
        stats = new Statistics();

        // Put the components in place. If this simulation is used more often
        // for different harbors, consider putting the numbers in an external
        // file.
        Component world = new World(stats);
        Component entryGate = new Gate(entry, schedule, 9, 3);
        Component exitGate = new Gate(exit, schedule, 3, 3);
        Component transitEntryStack = new Transit(schedule);
        Component transitStackExit = new Transit(schedule);
        Component stack = new StackModules(10, schedule, 4, 2);
        components.add(world);
        components.add(entryGate);
        components.add(transitEntryStack);
        components.add(stack);
        components.add(transitStackExit);
        components.add(exitGate);
        components.add(world);

        // Register the components so the schedule knows them
        schedule.registerComponents(components);

        // Get the data from the excel list and start populating the scheduler
        HashMap<Truck, LocalDateTime> timelist = ExcelTruckReader.read();
        schedule.addAll(timelist, components.getFirst());
    }

    /**
     * getViolation is a way to see if the simulation went into a state that
     * violated the restriction that no queue should be longer than 30 trucks.
     *
     * @return true if there was a violation of the max 30 truck queue length.
     */
    public final boolean getViolation() {
        return stats.getQueueViolation();
    }

    /**
     * getWaitTime is a way to get the total time that trucks had to wait in
     * queues in the simulation. The answer is given in seconds.
     *
     * @return a long that holds the number of seconds all the trucks had to
     *         wait in queue
     */
    public final long getWaitTime() {
        return stats.getTotalQueueTime();
    }

    /**
     * isActive reports back whether the simulation is still running.
     *
     * @return true if the simulation is still running.
     */
    private boolean isActive() {
        return schedule.isActive();
    }

    /**
     * run is the main loop of the simulator. While it is active it will take
     * steps.
     */
    public final void run() {
        // Main loop of the simulator
        while (isActive()) {
            step();
        }
    }

    /**
     * step is a single step of the simulator. In this case the steps are
     * entirely driven by the Schedule class. Although this program could be
     * adapted to make the layout of the harbor dynamic.
     */
    private void step() {
        schedule.doNextStep();
    }

}
