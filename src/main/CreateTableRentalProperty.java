package main;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableRentalProperty {
	public static void main(String[] args) throws SQLException {
		
		final String DB_NAME = "flexiRentDB";
		final String TABLE_NAME = "RENTAL_PROPERTY";
		
		//use try-with-resources Statement
		try (Connection con = ConnectionTest.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			int result = stmt.executeUpdate("CREATE TABLE rental_property ("
										+ "propertyID VARCHAR(6) NOT NULL,"
										+ "streetNumber INT NOT NULL,"
										+ "streetName VARCHAR(40) NOT NULL,"
										+ "suburb VARCHAR(40) NOT NULL,"
										+ "numBedrooms INT NOT NULL,"
										+ "type VARCHAR(20) NOT NULL,"
										+ "status VARCHAR(20) NOT NULL,"
										+ "numRecords INT NOT NULL,"
										+ "lastMaintenanceDate DATE NOT NULL,"
										+ "description VARCHAR(100) NOT NULL,"
										+ "image VARCHAR(20) NOT NULL,"
										+ "PRIMARY KEY (propertyID))");
			if(result == 0) {
				System.out.println("Table " + TABLE_NAME + " has been created successfully");
			} else {
				System.out.println("Table " + TABLE_NAME + " is not created");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}