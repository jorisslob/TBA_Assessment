package nl.jslob.tba.assessment.impl;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import nl.jslob.tba.assessment.model.Harbor;
import nl.jslob.tba.assessment.model.HarborLocation;
import nl.jslob.tba.assessment.model.Truck;

public class HarborImpl implements Harbor {
	// TODO: Add datastructure for locations
	private Map<LocalTime, Truck> schedule;
	private List<HarborLocation> locations;
	
	public HarborImpl(Map<LocalTime, Truck> truckmap) {
		// We use TreeMap locally because it keeps the ordering of time
		schedule = new TreeMap<LocalTime, Truck>(truckmap);
		locations = new LinkedList<HarborLocation>();
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
}
