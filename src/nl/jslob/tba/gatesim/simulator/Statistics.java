package nl.jslob.tba.gatesim.simulator;

/**
 * Statistics is an object that holds the results of the simulation and can report 
 * back in the end. In this simulation, the total queue time of trucks and the queue 
 * violations should show up.
 * @author jslob
 */
public class Statistics {
	double queueTime;
	int queueViolations;
	
	public Statistics() {
		queueTime = 0.0D;
		queueViolations = 0;
	}
	
	public double getTotalQueueTime() {
		return queueTime;
	}
	
	public int getTotalQueueViolations() {
		return queueViolations;
	}
}
