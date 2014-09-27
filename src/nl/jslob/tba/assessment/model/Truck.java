package nl.jslob.tba.assessment.model;

import java.time.LocalTime;

public interface Truck {
	public abstract LocalTime getArrivalTime();
	public abstract String getContainerID();
	public abstract boolean getLoaded();
	public abstract long getMinutesInQueue();
	public abstract void addQueueMinutes(long minutes);
}
