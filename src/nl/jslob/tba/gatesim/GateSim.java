package nl.jslob.tba.gatesim;

import nl.jslob.tba.gatesim.simulator.Simulator;

public class GateSim {

	public static void main(String[] args) {
		// The main function runs the simulator and requests statistics.
		// TODO: Make this parallel so we can simulate faster
		for(int i=1; i<20; i++) {
			for(int j=1; j<20; j++) {
				Simulator sim = new Simulator(i, j);
				sim.run();
				System.out.println(i + "," + j + "," + sim.getStatistics());		
			}
		}
	}
}
