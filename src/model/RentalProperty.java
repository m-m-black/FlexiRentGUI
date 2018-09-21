package model;

import main.DatabaseMethods;

public abstract class RentalProperty {
	
	// Instance variables
	private String propertyID;
	private int streetNumber;
	private String streetName;
	private String suburb;
	private int numBedrooms;
	private String type;
	private String status;
	private int numRecords;
	private DateTime lastMaintenanceDate;
	private String description;
	private String image;
	
	// Constructor
	public RentalProperty(String propertyID, int streetNumber, String streetName, String suburb,
			int numBedrooms, String type, String status, String description, String image) {
		// create new RentalProperty record in DB
		String values = "'" + propertyID + "', " + streetNumber + ", '" + streetName + "', '" + suburb + 
				"', " + numBedrooms + ", '" + type + "', '" + status + "', " + 0 + 
				", " + "'2018-09-17'" + ", '" + description + "', '" + image + "'";
		DatabaseMethods.insertRow("RENTAL_PROPERTY", values);
		
	}
	
	// Rent method
	public void rent(String recordID, String propertyID, String customerID, DateTime rentDate, 
			DateTime estReturnDate) {
		// create new RentalRecord in DB
		String values = "'" + recordID + "', '" + propertyID + "', '" + customerID + "', "
				+ "'2018-10-17'" + ", " + "'2018-10-18', " + "null, null, null";
		DatabaseMethods.insertRow("RENTAL_RECORD", values);
		DatabaseMethods.updateStatus(this.propertyID, "RENTED");

	}
	
	public void returnProperty() {
		double rentalFee = 0; // need to calculate this
		double lateFee = 0; // need to calculate this
		// find the current RentalRecord (will have actReturnDate == null)
		String recordID = DatabaseMethods.getCurrentRecord(this.propertyID);
		// update RentalRecord in DB with actReturnDate, calculate fees
		DatabaseMethods.updateRecord(recordID, new DateTime(), rentalFee, lateFee);
		// update RentalProperty status in DB
		DatabaseMethods.updateStatus(this.propertyID, "AVAILABLE");
	}
	
	public void performMaintenance() {
		// change status in DB to UNDER_MAINTENANCE
		DatabaseMethods.performMaintenance(this.propertyID);
	}
	
	public void completeMaintenance() {
		// change status in DB to AVAILABLE
		DatabaseMethods.completeMaintenance(this.propertyID, new DateTime());
	}
	
	public String getPropertyID() {
		return propertyID;
	}
	public void setPropertyID(String propertyID) {
		this.propertyID = propertyID;
	}
	public int getStreetNumber() {
		return streetNumber;
	}
	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getSuburb() {
		return suburb;
	}
	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}
	public int getNumBedrooms() {
		return numBedrooms;
	}
	public void setNumBedrooms(int numBedrooms) {
		this.numBedrooms = numBedrooms;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getNumRecords() {
		return numRecords;
	}
	public void setNumRecords(int numRecords) {
		this.numRecords = numRecords;
	}
	public DateTime getLastMaintenanceDate() {
		return lastMaintenanceDate;
	}
	public void setLastMaintenanceDate(DateTime lastMaintenanceDate) {
		this.lastMaintenanceDate = lastMaintenanceDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

}
