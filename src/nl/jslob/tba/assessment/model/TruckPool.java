package nl.jslob.tba.assessment.model;

import nl.jslob.tba.assessment.impl.CollisionAwareScheduleImpl;

/*
 * The TruckPool should read the excel file and present a representation
 * that can be used in the application. TruckPool is used as the start position of a Harbor.
 */
public interface TruckPool {

	public abstract CollisionAwareScheduleImpl getTruckMap();

}
