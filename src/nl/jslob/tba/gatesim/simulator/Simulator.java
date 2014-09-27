package nl.jslob.tba.gatesim.simulator;

import java.util.LinkedHashSet;

import nl.jslob.tba.gatesim.components.Component;

/**
 * Simulator Object that deals with time and registers components
 * Components include TruckActivity, Gates, Transit, Stacks and Queues
 * The Simulator Object is responsible to check if there is still activity in
 * its components and ending the simulation with a statistics overview.
 * 
 * In this particular Simulator, only two parameters are important:
 * (entry lanes and exit lanes). We will measure the performance by adding up
 * the total queue waiting time of the trucks and the amount of restriction
 * violations
 * 
 * @author jslob
 */
public class Simulator {
	
	// We choose a LinkedHashSet for this implementation because the order is important
	// and we have no duplicates of components.
	LinkedHashSet<Component> components;
	
	// The schedule has all the events that are waiting to happen.
	Schedule schedule;
	

	/**
	 * Creates a Simulator with a specified number of entry and exit lanes.
	 * @param entry number of entry lanes
	 * @param exit number of exit lanes
	 */
	public Simulator(int entry, int exit) {
		components = new LinkedHashSet<Component>();
		schedule = new Schedule();
	}

	public void run() {
		// Main loop of the simulator
		while(isActive()) {
			step();
		}
	}

	private void step() {
		// Nothing to do yet
	}

	private boolean isActive() {
		return schedule.isActive();
	}

	public String getStatistics() {
		return "Nothing simulated";
	}

}
