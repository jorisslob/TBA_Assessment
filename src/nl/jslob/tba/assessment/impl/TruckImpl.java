package nl.jslob.tba.assessment.impl;

import java.time.LocalTime;

import nl.jslob.tba.assessment.model.Truck;

public class TruckImpl implements Truck {
	LocalTime arrivaltime;
	String id;
	boolean loaded;
	
	public TruckImpl(LocalTime arrivaltime, String id, boolean loaded) {
		this.arrivaltime = arrivaltime;
		this.id = id;
		this.loaded = loaded;
	}
	
	public String getContainerID() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean getLoaded() {
		// TODO Auto-generated method stub
		return false;
	}

	public LocalTime getArrivalTime() {
		// TODO Auto-generated method stub
		return null;
	}

	public String toString() {
		return arrivaltime + " " + id + " " + loaded;
	}
}
