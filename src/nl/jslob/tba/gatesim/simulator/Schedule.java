package nl.jslob.tba.gatesim.simulator;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.google.common.collect.TreeMultimap;

import nl.jslob.tba.gatesim.components.Component;

/**
 * A Schedule is an object that keeps track of all the events that are about to happen.
 * It is a wrapper around a collection that is ordered by Time and holds the next step 
 * that needs to be done.
 * 
 * @author jslob
 *
 */
public class Schedule {

	TreeMultimap<LocalTime, Truck> schedule;
	
	/**
	 * Returns true if there are any events that still need processing
	 * @return
	 */
	public boolean isActive() {
		return false;
	}

	/**
	 * Provides an easy way to mass populate the schedule with new events.
	 * @param read
	 * @param gate 
	 */
	public void addAll(HashMap<Truck,LocalTime> timelist, Component next) {
		for(Entry<Truck, LocalTime> e:timelist.entrySet()) {
			Truck t = e.getKey();
			t.setNext(next);
			schedule.put(e.getValue(), t);
		}
	}
	
}
