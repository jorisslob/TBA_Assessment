package nl.jslob.tba.assessment.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalTime;
import java.util.Map;
import java.util.TreeMap;

import nl.jslob.tba.assessment.model.HarborLocation;
import nl.jslob.tba.assessment.model.Truck;
import nl.jslob.tba.assessment.model.TruckPool;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class TruckPoolImpl implements TruckPool, HarborLocation {

	private Map<LocalTime, Truck> trucks;
	
	/*
	 * Create a new TruckPoolImpl using an xlsx file located at the absolute filepath absFilePath
	 */
	public TruckPoolImpl(String absFilePath) throws InvalidFormatException, IOException {
		trucks = new TreeMap<LocalTime, Truck>();
		
		InputStream inp = new FileInputStream(absFilePath);
		Workbook wb = WorkbookFactory.create(inp);
		Sheet sheet = wb.getSheetAt(0);
		int rownum = 1;
		Row row = sheet.getRow(rownum);
		Cell cell1 = row.getCell(0);
		Cell cell2 = row.getCell(1);
		Cell cell3 = row.getCell(2);
		String date, id, kind;
		int hour, minute, second;
		LocalTime time;
		while(cell1!=null) {
			date = cell1.getStringCellValue();
			id = cell2.getStringCellValue();
			kind = cell3.getStringCellValue();
			hour = Integer.parseInt(date.substring(9, 11));
			minute = Integer.parseInt(date.substring(11, 13));
			second = Integer.parseInt(date.substring(13, 15));
			time = LocalTime.of(hour, minute, second);
			boolean loaded = true;
			if(kind.equals("DLVR")) {
				loaded = false;
			}
			// We could get time clashes if two trucks arrive in the same second.
			// Let us compensate by delaying one truck by one nanosecond until there 
			// is no clash.
			while (trucks.containsKey(time)) {
				time = time.plusNanos(1);
			}
			Truck truck = new TruckImpl(time, id, loaded);
			trucks.put(time, truck);
			rownum++;
			row = sheet.getRow(rownum);
			cell1 = row.getCell(0);
			cell2 = row.getCell(1);
			cell3 = row.getCell(2);
		}
	}

	@Override
	public void processTruck() {
		// TODO Auto-generated method stub		
	}
	
	@Override
	public Map<LocalTime, Truck> getTruckMap() {
		return new TreeMap<LocalTime, Truck>(trucks);
	}
}
