package nl.jslob.tba.gatesim.components;

import java.util.LinkedList;
import java.util.Queue;

import nl.jslob.tba.gatesim.simulator.Schedule;
import nl.jslob.tba.gatesim.simulator.Truck;
import nl.jslob.tba.gatesim.util.GammaDistributionRate;

public class StackModules implements Component {
	
	Schedule schedule;
	int num_in_use;
	int modules;
	Queue<Truck> queue;
	GammaDistributionRate gd;

	public StackModules(int modules, Schedule schedule, int alpha, int beta) {
		this.modules = modules;
		this.schedule = schedule;
		queue = new LinkedList<Truck>();
		gd = new GammaDistributionRate(alpha,beta);
	}

	@Override
	public void acceptTruck(Truck t) {
		t.setLocation(this);
		if(num_in_use<modules) {
			if(!queue.isEmpty()) {
				throw new IllegalStateException("module is empty, but queue not");
			}
			num_in_use++;
			schedule.nextTruckSecondFromNow(t, gd.getValueInSeconds());
		} else {
			queue.add(t);
			t.putInQueue(schedule.getNow());
		}
	}

	@Override
	public void releaseTruck(Truck t) {
		if(num_in_use<1) {
			throw new IllegalStateException("Cannot release another truck, there are none in use");
		}
		num_in_use--;
		if(!queue.isEmpty()) {
			Truck next = queue.poll();
			next.endQueueTime(schedule.getNow());
			schedule.nextTruckSecondFromNow(next, gd.getValueInSeconds());
			num_in_use++;
		}
	}
}
