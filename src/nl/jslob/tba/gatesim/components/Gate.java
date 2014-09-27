package nl.jslob.tba.gatesim.components;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import nl.jslob.tba.gatesim.simulator.Schedule;
import nl.jslob.tba.gatesim.simulator.Truck;
import nl.jslob.tba.gatesim.util.GammaDistributionRate;

public class Gate implements Component {
	List<Queue<Truck>> queues;
	private Schedule schedule;
	GammaDistributionRate gd;

	/**
	 * Constructs a gate with a specified number of queue lanes and a link to
	 * the scheduler, and the shape parameters of the Gamma Distribution.
	 */
	public Gate(int lanes, Schedule schedule, int alpha, int beta) {
		queues = new ArrayList<Queue<Truck>>();
		for (int l = 0; l < lanes; l++) {
			queues.add(new LinkedList<Truck>());
		}
		this.schedule = schedule;
		gd = new GammaDistributionRate(alpha,beta);
	}

	@Override
	public void acceptTruck(Truck t) {
		t.setLocation(this);

		// Find the smallest queue
		Queue<Truck> min = queues.get(0);
		int min_val = queues.get(0).size();
		for (Queue<Truck> q : queues) {
			if (q.size() < min_val) {
				min = q;
				min_val = q.size();
			}
		}
		min.offer(t);
		// If the queue was empty, we can process this truck right away!
		if (min_val == 0) {
			schedule.nextTruckSecondFromNow(t, gd.getValueInSeconds());
		} else {
			// Otherwise we have to wait till other trucks are released...
			t.putInQueue(schedule.getNow());
		}
	}

	@Override
	public void releaseTruck(Truck t) {
		for (Queue<Truck> q : queues) {
			if (q.peek() == t) {
				q.poll(); // The requested truck is released
				// The next in the queue might become active
				if (!q.isEmpty()) {
					Truck next = q.peek();
					next.endQueueTime(schedule.getNow());
					schedule.nextTruckSecondFromNow(q.peek(), gd.getValueInSeconds());
				}
				return;
			}
		}
		System.out.println("Looking for Truck: " + t);
		for (Queue<Truck> q : queues) {
			System.out.println(q.size());
		}

		throw new IllegalStateException("Truck to be released not found!");
	}
}
