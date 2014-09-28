package nl.jslob.tba.gatesim.simulator;

/**
 * Statistics is an object that holds the results of the simulation and can
 * report back in the end. In this simulation, the total queue time of trucks
 * and the queue violations should show up.
 * 
 * @author jslob
 */
public class Statistics {
	int num_of_trucks;
	long queueTime;
	boolean queueViolation;

	public Statistics() {
		queueTime = 0;
		queueViolation = false;
		num_of_trucks = 0;
	}

	public void addTruck(Truck t) {
		num_of_trucks++;
		if(t.getQueueSeconds()<0) {
			throw new IllegalStateException("Cannot spend negative time in queue");
		}
		queueTime += t.getQueueSeconds();
		if(t.getLongQueue()) {
			queueViolation = true;
		}
	}

	public int getNumOfTrucks() {
		return num_of_trucks;
	}

	public long getTotalQueueTime() {
		return queueTime;
	}

	public boolean getQueueViolation() {
		return queueViolation;
	}
}
