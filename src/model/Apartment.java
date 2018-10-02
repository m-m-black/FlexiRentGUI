package model;

import java.util.HashMap;

import model.db.DatabaseMethods;
import model.db.DateTimeMethods;

public class Apartment extends RentalProperty {
	
	// Constructor
	public Apartment(int streetNumber, String streetName, String suburb,
			int numBedrooms, PropertyType type, PropertyStatus status, String description, String image) {
		super(streetNumber, streetName, suburb, numBedrooms, type, status, description, image);
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
