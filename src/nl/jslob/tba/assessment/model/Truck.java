package nl.jslob.tba.assessment.model;

import java.time.LocalTime;

public interface Truck {
	public LocalTime getArrivalTime();
	public String getContainerID();
	public boolean getLoaded();
}
