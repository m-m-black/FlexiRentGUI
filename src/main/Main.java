package main;

import model.Apartment;
import model.DateTime;
import model.RentalProperty;

public class Main {

	public static void main(String[] args) {
		
		RentalProperty prop1 = new Apartment("a0", 273, "Winlow Crescent", "Caring Wood", 4, "Apartment",
				"Available", "Airy, spacious", "image.jpg");
		prop1.rent("r2", "a0", "morgan", new DateTime(), new DateTime());
		
	}

}
