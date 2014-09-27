package nl.jslob.tba.assessment.impl;

import nl.jslob.tba.assessment.model.HarborLocation;
import nl.jslob.tba.assessment.model.QueueList;

public class GateLocationImpl implements HarborLocation {
	private QueueList ql;
	private GammaDistributionImpl gd;

	
	/*
	 * Create a gate with gamma distribution waiting time with parameters alpha and beta and a
	 * queuelist with lanes lanes.
	 */
	public GateLocationImpl(int alpha, int beta, int lanes) {
		super();
		this.gd = new GammaDistributionImpl(alpha, beta);
		ql = new QueueListImpl(lanes);
	}

	public void processTruck() {
		// TODO Auto-generated method stub		
	}

}
