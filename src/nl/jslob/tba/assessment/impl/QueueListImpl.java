package nl.jslob.tba.assessment.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import nl.jslob.tba.assessment.model.QueueList;
import nl.jslob.tba.assessment.model.Truck;

public class QueueListImpl implements QueueList {
	List<Queue<Truck>> queues;
	
	public QueueListImpl(int lanes) {
		// Check that we do have a lane, no lane, means a hole in our habor route!
		assert lanes>0;
		
		queues = new ArrayList<Queue<Truck>>();
		for(int i=0;i<lanes;i++) {
			// TODO: Check if LinkedList is the most optimal for this queue
			queues.add(new LinkedList<Truck>());
		}
	}
}
