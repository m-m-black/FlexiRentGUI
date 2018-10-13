package model;

import java.util.HashMap;

import model.db.DatabaseMethods;
import model.db.DateTimeMethods;
import model.exceptions.DateTimeException;
import model.exceptions.MaintenanceException;
import model.exceptions.RentException;

public class PremiumSuite extends RentalProperty {
	
	// Constructors
	public PremiumSuite() {}
	
	public PremiumSuite(int streetNumber, String streetName, String suburb, 
			PropertyType type, PropertyStatus status, String description, String image) {
		super(streetNumber, streetName, suburb, 3, type, status, description, image);
	}
	
	public PremiumSuite(String propertyID, int streetNumber, String streetName, String suburb, 
			PropertyType type, PropertyStatus status, String description, String image) {
		super(propertyID, streetNumber, streetName, suburb, 3, type, status, description, image);
	}
	
	public static void rent(String propertyID, String customerID, String rentDateString, 
			String estReturnDateString) throws RentException {
		// check if maintenance will be due before estimated return date
		DateTime lastMaintenanceDate = DateTimeMethods.dateFromString(DatabaseMethods.getLastMaintenanceDate(propertyID));
		DateTime nextMaintenanceDate = new DateTime(lastMaintenanceDate, 10);
		DateTime estReturnDate = DateTimeMethods.dateFromString(estReturnDateString);
		if (estReturnDate.getTime() > nextMaintenanceDate.getTime()) {
			throw new RentException("Property cannot be rented as maintenance is due soon");
		} else {
			RentalProperty.rent(propertyID, customerID, rentDateString, estReturnDateString);
		}
	}
	
	@Override
	public void completeMaintenance(String propertyID, String completionDateString) throws MaintenanceException, DateTimeException {
		DateTime lastMaintenanceDate = DateTimeMethods.dateFromString(DatabaseMethods.getLastMaintenanceDate(propertyID));
		DateTime completionDate = DateTimeMethods.dateFromString(completionDateString);
		if (completionDate.getTime() < lastMaintenanceDate.getTime()) {
			throw new DateTimeException("Completion date must be after last maintenance date");
		} else {
			// update Property row with lastMaintenanceDate
			if (DatabaseMethods.getStatus(propertyID).equals(PropertyStatus.UnderMaintenance.toString())) {
				DatabaseMethods.completeMaintenance(propertyID, DateTimeMethods.dateFromString(completionDateString));
				// update Property status
				super.completeMaintenance(propertyID, completionDateString);
			} else {
				throw new MaintenanceException("This property is not currently under maintenance.");
			}
		}
	}
	
	@Override
	protected double calculateLateFee(String propertyID, HashMap<String, String> recordMap, String actReturnDateString) {
		HashMap<String, String> record = recordMap;
		DateTime estReturnDate = DateTimeMethods.dateFromString(record.get("estReturnDate"));
		DateTime actReturnDate = DateTimeMethods.dateFromString(actReturnDateString);
		int numDays = DateTime.diffDays(actReturnDate, estReturnDate);
		return numDays * 662;
	}

	@Override
	protected double getRentalRate(String propertyID) {
		return 554.0;
	}

}
