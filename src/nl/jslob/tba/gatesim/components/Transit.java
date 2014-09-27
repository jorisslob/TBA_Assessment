package nl.jslob.tba.gatesim.components;

import nl.jslob.tba.gatesim.simulator.Schedule;
import nl.jslob.tba.gatesim.simulator.Truck;

public class Transit implements Component {

	Schedule schedule;
	
	public Transit(Schedule schedule) {
		this.schedule = schedule;
	}
	
	@Override
	public void acceptTruck(Truck t) {
		t.setLocation(this);
		// All the roads in this simulation are 3 minutes long...
		schedule.nextTruckSecondFromNow(t, 3 * 60);
	}

	@Override
	public void releaseTruck(Truck t) {

	}

}
