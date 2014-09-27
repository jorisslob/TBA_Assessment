package nl.jslob.tba.gatesim.simulator;

import nl.jslob.tba.gatesim.components.Component;

public class Truck implements Comparable {
	private String id;
	private String kind;
	private Component location;
	
	public Truck(String id, String kind) {
		this.id = id;
		this.kind = kind;
		location = null;
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
