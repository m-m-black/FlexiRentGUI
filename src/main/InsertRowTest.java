package main;

import java.sql.Connection;
import java.sql.Statement;

public class InsertRowTest {
	public static void main(String[] args) {
		final String DB_NAME = "flexiRentDB";
		final String TABLE_NAME = "RENTAL_PROPERTY";
		
		//use try-with-resources Statement
		try (Connection con = ConnectionTest.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			String query = "INSERT INTO " + TABLE_NAME + 
					" VALUES ('A_002', 154, 'Station Street', 'Carlton', 2, 'Apartment', 'Available', "
					+ "0, '2018-09-17', 'Victorian terrace house', 'house.jpg')";
			
			int result = stmt.executeUpdate(query);
			
			con.commit();
			
			System.out.println("Insert into table " + TABLE_NAME + " executed successfully");
			System.out.println(result + " row(s) affected");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
