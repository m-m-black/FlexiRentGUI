package main;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseMethods {
	
	public static void insertRow(String dbName, String tableName, String valueString) {
		final String DB_NAME = dbName;
		final String TABLE_NAME = tableName;
		String values = valueString;
		try (Connection con = FlexiRentDBConnection.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
				) {
			String query = "INSERT INTO " + TABLE_NAME +
					" VALUES (" + values + ")";
			int result = stmt.executeUpdate(query);
			con.commit();
			System.out.println("Insert into table " + TABLE_NAME + " executed successfully");
			System.out.println(result + " row(s) affected");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void updateRow() {}
	
	public static void selectRow(String dbName, String tableName, String valueString) {}

}
