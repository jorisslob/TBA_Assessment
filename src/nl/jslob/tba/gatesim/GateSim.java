package nl.jslob.tba.gatesim;

import nl.jslob.tba.gatesim.simulator.Simulator;

public class GateSim {

	public static void main(String[] args) {
		// The main function runs the simulator and requests statistics.
		// TODO: Make this parallel so we can simulate faster
		for(int i=7; i<15; i++) {
			for(int j=3; j<10; j++) {
				Simulator sim = new Simulator(i, j);
				sim.run();
				System.out.println(i + "," + j + "," + sim.getStatistics());		
			}
		}
	}
}
