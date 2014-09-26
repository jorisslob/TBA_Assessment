package nl.jslob.tba.assessment.model;

import java.time.LocalTime;
import java.util.Map;

/*
 * The TruckPool should read the excel file and present a representation
 * that can be used in the application. TruckPool is used as the start position of a Harbor.
 */
public interface TruckPool {

	public abstract Map<LocalTime, Truck> getTruckMap();

}
