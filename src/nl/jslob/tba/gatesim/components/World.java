package nl.jslob.tba.gatesim.components;

import nl.jslob.tba.gatesim.simulator.Statistics;
import nl.jslob.tba.gatesim.simulator.Truck;

public class World implements Component {

	Statistics stats;

	public World(Statistics stats) {
		this.stats = stats;
	}

	@Override
	public void acceptTruck(Truck t) {
		stats.addTruck(t);
	}

	@Override
	public void releaseTruck(Truck t) {

	}

}
