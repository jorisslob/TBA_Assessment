package nl.jslob.tba.assessment.impl;

import nl.jslob.tba.assessment.model.Statistics;
import nl.jslob.tba.assessment.model.Truck;

public class StatisticsImpl implements Statistics {

	private int num_trucks;
	
	public StatisticsImpl() {
		num_trucks = 0;
	}
	
	@Override
	public void show() {
		System.out.println("Number of trucks: "+num_trucks);
	}

	@Override
	public void addTruck(Truck t) {
		num_trucks++;
	}

}
