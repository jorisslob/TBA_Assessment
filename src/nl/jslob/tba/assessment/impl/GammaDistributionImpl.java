package nl.jslob.tba.assessment.impl;

import org.apache.commons.math3.distribution.GammaDistribution;

public class GammaDistributionImpl {
	
	GammaDistribution gd;
	
	public GammaDistributionImpl(int alpha, int beta) {
		double inv_beta = 1.0/(float) beta;
		gd = new GammaDistribution(alpha, inv_beta);
	}
	
	public double getValueInNano() {
		double nano = gd.sample() * 60000000000D;
		return nano;
	}
}
