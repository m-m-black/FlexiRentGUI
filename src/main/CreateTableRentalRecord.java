package main;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableRentalRecord {
	public static void main(String[] args) throws SQLException {
		
		final String DB_NAME = "flexiRentDB";
		final String TABLE_NAME = "RENTAL_RECORD";
		
		//use try-with-resources Statement
		try (Connection con = ConnectionTest.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			int result = stmt.executeUpdate("CREATE TABLE rental_record ("
										+ "recordID VARCHAR(6) NOT NULL,"
										+ "propertyID VARCHAR(6) NOT NULL,"
										+ "customerID VARCHAR(6) NOT NULL,"
										+ "rentDate DATE NOT NULL,"
										+ "estReturnDate DATE NOT NULL,"
										+ "actReturnDate DATE,"
										+ "rentalFee REAL,"
										+ "lateFee REAL,"
										+ "PRIMARY KEY (recordID),"
										+ "FOREIGN KEY (propertyID) REFERENCES rental_property(propertyID))");
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