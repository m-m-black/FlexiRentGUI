package main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectQueryTest {
	public static void main(String[] args) {
		final String DB_NAME = "flexiRentDB";
		final String TABLE_NAME = "RENTAL_PROPERTY";
		
		//use try-with-resources Statement
		try (Connection con = ConnectionTest.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			String query = "SELECT * FROM " + TABLE_NAME;
			
			try (ResultSet resultSet = stmt.executeQuery(query)) {
				while(resultSet.next()) {
					System.out.printf("Id: %s | Street Number: %d | Street Name: %s | Suburb: %s\n",
							resultSet.getString("propertyID"), resultSet.getInt("streetNumber"), 
							resultSet.getString("streetName"), resultSet.getString("suburb"));
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
