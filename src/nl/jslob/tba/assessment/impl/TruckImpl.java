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
	
	@Override
	public String getContainerID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getLoaded() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public LocalTime getArrivalTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return arrivaltime + " " + id + " " + loaded;
	}
}
