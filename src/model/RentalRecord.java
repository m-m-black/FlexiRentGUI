package model;

public class RentalRecord {
	
	// Instance variable
	private String recordID;
	private DateTime rentDate;
	private DateTime estReturnDate;
	private DateTime actReturnDate;
	private double rentalFee;
	private double lateFee;
	
	// Constructor
	public RentalRecord() {}
	
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
