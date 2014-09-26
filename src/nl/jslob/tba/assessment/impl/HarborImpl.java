package nl.jslob.tba.assessment.impl;

import java.time.LocalTime;
import java.util.Map;
import java.util.TreeMap;

import nl.jslob.tba.assessment.model.Harbor;
import nl.jslob.tba.assessment.model.Truck;

public class HarborImpl implements Harbor {
	// TODO: Add datastructure for locations
	private Map<LocalTime, Truck> schedule;
	
	public HarborImpl(Map<LocalTime, Truck> truckmap) {
		// We use TreeMap locally because it keeps the ordering of time
		schedule = new TreeMap<LocalTime, Truck>(truckmap);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(LocalTime lt:schedule.keySet()) {
			sb.append(schedule.get(lt));
			sb.append("\n");
		}
		return sb.toString();
	}

}
