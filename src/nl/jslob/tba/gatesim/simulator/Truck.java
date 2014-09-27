package nl.jslob.tba.gatesim.simulator;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import nl.jslob.tba.gatesim.components.Component;

public class Truck implements Comparable {
	private String id;
	private String kind;
	private Component location;
	private long queue_seconds;
	private LocalTime startQueue;
	
	public Truck(String id, String kind) {
		this.id = id;
		this.kind = kind;
		location = null;
		queue_seconds = 0;
	}
	
	public void putInQueue(LocalTime time) {
		if (startQueue!=null) {
			throw new IllegalStateException("Truck is already in a queue");
		}
		startQueue = time;
	}
	
	public void endQueueTime(LocalTime time) {
		if (startQueue==null) {
			throw new IllegalStateException("Truck was not in a queue");
		}
		queue_seconds += startQueue.until(time, ChronoUnit.SECONDS);
		startQueue = null;
	}
	
	public long getQueueSeconds() {
		return queue_seconds;
	}
	
	public String getId() {
		return id;
	}
	
	public String getKind() {
		return kind;
	}

	public void setLocation(Component location) {
		this.location = location;
	}

	@Override
	public int compareTo(Object o) {
		Truck other = (Truck) o;
		return this.id.compareTo(other.id);
	}

	public Component getLocation() {
		return location;
	}
	
	@Override
	public String toString() {
		return id + " " + location;
	}

}
