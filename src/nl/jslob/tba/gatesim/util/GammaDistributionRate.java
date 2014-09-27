package nl.jslob.tba.gatesim.util;

import org.apache.commons.math3.distribution.GammaDistribution;

public class GammaDistributionRate {
	
	GammaDistribution gd;
	
	public GammaDistributionRate(int alpha, int beta) {
		double inv_beta = 1.0/(float) beta;
		gd = new GammaDistribution(alpha, inv_beta);
	}
	
	public double getValueInSeconds() {
		double nano = gd.sample() * 60;
		return nano;
	}
}
