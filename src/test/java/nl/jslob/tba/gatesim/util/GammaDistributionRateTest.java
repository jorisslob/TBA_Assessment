package nl.jslob.tba.gatesim.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * GammaDistributionRateTest is the JUnit test class of GammaDistributionRate.
 * In this test we check whether GammaDistributionRates are properly constructed
 * and conform to some basic expectancies we have of this distribution.
 *
 * @author jslob
 *
 */
public class GammaDistributionRateTest {

    /**
     * Because we are dealing with a random variable, we have to do multiple
     * calls to get statistical properties of this random variable. CHECKS is
     * the number of measurements that are done in the tests.
     */
    private static final int CHECKS = 1000;

    /**
     * OVERLAPMAX is the maximum number of values that two almost simultaneously
     * created distributions are allowed to return the same result in CHECKS
     * runs.
     */
    private static final int OVERLAPMAX = 20;

    /**
     * DEVIATIONMAX is the maximum amount by which the measured average over
     * CHECKS runs can deviate from expected average of the distribution.
     */
    private static final double DEVIATIONMAX = 5.0;

    /**
     * SECONDSPERMINUTE is a simple logical constant of the number of seconds
     * that go into a minute.
     */
    private static final int SECONDSPERMINUTE = 60;

    /**
     * JUnit test for the constructor. Can we construct a distribution without
     * errors? If two distributions are created directly after one another, do
     * they share the same seed and therefore generate the same values?
     */
    @Test
    public final void testGammaDistributionRate() {
        // This is a normal call to the GammaDistributionRate
        // We are only testing the constructor, so we understand the
        // object is not used in this function.
        @SuppressWarnings("unused")
        GammaDistributionRate gdr = new GammaDistributionRate(9, 3);

        // Test if two GammaDistributions created quickly after one another
        // provide different values, or whether they both have the same time
        // seed.
        int same = 0;
        for (int i = 0; i < CHECKS; i++) {
            GammaDistributionRate first = new GammaDistributionRate(9, 3);
            GammaDistributionRate second = new GammaDistributionRate(9, 3);
            if (first.getValueInSeconds() == second.getValueInSeconds()) {
                same++;
            }
        }
        assertTrue("In " + CHECKS + " tests with 2 distributions " + same
                + " overlapped", same < OVERLAPMAX);
    }

    /**
     * JUnit test if the getValueInSeconds function returns expected values.
     * None of the values should be 0 or smaller. The values should have an
     * expected average.
     */
    @Test
    public final void testGetValueInSeconds() {
        // Get some values from the GammaDistributionRate and check
        // properties:
        // * All values should be >0
        // * The average should tend towards alpha/beta * 60
        int alpha = 9;
        int beta = 3;
        GammaDistributionRate gdr = new GammaDistributionRate(alpha, beta);
        double avg = 0.0;
        for (int i = 0; i < CHECKS; i++) {
            long s = gdr.getValueInSeconds();
            avg += s / (double) CHECKS;
            assertTrue("Value is less than 0", s > 0);
        }
        double expected = (alpha / beta) * SECONDSPERMINUTE;
        double diff = Math.abs(avg - expected);
        assertTrue("Average " + avg + " too far from expected value "
                + expected, diff < DEVIATIONMAX);
    }
}
