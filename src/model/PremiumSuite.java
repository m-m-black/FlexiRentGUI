package model;

import java.util.HashMap;

import model.db.DateTimeMethods;

public class PremiumSuite extends RentalProperty {
	
	// Constructor
	public PremiumSuite(int streetNumber, String streetName, String suburb, 
			PropertyType type, PropertyStatus status, String description, String image) {
		super(streetNumber, streetName, suburb, 3, type, status, description, image);
	}
	
	@Override
	protected double calculateLateFee(String propertyID, HashMap<String, String> recordMap) {
		HashMap<String, String> record = recordMap;
		DateTime estReturnDate = DateTimeMethods.dateFromString(record.get("estReturnDate"));
		DateTime actReturnDate = DateTimeMethods.dateFromString(record.get("actReturnDate"));
		int numDays = DateTime.diffDays(actReturnDate, estReturnDate);
		return numDays * 662;
	}

	@Override
	protected double getRentalRate(String propertyID) {
		return 554.0;
	}

}
