package nl.jslob.tba.gatesim.simulator;

import nl.jslob.tba.gatesim.components.Component;

public class Truck {
	private String id;
	private String kind;
	private Component next;
	
	public Truck(String id, String kind) {
		this.id = id;
		this.kind = kind;
		next = null;
	}
	
	public String getId() {
		return id;
	}
	
	public String getKind() {
		return kind;
	}

	public void setNext(Component next) {
		this.next = next;
	}

}
