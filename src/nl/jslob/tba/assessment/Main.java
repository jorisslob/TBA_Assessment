package nl.jslob.tba.assessment;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import nl.jslob.tba.assessment.impl.GammaDistributionImpl;
import nl.jslob.tba.assessment.impl.GateLocationImpl;
import nl.jslob.tba.assessment.impl.HarborImpl;
import nl.jslob.tba.assessment.impl.TruckPoolImpl;
import nl.jslob.tba.assessment.model.Harbor;
import nl.jslob.tba.assessment.model.HarborLocation;
import nl.jslob.tba.assessment.model.TruckPool;

public class Main {
	private TruckPool pool;
	private Harbor h;
	
	private Main() {
		try {
			// First we read the truck activity from the xlsx file
			pool = new TruckPoolImpl("TruckActivity.xlsx");

			// Next we initialize the Harbor
			// The harbor in this simulation looks like this:
			//
			// pool -> entry -> transit -> loader -> transit -> exit -> statistics
			h = new HarborImpl(pool.getTruckMap());
			h.add(new GateLocationImpl(9,3,1));
			// TODO: Add the next steps
			
			while(h.isActive()) {
				h.nextStep();
				System.out.println("Taking a harbor step");
			}
			// TODO: Show harbor statistics
			
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
