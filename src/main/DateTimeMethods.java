package main;

import model.DateTime;

public class DateTimeMethods {
	
	// Method to take String input ("28/12/1988") and convert to DateTime (28/12/1988)
	public DateTime dateFromString(String dateString) {
		int[] dateArray = new int[3];
		dateArray[0] = Integer.parseInt(dateString.split("/")[0]);
		dateArray[1] = Integer.parseInt(dateString.split("/")[1]);
		dateArray[2] = Integer.parseInt(dateString.split("/")[2]);
		return new DateTime(dateArray[0], dateArray[1], dateArray[2]);
	}
	
	public static String toSQLDate(DateTime date) {
		// convert DateTime object to String like "yyyy-mm-dd"
		return "";
	}

}
