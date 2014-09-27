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
	
	// Statistics object holds the important values for this simulation.
	Statistics stats;
	

	/**
	 * Creates a Simulator with a specified number of entry and exit lanes.
	 * @param entry number of entry lanes
	 * @param exit number of exit lanes
	 */
	public Simulator(int entry, int exit) {
		// Initialize internal structures of the Simulator
		components = new LinkedHashSet<Component>();
		schedule = new Schedule();
		stats = new Statistics();
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
		StringBuilder sb = new StringBuilder();
		sb.append("Total Queue Time: ");
		sb.append(stats.getTotalQueueTime());
		sb.append("\n");
		sb.append("Total Queue Violations: ");
		sb.append(stats.getTotalQueueViolations());
		sb.append("\n");
		return sb.toString();
	}

}
