package model;

public class PremiumSuite extends RentalProperty {
	
	// Constructor
	public PremiumSuite(String propertyID, int streetNumber, String streetName, String suburb, 
			String type, String status, String description, String image) {
		super(propertyID, streetNumber, streetName, suburb, 3, type, status, description, image);
	}

}
