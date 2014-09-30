package nl.jslob.tba.gatesim.util;

import org.apache.commons.math3.distribution.GammaDistribution;

/**
 * GammaDistributionRate is a wrapper around the Apache commons
 * GammaDistribution. In the Apache commons, it is assumed that the scale is
 * given, instead of the rate as parameter.
 *
 * @author jslob
 *
 */
public class GammaDistributionRate {

    /**
     * Number of seconds in a minute, physical constant.
     */
    private static final int SECONDSINMINUTE = 60;

    /**
     * This is the Apache commons GammaDistribution that actually does all the
     * work.
     */
    private final GammaDistribution gd;

    /**
     * Initialize a GammaDistributionRate where the alpha is the shape parameter
     * and beta the rate parameter.
     *
     * @param alpha
     *            Shape parameter of the Gamma Distribution.
     * @param beta
     *            Rate paramter of the Gamma Distribution.
     */
    public GammaDistributionRate(final int alpha, final int beta) {
        double invBeta = 1.0 / beta;
        gd = new GammaDistribution(alpha, invBeta);
    }

    /**
     * The GammaDistribution now returns values in minutes, but we want to be
     * more accurate. Here we return the values in seconds.
     *
     * @return Gamma Distribution Values in seconds.
     */
    public final long getValueInSeconds() {
        long seconds = (long) (gd.sample() * SECONDSINMINUTE);
        return seconds;
    }
}
