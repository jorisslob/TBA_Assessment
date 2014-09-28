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

/**
 * ExcelTruckReader is a utility class that reads Trucks from a specific xlsx
 * file. The Trucks are then returned in a Map with the truck and the time of
 * arrival.
 *
 * @author jslob
 *
 */
public final class ExcelTruckReader {
    /**
     * FILEPATH is a constant that points to the relative location of the xlsx
     * file.
     */
    private static final String FILEPATH = "TruckActivity.xlsx";

    /**
     * pareDateTime is a internal function that parses the date format as found
     * in the xlsx file to a LocalDateTime Object. Assumption is that the
     * computer running the application is in the same timezone as the xlsx
     * file.
     *
     * This function contains a lot of magic numbers. If the format of the xlsx
     * file ever changes, this function will break.
     *
     * @param date
     *            String in format yyyyMMdd_hhmmss
     * @return A LocalDateTime object that matches the string
     */
    private static LocalDateTime parseDateTime(final String date) {
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(4, 6));
        int day = Integer.parseInt(date.substring(6, 8));
        int hour = Integer.parseInt(date.substring(9, 11));
        int minute = Integer.parseInt(date.substring(11, 13));
        int second = Integer.parseInt(date.substring(13, 15));
        return LocalDateTime.of(year, month, day, hour, minute, second);
    }

    /**
     * read is a function that reads a file (FILEPATH) parses it and returns a
     * HashMap where the keys are Trucks and the values are LocalDateTime
     * objects. For this function we assume that the file has no errors and
     * there are no duplicate trucks in the file.
     *
     * The file is expected to have the following structure:
     * <ul>
     * <li>Column A: A string denoting a datetime in format yyyyMMdd_hhmmss
     * <li>Column B: A string denoting the id of a truck
     * <li>Column C: A string denoting whether the truck is there to receive or
     * to deliver
     * </ul>
     *
     * @return A HashMap containing the key/value pairs Trucks/LocalDateTime.
     */
    public static HashMap<Truck, LocalDateTime> read() {
        InputStream inp;
        HashMap<Truck, LocalDateTime> truckmap;

        truckmap = new HashMap<Truck, LocalDateTime>();
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
            while (cell1 != null) {
                date = cell1.getStringCellValue();
                id = cell2.getStringCellValue();
                kind = cell3.getStringCellValue();
                LocalDateTime time = parseDateTime(date);
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

    /**
     * ExcelTruckReader should not be instantiated, so the constructor is
     * private.
     */
    private ExcelTruckReader() {

    }
}
