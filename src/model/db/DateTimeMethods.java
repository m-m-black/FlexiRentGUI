package model.db;

import model.DateTime;

public class DateTimeMethods {
	
	// Method to take String input ("28/12/1988") and convert to DateTime (28/12/1988)
	public static DateTime dateFromString(String dateString) {
		int[] dateArray = new int[3];
		dateArray[0] = Integer.parseInt(dateString.split("/")[0]);
		dateArray[1] = Integer.parseInt(dateString.split("/")[1]);
		dateArray[2] = Integer.parseInt(dateString.split("/")[2]);
		return new DateTime(dateArray[0], dateArray[1], dateArray[2]);
	}
	
	// Method to take String input from DatePicker object ("1988-12-28") and covert to String ("28/12/1988")
	public static String toString(String dateString) {
		String[] dateArray = new String[3];
		dateArray[0] = dateString.split("-")[2];
		dateArray[1] = dateString.split("-")[1];
		dateArray[2] = dateString.split("-")[0];
		return dateArray[0] + "/" + dateArray[1] + "/" + dateArray[2];
	}

}
