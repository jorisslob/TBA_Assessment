package nl.jslob.tba.gatesim.simulator;

/**
 * A Schedule is an object that keeps track of all the events that are about to happen.
 * It is a wrapper around a collection that is ordered by Time and holds the next step 
 * that needs to be done.
 * 
 * @author jslob
 *
 */
public class Schedule {

	/**
	 * Returns true if there are any events that still need processing
	 * @return
	 */
	public boolean isActive() {
		return false;
	}
	
}
