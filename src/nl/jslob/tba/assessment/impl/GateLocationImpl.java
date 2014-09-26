package nl.jslob.tba.assessment.impl;

import nl.jslob.tba.assessment.model.HarborLocation;
import nl.jslob.tba.assessment.model.QueueList;

public class GateLocationImpl implements HarborLocation {
	private QueueList ql;
	private int alpha;
	private int beta;

	
	/*
	 * Create a gate with gamma distribution waiting time with parameters alpha and beta and a
	 * queuelist with lanes lanes.
	 */
	public GateLocationImpl(int alpha, int beta, int lanes) {
		super();
		this.alpha = alpha;
		this.beta = beta;
		ql = new QueueListImpl(lanes);
	}

	public void processTruck() {
		// TODO Auto-generated method stub		
	}

}
