package nl.jslob.tba.assessment.model;

import java.time.LocalTime;
import java.util.Map;
import java.util.Map.Entry;

public interface CollisionAwareSchedule extends Map<LocalTime, Truck> {
	// TODO: Refactor me!
	
	@Override
	public Truck put(LocalTime lt, Truck t);

	public Entry<LocalTime, Truck> pollFirstEntry();
}