package nl.jslob.tba.gatesim;

import nl.jslob.tba.gatesim.simulator.Simulator;

public class GateSim {
	private static int SIMULATIONS = 100;

	public static void main(String[] args) {
		// The main function runs the simulator and requests statistics.
		// TODO: Make this parallel so we can simulate faster
		for(int i=1; i<20; i++) {
			for(int j=1; j<20; j++) {
				long wait = 0;
				boolean violation = false;
				for(int k=1; k<SIMULATIONS; k++) {
					Simulator sim = new Simulator(i, j);
					sim.run();
					wait += sim.getWaitTime();
					violation = violation || sim.getViolation();
				}
				double avg = (double) wait / (double) SIMULATIONS;
				System.out.println(i + "," + j + "," + avg + "," + violation);
			}
		}
	}
}
