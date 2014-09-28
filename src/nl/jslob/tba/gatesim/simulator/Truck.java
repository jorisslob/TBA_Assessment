package nl.jslob.tba.gatesim.simulator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import nl.jslob.tba.gatesim.components.Component;

public class Truck implements Comparable<Truck> {
	private String id;
	private String kind;
	private Component location;
	private long queue_seconds;
	private LocalDateTime startQueue;
	private boolean longQueue;

	public Truck(String id, String kind) {
		this.id = id;
		this.kind = kind;
		location = null;
		queue_seconds = 0;
		longQueue = false;
	}

	public void putInQueue(LocalDateTime time) {
		if (startQueue != null) {
			throw new IllegalStateException("Truck is already in a queue");
		}
		startQueue = time;
	}

	public void endQueueTime(LocalDateTime time) {
		if (startQueue == null) {
			throw new IllegalStateException("Truck was not in a queue");
		}
		long extra_seconds = startQueue.until(time, ChronoUnit.SECONDS);
		
		if(extra_seconds<0) {
			System.out.println(startQueue + " until " + time + " = " + extra_seconds);
			throw new IllegalStateException("Cannot spend negative time in queue");
		}
		queue_seconds += extra_seconds;
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

	public Component getLocation() {
		return location;
	}

	@Override
	public String toString() {
		return id + " " + location;
	}

	@Override
	public int compareTo(Truck o) {
		return this.id.compareTo(o.id);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Truck other = (Truck) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public void inLongQueue() {
		longQueue = true;
	}
	
	public boolean getLongQueue() {
		return longQueue;
	}
}
