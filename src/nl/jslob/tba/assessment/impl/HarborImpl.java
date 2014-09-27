package nl.jslob.tba.assessment.impl;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import nl.jslob.tba.assessment.model.CollisionAwareSchedule;
import nl.jslob.tba.assessment.model.Harbor;
import nl.jslob.tba.assessment.model.HarborLocation;
import nl.jslob.tba.assessment.model.Statistics;
import nl.jslob.tba.assessment.model.Truck;
import nl.jslob.tba.assessment.model.TruckPool;

public class HarborImpl implements Harbor {
	// We use TreeMap locally because it keeps the ordering of time
	private CollisionAwareScheduleImpl schedule;
	private List<HarborLocation> locations;
	private Map<Truck,HarborLocation> positions;
	private Statistics stats;
	
	public HarborImpl(CollisionAwareScheduleImpl truckmap, Statistics stats) {
		schedule = new CollisionAwareScheduleImpl(truckmap);

		locations = new LinkedList<HarborLocation>();
		positions = new HashMap<Truck,HarborLocation>();
		for(Truck t:truckmap.values()) {
			positions.put(t, null);
		}
		this.stats = stats;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Number of trucks still in system: "+schedule.size()+"\n");
		sb.append("Number of Harbor locations: "+locations.size());
		return sb.toString();
	}
	
	public String showSchedule() {
		StringBuilder sb = new StringBuilder();
		for(Truck t:schedule.values()) {
			sb.append(t + "\n");
		}
		return sb.toString();
	}

	@Override
	public void add(HarborLocation entry) {
		locations.add(entry);
	}

	@Override
	public boolean isActive() {
		return schedule.size()>0;
	}

	@Override
	public void nextStep() {
		Entry<LocalTime, Truck> entry = schedule.pollFirstEntry();
		Truck t = entry.getValue();
		HarborLocation loc = positions.remove(t);
		// First deal with the situation that the truck just arrives at the harbor
		if(loc==null) {
			HarborLocation newloc = locations.get(0);
			positions.put(t, newloc);
			schedule.put(entry.getKey().plusMinutes(2), t);
		}
		// Next lets deal with the situation that the truck is leaving the harbor
		else if (locations.indexOf(loc)==locations.size()-1) {
			// The truck isn't in a harbor location anymore
			// The truck doesn't reenter the schedule.
			stats.addTruck(t);
		}
		// The truck is moving from one location in the harbor to another.
		else {
			int pos_loc = locations.indexOf(loc);
			positions.put(t, locations.get(pos_loc+1));
			schedule.put(entry.getKey().plusMinutes(2), t);
		}
	}
}
