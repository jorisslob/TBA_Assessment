package nl.jslob.tba.gatesim.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GammaDistributionRateTest {
    
    @Test
    public void testGammaDistributionRate() {
        // This is a normal call to the GammaDistributionRate
        // We are only testing the constructor, so we understand the
        // object is not used in this function.
        @SuppressWarnings("unused")
        GammaDistributionRate gdr = new GammaDistributionRate(9,3);
    }

    @Test
    public void testGetValueInSeconds() {
        // Get some values from the GammaDistributionRate and check
        // properties:
        // * All values should be >0
        // * The average should tend towards 180
        int tests = 1000;
        GammaDistributionRate gdr = new GammaDistributionRate(9,3);
        double avg = 0.0;
        for(int i=0; i<tests; i++) {
            long s = gdr.getValueInSeconds();
            avg += s/(double) tests;
            assertTrue("Value is less than 0", s>0);
        }
        double expected = 3*60;
        double diff = Math.abs(avg-expected);
        assertTrue("Average "+avg+" too far from expected value "+expected, Math.abs(avg-180.0)<5.0);
    }
}
