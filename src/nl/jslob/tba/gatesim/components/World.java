package nl.jslob.tba.gatesim.components;

import nl.jslob.tba.gatesim.simulator.Statistics;
import nl.jslob.tba.gatesim.simulator.Truck;

public class World implements Component {

	Statistics stats;

	public World(Statistics stats) {
		this.stats = stats;
	}

	@Override
	public double processTimeTruck(Truck t) {
		return 0;
	}

	@Override
	public boolean isQueueViolated() {
		return false;
	}

	@Override
	public double queueTimeTruck(Truck t) {
		return 0;
	}

	@Override
	public void acceptTruck(Truck t) {
		stats.addTruck(t);
	}

	@Override
	public void releaseTruck(Truck t) {

	}

}
