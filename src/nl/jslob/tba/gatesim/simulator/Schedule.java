package nl.jslob.tba.gatesim.simulator;

import java.time.LocalDateTime;
import java.util.LinkedList;
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

	public TreeMultimap<LocalDateTime, Truck> schedule;
	LinkedList<Component> components;
	LocalDateTime now;

	public Schedule() {
		schedule = TreeMultimap.create();
	}

	/**
	 * Returns true if there are any events that still need processing
	 * 
	 * @return
	 */
	public boolean isActive() {
		return schedule.size() > 0;
	}

	/**
	 * Provides an easy way to mass populate the schedule with new entry events.
	 * 
	 * @param read
	 * @param gate
	 */
	public void addAll(Map<Truck, LocalDateTime> timelist, Component next) {
		for (Entry<Truck, LocalDateTime> e : timelist.entrySet()) {
			Truck t = e.getKey();
			LocalDateTime now = e.getValue();
			t.setLocation(next);
			schedule.put(now, t);
		}
	}

	public void doNextStep() {
		// Find out which truck is next...
		LocalDateTime first = schedule.asMap().firstKey();
		Truck t = schedule.get(first).first();
		now = first;
		schedule.remove(first, t);

		// The component that has the truck should be notified that it is done.
		// And the next component should be notified that the truck is on its
		// way
		Component n = getNextComponent(t.getLocation());
		t.getLocation().releaseTruck(t);
		if (n == null) {
			System.out.println("Sending truck nowhere!");
			throw new IllegalStateException(
					"The components do not end with a World component");
		}
		n.acceptTruck(t);
	}

	private Component getNextComponent(Component c) {
		int i = components.indexOf(c) + 1;
		if (i < components.size()) {
			return components.get(i);
		}
		return null;
	}

	public void registerComponents(LinkedList<Component> components) {
		this.components = components;
	}

	public void nextTruckSecondFromNow(Truck t, long seconds) {
		schedule.put(getNow().plusSeconds(seconds), t);
	}

	public LocalDateTime getNow() {
		return now;
	}

}
