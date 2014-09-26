package nl.jslob.tba.assessment;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

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
			h = new HarborImpl(pool.getTruckMap());
			HarborLocation entry = new GateLocationImpl(9,3,1);
			h.add(entry);
			// TODO: Construct the harbor components and link them
			// The harbor is this chain of HarborLocations:
			// Entry -> Transit -> Loader -> Transit -> Exit
			System.out.println(h);
			System.out.println(h.showSchedule());
			
			// TODO: Simulate the harbor
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
