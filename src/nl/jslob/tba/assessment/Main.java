package nl.jslob.tba.assessment;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import nl.jslob.tba.assessment.impl.GammaDistributionImpl;
import nl.jslob.tba.assessment.impl.GateLocationImpl;
import nl.jslob.tba.assessment.impl.HarborImpl;
import nl.jslob.tba.assessment.impl.StatisticsImpl;
import nl.jslob.tba.assessment.impl.TransitLocation;
import nl.jslob.tba.assessment.impl.TruckPoolImpl;
import nl.jslob.tba.assessment.model.Harbor;
import nl.jslob.tba.assessment.model.HarborLocation;
import nl.jslob.tba.assessment.model.Statistics;
import nl.jslob.tba.assessment.model.TruckPool;

public class Main {
	private TruckPool pool;
	private Harbor h;
	private Statistics stats;
	
	private Main() {
		try {
			// First we read the truck activity from the xlsx file
			pool = new TruckPoolImpl("TruckActivity.xlsx");
			stats = new StatisticsImpl();

			// Next we initialize the Harbor
			// The harbor in this simulation looks like this:
			//
			// (pool ->) entry -> transit -> loader -> transit -> exit (-> statistics)
			h = new HarborImpl(pool.getTruckMap(), stats);
			h.add(new GateLocationImpl(9,3,1));
			h.add(new TransitLocation());
			
			while(h.isActive()) {
				h.nextStep();
			}
			stats.show();
			
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Main main = new Main();
	}
}
