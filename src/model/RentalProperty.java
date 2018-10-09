package model;

import java.util.HashMap;

import model.db.DatabaseMethods;
import model.db.DateTimeMethods;
import model.exceptions.DateTimeException;
import model.exceptions.MaintenanceException;
import model.exceptions.RentException;
import model.exceptions.ReturnException;

public abstract class RentalProperty {
	
	// Instance variables
	private String propertyID;
	private int streetNumber;
	private String streetName;
	private String suburb;
	private int numBedrooms;
	private PropertyType type;
	private PropertyStatus status;
	private DateTime lastMaintenanceDate;
	private String description;
	private String image;
	
	// Constructors
	public RentalProperty() {}
	
	public RentalProperty(int streetNumber, String streetName, String suburb,
			int numBedrooms, PropertyType type, PropertyStatus status, String description, String image) {
		propertyID = generateID(type);
		// create new RentalProperty record in DB
		String values = "'" + propertyID + "', " + Integer.toString(streetNumber) + ", '" + streetName + "', '" 
				+ suburb + "', " + Integer.toString(numBedrooms) + ", '" + type.toString() + "', '" 
				+ status.toString() + "', " + "'" + new DateTime().toString() + "'" + ", '" 
				+ description + "', '" + image + "'";
		DatabaseMethods.insertRow("RENTAL_PROPERTY", values);
	}
	
	// toString method
	@Override
	public String toString() {
		return propertyID + ":" + streetNumber + ":" + streetName + ":" + suburb + ":" + 
				type + ":" + numBedrooms + ":" + status + ":" + lastMaintenanceDate + ":" + 
				image + ":" + description;
	}
	
	// Method to generate new propertyID, based on count of properties already in DB
	private String generateID(PropertyType type) {
		int propertyCount = DatabaseMethods.getPropertyCount(type.toString());
		String idTail = "";
		String id = "";
		if ((propertyCount + 1) < 10) {
			idTail = "00" + (propertyCount + 1);
		} else if ((propertyCount + 1) < 100) {
			idTail = "0" + (propertyCount + 1);
		} else {
			idTail += (propertyCount + 1);
		}
		if (type.equals(PropertyType.Apartment)) {
			id =  "A_" + idTail;
		} else if (type.equals(PropertyType.PremiumSuite)) {
			id =  "S_" + idTail;
		}
		return id;
	}
	
	// Rent method
	public static void rent(String propertyID, String customerID, String rentDate, 
			String estReturnDate) throws RentException {
		if (propertyID.isEmpty() || customerID.isEmpty()) {
			throw new RentException("Information is missing");
		} else {
			new RentalRecord(propertyID, customerID, rentDate, estReturnDate);
			DatabaseMethods.updateStatus(propertyID, PropertyStatus.Rented.toString());
		}
	}
	
	public void returnProperty(String propertyID, String actReturnDate) throws ReturnException {
		// find the current RentalRecord (will have actReturnDate == null)
		String recordID = DatabaseMethods.getCurrentRecord(propertyID);
		HashMap<String, String> record = DatabaseMethods.getRentalRecord(recordID);
		double rentalFee = calculateRentalFee(propertyID, record, actReturnDate);
		double lateFee = 0;
		// calculate late fee, if necessary
		if (DateTimeMethods.dateFromString(actReturnDate).getTime() > 
			DateTimeMethods.dateFromString(record.get("estReturnDate")).getTime()) {
			lateFee = calculateLateFee(propertyID, record, actReturnDate);
		}
		// update RentalRecord in DB with actReturnDate and fees
		DatabaseMethods.updateRecord(recordID, actReturnDate, rentalFee, lateFee);
		// update RentalProperty status in DB
		DatabaseMethods.updateStatus(propertyID, PropertyStatus.Available.toString());
	}
	
	public void performMaintenance(String propertyID) throws MaintenanceException {
		// change status in DB to UNDER_MAINTENANCE, if AVAILABLE
		if (DatabaseMethods.getStatus(propertyID).equals(PropertyStatus.Available.toString())) {
			DatabaseMethods.performMaintenance(propertyID, PropertyStatus.UnderMaintenance.toString());
		} else {
			throw new MaintenanceException("Property is not available for maintenance");
		}
	}
	
	public void completeMaintenance(String propertyID, String completionDate) throws MaintenanceException, DateTimeException {
		// change status in DB to AVAILABLE, if UNDER_MAINTENANCE
		if (DatabaseMethods.getStatus(propertyID).equals(PropertyStatus.UnderMaintenance.toString())) {
			DatabaseMethods.updateStatus(propertyID, PropertyStatus.Available.toString());
		} else {
			throw new MaintenanceException("Property is not currently under maintenance");
		}
	}
	
	private double calculateRentalFee(String propertyID, HashMap<String, String> recordMap, String actReturnDateString) {
		double rentalRate = getRentalRate(propertyID);
		HashMap<String, String> record = recordMap;
		DateTime rentDate = DateTimeMethods.dateFromString(record.get("rentDate"));
		DateTime actReturnDate = DateTimeMethods.dateFromString(actReturnDateString);
		int numDays = DateTime.diffDays(actReturnDate, rentDate);
		return numDays * rentalRate;
	}
	
	protected abstract double calculateLateFee(String propertyID, HashMap<String, String> record, String actReturnDateString);
	
	protected abstract double getRentalRate(String propertyID);
	
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
	public PropertyType getType() {
		return type;
	}
	public void setType(PropertyType type) {
		this.type = type;
	}
	public PropertyStatus getStatus() {
		return status;
	}
	public void setStatus(PropertyStatus status) {
		this.status = status;
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
