package nl.jslob.tba.assessment;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import nl.jslob.tba.assessment.impl.HarborImpl;
import nl.jslob.tba.assessment.impl.TruckPoolImpl;
import nl.jslob.tba.assessment.model.Harbor;
import nl.jslob.tba.assessment.model.TruckPool;

public class Main {
	private TruckPool tal;
	//private Harbor h;
	
	private Main() {
		try {
			tal = new TruckPoolImpl("TruckActivity.xlsx");
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//h = new HarborImpl(tal);
		// TODO: Construct the harbor components and link them
		// TODO: Simulate the harbor
	}
	
	public static void main(String[] args) {
		Main main = new Main();
	}
}
