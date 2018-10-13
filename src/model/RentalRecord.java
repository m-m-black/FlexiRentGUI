package model;

import model.db.DatabaseMethods;
import model.db.DateTimeMethods;

public class RentalRecord {
	
	// Instance variable
	private String recordID;
	private DateTime rentDate;
	private DateTime estReturnDate;
	private DateTime actReturnDate;
	private double rentalFee;
	private double lateFee;
	
	// Constructor
	public RentalRecord(String propertyID, String customerID, String rentDate, String estReturnDate) {
		this.recordID = propertyID + "_" + customerID + "_" 
				+ DateTimeMethods.dateFromString(rentDate).getEightDigitDate();
		this.rentDate = DateTimeMethods.dateFromString(rentDate);
		this.estReturnDate = DateTimeMethods.dateFromString(estReturnDate);
		String values = "'" + this.recordID + "', '" + propertyID + "', '" + this.rentDate.toString() + "', '" 
		+ this.estReturnDate.toString() + "', 'null', '0', '0'";
		DatabaseMethods.insertRow("RENTAL_RECORD", values);
	}
	
	// Alternative constructor
	public RentalRecord(String recordID, String rentDate, String estReturnDate, 
			String actReturnDate, String rentalFee, String lateFee) {
		String propertyID = recordID.split("_")[0] + "_" + recordID.split("_")[1];
		String values = "'" + recordID + "', '" + propertyID + "', '" + rentDate + "', '" + estReturnDate + 
				"', '" + actReturnDate + "', '" + rentalFee + "', '" + lateFee + "'";
		DatabaseMethods.insertRow("RENTAL_RECORD", values);
	}
	
	public String getRecordID() {
		return recordID;
	}
	public void setRecordID(String recordID) {
		this.recordID = recordID;
	}
	public DateTime getRentDate() {
		return rentDate;
	}
	public void setRentDate(DateTime rentDate) {
		this.rentDate = rentDate;
	}
	public DateTime getEstReturnDate() {
		return estReturnDate;
	}
	public void setEstReturnDate(DateTime estReturnDate) {
		this.estReturnDate = estReturnDate;
	}
	public DateTime getActReturnDate() {
		return actReturnDate;
	}
	public void setActReturnDate(DateTime actReturnDate) {
		this.actReturnDate = actReturnDate;
	}
	public double getRentalFee() {
		return rentalFee;
	}
	public void setRentalFee(double rentalFee) {
		this.rentalFee = rentalFee;
	}
	public double getLateFee() {
		return lateFee;
	}
	public void setLateFee(double lateFee) {
		this.lateFee = lateFee;
	}

}
