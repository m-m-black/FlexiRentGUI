package model;

import java.sql.Connection;
import java.sql.Statement;

import main.FlexiRentDBConnection;

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
		insertRow("flexiRentDB", "RENTAL_PROPERTY", values);
		
	}
	
	public void rent(String recordID, String propertyID, String customerID, DateTime rentDate, DateTime estReturnDate) {
		// create new RentalRecord in DB
		String values = "'" + recordID + "', '" + propertyID + "', '" + customerID + "', "
				+ "'2018-10-17'" + ", " + "'2018-10-18', " + "null, null, null";
		insertRow("flexiRentDB", "RENTAL_RECORD", values);

	}
	
	private void insertRow(String dbName, String tableName, String valueString) {
		final String DB_NAME = dbName;
		final String TABLE_NAME = tableName;
		String values = valueString;
		try (Connection con = FlexiRentDBConnection.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
				) {
			String query = "INSERT INTO " + TABLE_NAME +
					" VALUES (" + values + ")";
			int result = stmt.executeUpdate(query);
			con.commit();
			System.out.println("Insert into table " + TABLE_NAME + " executed successfully");
			System.out.println(result + " row(s) affected");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
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
