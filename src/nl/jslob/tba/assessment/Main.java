package nl.jslob.tba.assessment;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import nl.jslob.tba.assessment.impl.HarborImpl;
import nl.jslob.tba.assessment.impl.TruckPoolImpl;
import nl.jslob.tba.assessment.model.Harbor;
import nl.jslob.tba.assessment.model.TruckPool;

public class Main {
	private TruckPool pool;
	private Harbor h;
	
	private Main() {
		try {
			pool = new TruckPoolImpl("TruckActivity.xlsx");
			h = new HarborImpl(pool.getTruckMap());
			System.out.println(h);
			// TODO: Construct the harbor components and link them
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
