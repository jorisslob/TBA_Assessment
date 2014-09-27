package nl.jslob.tba.gatesim.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.HashMap;

import nl.jslob.tba.gatesim.simulator.Truck;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelTruckReader {
	private static String FILEPATH = "TruckActivity.xlsx";

	public static HashMap<Truck, LocalDateTime> read() {
		InputStream inp;
		HashMap<Truck, LocalDateTime> truckmap = new HashMap<Truck, LocalDateTime>();
		try {
			inp = new FileInputStream(FILEPATH);
			Workbook wb = WorkbookFactory.create(inp);
			Sheet sheet = wb.getSheetAt(0);
			int rownum = 1;
			Row row = sheet.getRow(rownum);
			Cell cell1 = row.getCell(0);
			Cell cell2 = row.getCell(1);
			Cell cell3 = row.getCell(2);
			String date, id, kind;
			int year, month, day, hour, minute, second;
			while (cell1 != null) {
				date = cell1.getStringCellValue();
				id = cell2.getStringCellValue();
				kind = cell3.getStringCellValue();
				year = Integer.parseInt(date.substring(0, 4));
				month = Integer.parseInt(date.substring(4, 6));
				day = Integer.parseInt(date.substring(6, 8));
				hour = Integer.parseInt(date.substring(9, 11));
				minute = Integer.parseInt(date.substring(11, 13));
				second = Integer.parseInt(date.substring(13, 15));
				LocalDateTime time = LocalDateTime.of(year, month, day, hour, minute, second);
				Truck t = new Truck(id, kind);
				truckmap.put(t, time);
				rownum++;
				row = sheet.getRow(rownum);
				cell1 = row.getCell(0);
				cell2 = row.getCell(1);
				cell3 = row.getCell(2);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return truckmap;
	}
}
