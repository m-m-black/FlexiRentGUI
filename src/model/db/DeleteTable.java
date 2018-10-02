package model.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteTable {
	public static void main(String[] args) throws SQLException {
		
		final String DB_NAME = "flexiRentDB";
		final String TABLE_NAME = "RENTAL_RECORD";
		
		//use try-with-resources Statement
		try (Connection con = FlexiRentDBConnection.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			int result = stmt.executeUpdate("DROP TABLE " + TABLE_NAME);
			
			if(result == 0) {
				System.out.println("Table " + TABLE_NAME + " has been deleted successfully");
			} else {
				System.out.println("Table " + TABLE_NAME + " was not deleted");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
