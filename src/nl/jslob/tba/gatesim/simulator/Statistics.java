package nl.jslob.tba.gatesim.simulator;

/**
 * Statistics is an object that holds the results of the simulation and can report 
 * back in the end. In this simulation, the total queue time of trucks and the queue 
 * violations should show up.
 * @author jslob
 */
public class Statistics {
	int num_of_trucks;
	double queueTime;
	int queueViolations;
	
	public Statistics() {
		queueTime = 0.0D;
		queueViolations = 0;
		num_of_trucks = 0;
	}
	
	public void addTruck(Truck t) {
		num_of_trucks++;
	}
	
	public int getNumOfTrucks() {
		return num_of_trucks;
	}
	
	public double getTotalQueueTime() {
		return queueTime;
	}
	
	public int getTotalQueueViolations() {
		return queueViolations;
	}

	public void addQueueTime(Object processTimeTruck) {
		// TODO Auto-generated method stub
		
	}

	public void addQueueViolation(Object queueViolated) {
		// TODO Auto-generated method stub
		
	}
}
