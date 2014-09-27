package nl.jslob.tba.gatesim;

import nl.jslob.tba.gatesim.simulator.Simulator;

public class GateSim {

	public static void main(String[] args) {
		// The main function runs the simulator and requests statistics.
		// TODO: Make this parallel so we can simulate faster
		Simulator sim = new Simulator(1,1);
		sim.run();
		System.out.println(sim.getStatistics());
	}
}
