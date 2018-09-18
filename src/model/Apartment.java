package model;

public class Apartment extends RentalProperty {
	
	// Constructor
	public Apartment(String propertyID, int streetNumber, String streetName, String suburb,
			int numBedrooms, String type, String status, String description, String image) {
		super(propertyID, streetNumber, streetName, suburb, numBedrooms, type, status, description, image);
	}

}
