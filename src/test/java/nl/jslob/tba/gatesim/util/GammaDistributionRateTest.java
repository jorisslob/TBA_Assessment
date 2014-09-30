package nl.jslob.tba.gatesim.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GammaDistributionRateTest {
    
    @Test
    public void testGammaDistributionRate() {
        // This is a normal call to the Gamma Distribution Rate
        @SuppressWarnings("unused")
        GammaDistributionRate gdr = new GammaDistributionRate(9,3);
    }

    @Test
    public void testGetValueInSeconds() {
        GammaDistributionRate gdr = new GammaDistributionRate(9,3);
        for(int i=0; i<1000; i++) {
            long s = gdr.getValueInSeconds();
            assertTrue(s>=0);
        }
    }

}
