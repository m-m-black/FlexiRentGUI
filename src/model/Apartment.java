package model;

import java.util.Date;
import java.util.HashMap;

import model.db.DatabaseMethods;
import model.db.DateTimeMethods;
import model.exceptions.RentException;

public class Apartment extends RentalProperty {
	
	// Constructor
	public Apartment(int streetNumber, String streetName, String suburb,
			int numBedrooms, PropertyType type, PropertyStatus status, String description, String image) {
		super(streetNumber, streetName, suburb, numBedrooms, type, status, description, image);
	}
	
	@Override
	public void rent(String propertyID, String customerID, String rentDateString, 
			String estReturnDateString) throws RentException {
		// check which day of week rentDate is, then check if minimum day requirement has been met
		DateTime rentDate = DateTimeMethods.dateFromString(rentDateString);
		DateTime estReturnDate = DateTimeMethods.dateFromString(estReturnDateString);
		int numDays = DateTime.diffDays(estReturnDate, rentDate);
		Date date = new Date(DateTimeMethods.dateFromString(rentDateString).getTime());
		String dateString = date.toString().split(" ")[0];
		if (dateString.compareTo("Fri") == 0 || dateString.compareTo("Sat") == 0) {
			if (numDays < 3) {
				throw new RentException("Must be 3 days minimum");
			}
		} else {
			if (numDays < 2) {
				throw new RentException("Must be 2 days minimum");
			}
		}
		if (numDays > 28) {
			throw new RentException("An apartment cannot be rented for more than 28 days");
		}
		super.rent(propertyID, customerID, rentDateString, estReturnDateString);
	}
	
	@Override
	protected double calculateLateFee(String propertyID, HashMap<String, String> recordMap) {
		HashMap<String, String> record = recordMap;
		double rentalRate = getRentalRate(propertyID);
		DateTime estReturnDate = DateTimeMethods.dateFromString(record.get("estReturnDate"));
		DateTime actReturnDate = DateTimeMethods.dateFromString(record.get("actReturnDate"));
		int numDays = DateTime.diffDays(actReturnDate, estReturnDate);
		return numDays * (rentalRate * 1.15);
	}
	
	@Override
	protected double getRentalRate(String propertyID) {
		int numBedrooms = DatabaseMethods.getNumBedrooms(propertyID);
		if (numBedrooms == 1) {
			return 143.0;
		} else if (numBedrooms == 2) {
			return 210.0;
		} else {
			return 319;
		}
	}

}
