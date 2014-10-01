package nl.jslob.tba.gatesim;

import nl.jslob.tba.gatesim.simulator.Simulator;

/**
 * GateSim is the entrypoint of the program. It runs simulations as descibed
 * in ASSIGNMENT.md. The class determines how many times the simulation will
 * run and with what parameters. The results are sent to the System standard
 * output. The output has the following format:
 * ENTRY_LANES,EXIT_LANES,TOTAL_WAIT_TIME,VIOLATIONS?
 *
 * Where ENTRY_LANES is the number of lanes were simulated for the entry gate,
 * EXIT_LANES the number of lanes for the exit gate, TOTAL_WAIT_TIME is the
 * sum of the time that all trucks spent in queues and VIOLATIONS? is a
 * boolean that notes if any of the queues exceeded 30 trucks.
 *
 * Constant parameters that can be changed to change the behavior of the
 * program:
 * <ul>
 * <li>SIMULATIONS: Determines how many times each simulation has to run.
 * <li>ENTRY_MAX: Determines how many entry lanes are maximally tested.
 * <li>EXIT_MAX: Determines how many exit lanes are maximally tested.
 * </ul>
 *
 * @author jslob
 *
 */
public final class GateSim {
    /**
     * ENTRY_MAX determines up to how many entry lanes must be simulated.
     */
    private static final int ENTRY_MAX = 40;
    /**
     * EXIT_MAX determines up to how many exit lanes must be simulated.
     */
    private static final int EXIT_MAX = 15;
    /**
     * SIMULATIONS determines how many times every simulation must run for
     * statistical significance.
     */
    private static final int SIMULATIONS = 10;

    /**
     * Main is the entry function of our program. It will run through all
     * number of entry and exit lane possibilities and report back which ones
     * violate the max queue rule and how much time the trucks spend waiting
     * in queues.
     *
     * @param args Arguments to this program are ignored.
     */
    public static void main(final String[] args) {
        // The main function runs the simulator and requests statistics.
        for (int i = 1; i < ENTRY_MAX; i++) {
            for (int j = 1; j < EXIT_MAX; j++) {
                long wait = 0;
                boolean violation = false;
                int rounds = 0;
                for (int k = 0; k < SIMULATIONS; k++) {
                    Simulator sim = new Simulator(i, j);
                    sim.run();
                    wait += sim.getWaitTime();
                    violation = sim.getViolation();
                    rounds++;
                    if (violation) {
                        break;
                    }
                }
                double avg = (double) wait / (double) rounds;
                System.out.println(i + "," + j + "," + avg + "," + violation);
            }
        }
    }

    /**
     * Private constructor to avoid instantiation.
     */
    private GateSim() {
        // Class should not be instantiated
    }
}
