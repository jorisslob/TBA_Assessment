package nl.jslob.tba.gatesim.components;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import nl.jslob.tba.gatesim.simulator.Schedule;
import nl.jslob.tba.gatesim.simulator.Truck;

public class Gate implements Component {
	List<Queue<Truck>> queues; 
	private Schedule schedule;
	String name;
	/**
	 * Constructs a gate with a specified number of queue lanes and a link to the scheduler
	 * @param schedule 
	 * @param entry
	 */
	public Gate(int lanes, Schedule schedule, String name) {
		queues = new ArrayList<Queue<Truck>>();
		for(int i=0; i<lanes; i++) {
			queues.add(new LinkedList<Truck>());
		}
		this.schedule = schedule;
		this.name = name;
	}

	@Override
	public double processTimeTruck(Truck t) {
		return 0.0D;
	}

	@Override
	public boolean isQueueViolated() {
		return false;
	}

	@Override
	public double queueTimeTruck(Truck t) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public long acceptTruck(Truck t) {
		t.setLocation(this);
		
		// Find the smallest queue
		Queue<Truck> min = queues.get(0); 
		int min_val = queues.get(0).size();
		for(Queue<Truck> q:queues) {
			if (q.size()<min_val) {
				min = q;
				min_val = q.size();
			}
		}
		min.offer(t);
		// If the queue was empty, we can process this truck right away!
		if(min_val==0) {
			return 3*60;
		}
		// Otherwise we have to wait till trucks are released...
		return -1;
	}
	
	public void releaseTruck(Truck t) {
		for(Queue<Truck> q:queues) {
			if(q.peek()==t) {
				q.poll(); // The requested truck is released
				// The next in the queue might become active
				if(!q.isEmpty()) {
					schedule.nextTruckSecondFromNow(q.peek(), 3*60);
				}
				return;
			}
		}
		System.out.println("Looking for Truck: "+t);
		for(Queue<Truck> q:queues) {
			System.out.println(q.size());
		}
		
		throw new IllegalStateException("Truck to be released not found!");
	}

	@Override
	public String toString() {
		return name;
	}
}
