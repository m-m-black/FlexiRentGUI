package main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import model.DateTime;

public class DatabaseMethods {
	
	public static void insertRow(String tableName, String valueString) {
		final String DB_NAME = "flexiRentDB";
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
	
	public static void performMaintenance(String propertyID) {
		final String DB_NAME = "flexiRentDB";
		final String TABLE_NAME = "RENTAL_PROPERTY";
		try (Connection con = FlexiRentDBConnection.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
				) {
			String query = "UPDATE " + TABLE_NAME +
					" SET status = 'UNDER_MAINTENANCE'" +
					" WHERE propertyID LIKE '" + propertyID + "'";
			int result = stmt.executeUpdate(query);
			System.out.println("Update table " + TABLE_NAME + " executed successfully");
			System.out.println(result + " row(s) affected");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void completeMaintenance(String propertyID, DateTime completionDate) {
		final String DB_NAME = "flexiRentDB";
		final String TABLE_NAME = "RENTAL_PROPERTY";
		try (Connection con = FlexiRentDBConnection.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
				) {
			String query = "UPDATE " + TABLE_NAME +
					" SET status = 'AVAILABLE'," + 
					" lastMaintenanceDate = '" + DateTimeMethods.toSQLDate(completionDate) +
					"' WHERE propertyID LIKE '" + propertyID + "'";
			int result = stmt.executeUpdate(query);
			System.out.println("Update table " + TABLE_NAME + " executed successfully");
			System.out.println(result + " row(s) affected");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void updateRecord(String recordID, DateTime actReturnDate, double rentalFee, double lateFee) {
		final String DB_NAME = "flexiRentDB";
		final String TABLE_NAME = "RENTAL_RECORD";
		try (Connection con = FlexiRentDBConnection.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
				) {
			String query = "UPDATE " + TABLE_NAME + 
					" SET actReturnDate = '" + DateTimeMethods.toSQLDate(actReturnDate) + 
					"', rentalFee = " + rentalFee + ", lateFee = " + lateFee + 
					" WHERE recordID LIKE '" + recordID + "'";
			int result = stmt.executeUpdate(query);
			System.out.println("Update table " + TABLE_NAME + " executed successfully");
			System.out.println(result + " row(s) affected");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void updateStatus(String propertyID, String status) {
		final String DB_NAME = "flexiRentDB";
		final String TABLE_NAME = "RENTAL_PROPERTY";
		try (Connection con = FlexiRentDBConnection.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
				) {
			String query = "UPDATE " + TABLE_NAME +
					" SET status = '" + status + "'" +
					" WHERE propertyID LIKE '" + propertyID + "'";
			int result = stmt.executeUpdate(query);
			System.out.println("Update table " + TABLE_NAME + " executed successfully");
			System.out.println(result + " row(s) affected");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static String selectRow(String tableName, String id) {
		final String DB_NAME = "flexiRentDB";
		final String TABLE_NAME = tableName;
		String identifier = null;
		String returnValue = null;
		if (TABLE_NAME.compareTo("RENTAL_PROPERTY") == 0) {
			identifier = "propertyID";
		} else if (TABLE_NAME.compareTo("RENTAL_RECORD") == 0) {
			identifier = "recordID";
		}
		try (Connection con = FlexiRentDBConnection.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
				) {
			String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + identifier + " LIKE " + id;
			try (ResultSet resultSet = stmt.executeQuery(query)) {
				returnValue = resultSet.getString(identifier);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
 		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return returnValue;
	}
	
	public static String getCurrentRecord(String propertyID) {
		final String DB_NAME = "flexiRentDB";
		final String TABLE_NAME = "RENTAL_RECORD";
		String recordID = null;
		try (Connection con = FlexiRentDBConnection.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
				) {
			String query = "SELECT recordID FROM " + TABLE_NAME + 
					" WHERE propertyID LIKE " + propertyID + 
					" AND actReturnDate IS NULL";
			try (ResultSet resultSet = stmt.executeQuery(query)) {
				recordID = resultSet.getString("recordID");
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
 		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return recordID;
	}
	
	public static ArrayList<HashMap<String, String>> getProperties() {
		final String DB_NAME = "flexiRentDB";
		final String TABLE_NAME = "RENTAL_PROPERTY";
		// ArrayList to store temporary RentalProperty objects, where each object is represented as a HashMap
		ArrayList<HashMap<String, String>> propertiesResultSets = new ArrayList<HashMap<String, String>>();
		try (Connection con = FlexiRentDBConnection.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
				) {
			String query = "SELECT * FROM " + TABLE_NAME;
			try (ResultSet resultSet = stmt.executeQuery(query)) {
				while (resultSet.next()) {
					// Build HashMap from each DB row, add to ArrayList
					HashMap<String, String> p = new HashMap<String, String>();
					p.put("propertyID", resultSet.getString(1));
					p.put("streetNumber", resultSet.getString(2));
					p.put("streetName", resultSet.getString(3));
					p.put("suburb", resultSet.getString(4));
					p.put("numBedrooms", resultSet.getString(5));
					p.put("type", resultSet.getString(6));
					p.put("status", resultSet.getString(7));
					p.put("numRecords", resultSet.getString(8));
					p.put("lastMaintenanceDate", resultSet.getString(9));
					p.put("description", resultSet.getString(10));
					p.put("image", resultSet.getString(11));
					propertiesResultSets.add(p);
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return propertiesResultSets;
	}

}
